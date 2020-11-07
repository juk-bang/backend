package com.jukbang.api.file.service;

import com.jukbang.api.configs.FileConfig;
import com.jukbang.api.file.data.ThumbnailImage;
import com.jukbang.api.file.data.ThumbnailImageRepository;
import com.jukbang.api.file.exception.CantCreateFileDirectoryException;
import com.jukbang.api.file.exception.FileDownloadException;
import com.jukbang.api.room.exception.NotYourRoomException;
import com.jukbang.api.room.repository.RoomRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ThumbnailImageService {

  private final Path thumbnailImageLocation;

  private final FileService fileService;

  private final ThumbnailImageRepository thumbnailImageRepository;
  private final RoomRepository roomRepository;

  @Autowired
  public ThumbnailImageService(FileConfig config, ThumbnailImageRepository thumbnailImageRepository,
                               RoomRepository roomRepository, FileService fileService) {
    this.thumbnailImageRepository = thumbnailImageRepository;
    this.fileService = fileService;
    this.roomRepository = roomRepository;
    this.thumbnailImageLocation = Paths.get(config.getThumbnailDir())
        .toAbsolutePath().normalize();
    try {
      Files.createDirectories(this.thumbnailImageLocation);
    } catch (Exception e) {
      throw new CantCreateFileDirectoryException(this.thumbnailImageLocation.toString(), e);
    }
  }

  public void storeThumbnailImage(String userId, long roomId, long imageId, MultipartFile file) {
      if (!roomRepository.existsByRoomIdAndSeller_UserId(roomId, userId)) {
          throw new NotYourRoomException();
      }
    String fileId = roomId + "-" + imageId;
    String fileName = fileService.storeFile(file, this.thumbnailImageLocation, fileId);

    ThumbnailImage thumbnailImage =
        this.thumbnailImageRepository.findByFileId(userId).orElse(new ThumbnailImage());
    String filePath = this.thumbnailImageLocation.resolve(fileName).toString();
    thumbnailImage.updateThumbnailImage(fileId, filePath);
    thumbnailImageRepository.save(thumbnailImage);
  }

  public Resource getThumbnailImage(long roomId, long imageId) {
    ThumbnailImage image = this.thumbnailImageRepository.findByFileId(roomId + "-" + imageId)
        .orElseThrow(() -> new FileDownloadException(roomId+"-"+imageId));
    return fileService.loadFile(Paths.get(image.getFilePath()));
  }

  public void deleteThumbnailImage(long roomId, long imageId) {
    ThumbnailImage image = this.thumbnailImageRepository.findByFileId(roomId + "-" + imageId)
        .orElseThrow(() -> new FileDownloadException(roomId+"-"+imageId));
    fileService.deleteFile(image.getFilePath());
    this.thumbnailImageRepository.delete(image);
  }
}

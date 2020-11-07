package com.jukbang.api.file.controller;

import com.jukbang.api.file.service.ThumbnailImageService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rooms/{roomId}/images/{imageId}")
public class ThumbnailImageController {
  private static final Logger logger = LoggerFactory.getLogger(ThumbnailImageController.class);
  private final ThumbnailImageService thumbnailImageService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void uploadThumbnailImage(
      @PathVariable long roomId,
      @PathVariable long imageId,
      @RequestParam("image") MultipartFile image
  ) {
    String requestUserId = SecurityContextHolder.getContext().getAuthentication().getName();
    thumbnailImageService.storeThumbnailImage(requestUserId, roomId, imageId, image);
  }

  @GetMapping
  public ResponseEntity<Resource> downloadThumbnailImage(
      @PathVariable long roomId,
      @PathVariable long imageId,
      HttpServletRequest request
  ) {
    // Load file as Resource
    Resource resource = thumbnailImageService.getThumbnailImage(roomId, imageId);
    String contentType = "application/octet-stream";
    try {
      contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    } catch (IOException ex) {
      logger.info("Could not determine file type.");
    }
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION,
            "attachment; filename=\"" + resource.getFilename() + "\"")
        .body(resource);
  }

  @DeleteMapping
  public void deleteThumbnailImage(
      @PathVariable long roomId,
      @PathVariable long imageId
  ){
    thumbnailImageService.deleteThumbnailImage(roomId,imageId);
  }
}

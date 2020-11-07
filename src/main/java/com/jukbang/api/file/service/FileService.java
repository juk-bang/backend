package com.jukbang.api.file.service;

import com.jukbang.api.file.exception.FileDownloadException;
import com.jukbang.api.file.exception.FileNameException;
import com.jukbang.api.file.exception.FileUploadException;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.StringTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileService {

    public String storeFile(MultipartFile file, Path location, String id) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        StringTokenizer tokens = new StringTokenizer(fileName);
        tokens.nextToken(".");
        String newFileName = id + "." + tokens.nextToken();
        try {
            fileName = checkFileNameAndExtension(file);
            Files.copy(file.getInputStream(), location.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new FileUploadException(fileName, e);
        }
        return newFileName;
    }

    private String checkFileNameAndExtension(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        if (fileName.contains("..")) {
            throw new FileNameException(fileName);
        }
        return fileName;
    }

    public Resource loadFile(Path filePath) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists())
                return resource;
            else
                throw new FileDownloadException(filePath.getFileName().toString());
        } catch (MalformedURLException e) {
            throw new FileDownloadException(filePath.getFileName().toString(), e);
        }
    }

    public Boolean deleteFile(String path){
        File file = new File(path);
        return file.delete();
    }
}

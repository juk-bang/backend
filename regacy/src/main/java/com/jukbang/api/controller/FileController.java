package com.jukbang.api.controller;

import com.jukbang.api.file.response.FileUploadResponse;
import com.jukbang.api.service.FileUploadDownloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.StringTokenizer;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileUploadDownloadService service;

    @CrossOrigin(origins = "*")
    @PostMapping("/manager/manageroom/uploadimg/{Univid}/{roomid}/{no}")
    public String uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("Univid") int Univid, @PathVariable("roomid") int roomid, @PathVariable("no") int no) {
        String filename = "image" + Univid + "-" + roomid + "-" + no;

        String fileName = service.storeFile(file, filename);
        StringTokenizer tockens = new StringTokenizer(fileName);
        tockens.nextToken(".");
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/roomimg/" + Univid)
                .path("/" + roomid + "/" + no + "." + tockens.nextToken("."))
                .toUriString();
        FileUploadResponse a = new FileUploadResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
        return "/ext/" + Univid + "/" + roomid + "/" + no + "/" + fileName;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ext/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("filename") String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
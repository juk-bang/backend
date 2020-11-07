package com.jukbang.api.file.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "imageId")
public class ThumbnailImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    private String fileId;
    private String filePath;

    public ThumbnailImage(String fileId, String filePath) {
        this.fileId = fileId;
        this.filePath = filePath;
    }

    public void updateThumbnailImage(String fileId, String filePath) {
        this.fileId = fileId;
        this.filePath = filePath;
    }
}
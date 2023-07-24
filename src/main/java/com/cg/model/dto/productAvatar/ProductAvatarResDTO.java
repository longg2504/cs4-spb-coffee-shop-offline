package com.cg.model.dto.productAvatar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductAvatarResDTO {

    private String id;
    private String fileName;
    private String fileFolder;
    private String fileUrl;
     private String fileType;

    private String cloudId;

    public ProductAvatarResDTO toProductAvatarResDTO() {
        return new ProductAvatarResDTO()
                .setId(id)
                .setFileName(fileName)
                .setFileFolder(fileFolder)
                .setFileUrl(fileUrl)
                .setFileType(fileType)
                .setCloudId(cloudId)
                ;
    }
}

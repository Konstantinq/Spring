package org.example.web.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;


public class UploadFile{
   @NotBlank
   private String filename;

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }
}

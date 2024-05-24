package az.news.presslab.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + File.separator + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());
            
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/download/")
                    .path(fileName)
                    .toUriString();
            return fileDownloadUri;
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        try{
            Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()){
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename() +"\"")
                        .body(resource);
            }else {
                throw new RuntimeException("File not found "+fileName);
            }
        }catch (MalformedURLException ex){
            throw new RuntimeException("File not found "+fileName,ex);
        }catch (IOException ex) {
            throw new RuntimeException("Could not determine file type. ");
        }
    }

}

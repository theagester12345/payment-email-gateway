package com.project.paymentemailgateway.download;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/download")
public class DownloadController {
    @GetMapping("{id}")
    public ResponseEntity<String> getFileUrl(@PathVariable String id) throws IOException {
        Path file = Paths.get("src\\main\\resources\\public\\pdf", id + ".pdf");
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                ResponseEntity<String> test = ResponseEntity.ok(resource.getURI().toString());
                return null;
            } else {
                throw new RuntimeException("Could not read file: " + id);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error:" + e);
        }
    }
}

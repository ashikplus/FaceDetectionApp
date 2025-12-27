package com.ashik.FaceDetectApp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ashik.FaceDetectApp.service.FaceDetectionService;

import java.io.File;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/face")
@CrossOrigin
public class FaceDetectionController {

    private final FaceDetectionService service;
    private static final AtomicInteger TOTAL_FACE_COUNT = new AtomicInteger(0);

    public FaceDetectionController(FaceDetectionService service) {
        this.service = service;
    }

    @PostMapping("/detect")
    public ResponseEntity<?> detect(@RequestParam("image") MultipartFile file)
            throws Exception {

        // uploads folder
        File uploadDir = new File(System.getProperty("user.dir"), "uploads");
        uploadDir.mkdirs();

        // temp file
        File tempFile = File.createTempFile("face_", ".jpg");
        file.transferTo(tempFile);

        int detectedFaces = service.detectFaces(tempFile);

        // reject image if no face
        if (detectedFaces == 0) {
            tempFile.delete();
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "No human face detected in the image"));
        }

        // save valid image
        String filename = System.currentTimeMillis() + ".jpg";
        File savedFile = new File(uploadDir, filename);
        tempFile.renameTo(savedFile);

        int total = TOTAL_FACE_COUNT.addAndGet(detectedFaces);

        return ResponseEntity.ok(
                Map.of(
                        "facesInImage", detectedFaces,
                        "totalFaces", total,
                        "savedAs", filename,
                        "message", "Human face detected successfully"
                )
        );
    }
}

package com.ashik.FaceDetectApp.service;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FaceDetectionService {

    private final CascadeClassifier faceDetector;

    public FaceDetectionService() {
        String path = "src/main/resources/haarcascade/haarcascade_frontalface_default.xml";
        faceDetector = new CascadeClassifier(path);
    }

    public int detectFaces(File imageFile) {

        Mat image = Imgcodecs.imread(imageFile.getAbsolutePath());
        if (image.empty()) return 0;

        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(gray, gray);

        MatOfRect faces = new MatOfRect();
        faceDetector.detectMultiScale(
                gray,
                faces,
                1.1,
                6,
                0,
                new Size(80, 80),
                new Size()
        );

        return faces.toArray().length;
    }
}

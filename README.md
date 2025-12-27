# 🧠 Face Detection Web Application (Spring Boot + OpenCV)

A simple **Face Detection Web Application** built using **Java Spring Boot** and **OpenCV (Haar Cascade)**.  
The application accepts an image, detects human faces, and returns a meaningful response indicating whether a face was detected or not.

---

## 🚀 Features

- Detect human faces from uploaded images
- REST API based backend
- Uses OpenCV Haar Cascade classifier
- Saves images only when a face is detected
- Returns clear success / failure messages
- Beginner-friendly project structure

---

## 🛠️ Tech Stack

**Backend**
- Java 17+
- Spring Boot
- OpenCV (Java)
- REST API
- Embedded Tomcat

**Frontend**
- HTML
- JavaScript (Fetch API)
---

## 🔍 How Face Detection Works

1. User uploads an image
2. Image is converted to grayscale
3. Haar Cascade classifier scans facial features
4. If face is detected:
   - Image is saved
   - Face count is returned
5. If no face is detected:
   - Image is rejected
   - Informative message is returned

---

## 📡 API Endpoint

### POST `/detect`

**Request**  
multipart/form-data  
key: image  
value: image file  

### ✔ Success Response (200 OK)

```json
{
  "success": true,
  "message": "Human face detected successfully",
  "facesInImage": 1,
  "savedAs": "1703501234567.jpg"
}
❌ Failure Response (400 Bad Request)

{
  "success": false,
  "message": "No human face detected in the image"
}

# 🚀 AWS Certification Portal V2

A full-stack web application that centralizes and streamlines the AWS certification journey — enabling users to discover exams, register, apply vouchers, track exam history, and manage certifications in one unified platform.

---

## 📖 Project Overview

The **AWS Certification Portal V2** eliminates the need for multiple platforms by providing a single, centralized system for managing AWS certification activities.

Users can:

* Register and securely log in
* Browse and search AWS certification exams
* Enroll in exams (online or offline mode)
* Apply discount vouchers
* Track scheduled exams
* View exam history
* Access achieved certifications
* Manage personal profile information

This project demonstrates real-world full-stack development using modern web technologies and backend architecture principles.

---

## 🏷️ AWS Certification Categories

The portal supports all four AWS certification levels:

1. **Foundational**
2. **Associate**
3. **Professional**
4. **Specialty**

Each category contains multiple exam titles with unique exam codes, active dates, and expiration dates.

---

## 🧩 Key Features

### 👤 User Management

* User Registration
* Secure Login
* Profile Management (Update personal details)

### 🔍 Exam Search & Discovery

* Search exams by:

  * Exam Name
  * Exam Code
* View:

  * Certification Category
  * Active Exam Date
  * Expiration Date
  * Exam Description

### 📝 Exam Registration Workflow

1. Select Exam
2. Choose Exam Mode:

   * Offline Test Center
   * Online (Remote)
3. Select:

   * Preferred Language
   * Date
   * Time Slot
4. Apply Voucher (Optional)
5. Complete Payment

### 🎟 Voucher System

* Apply available vouchers during checkout
* Automatic discount calculation

### 📊 Certification Tracking

* Exam History (Past & Upcoming)
* Achieved Certifications
* Profile Management Dashboard

---

## 🏗️ Tech Stack

### Frontend

* HTML5
* CSS3
* JavaScript (ES6+)
* React.js

### Backend

* Java
* Spring Boot
* RESTful APIs

### Architecture

```
React Frontend
      ↓
Spring Boot REST APIs
      ↓
Database (User, Exam, Voucher, Certification Data)
```

---

## 📂 Project Structure

```
AWS-Certification-Portal-V2
│
├── frontend/
│   ├── src/
│   ├── public/
│   └── package.json
│
├── backend/
│   ├── src/main/java/
│   ├── src/main/resources/
│   └── pom.xml
│
└── README.md
```

---

## ⚙️ Installation & Setup

### 🔹 Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

### 🔹 Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs on:

```
http://localhost:3000
```

---

## 🔐 Core Functional Modules

* Authentication & Authorization
* Exam Management
* Voucher Management
* Payment Integration Flow
* Certification Tracking
* User Profile Management

---

## 📈 Future Enhancements

* Email notifications for exam reminders
* Certificate PDF download
* Admin dashboard
* Role-based access control (Admin/User)
* Dashboard analytics & reporting
* Cloud deployment (AWS EC2 / S3 / RDS)
* CI/CD pipeline integration

---

## 📜 License

This project is built for educational and demonstration purposes.

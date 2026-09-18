# Campus Lost & Found Matcher

## 📌 Project Description

Campus Lost & Found Matcher is a Java-based console application designed to help students report, search, and recover lost items within a campus.

The system allows users to register and log in, report lost or found items, search reported items, find possible matches, and submit claims. An admin panel is included to review reports and claims and update item and claim status.

The project uses Java file handling to store user, item, and claim information locally, making it simple to run without an external database.

## ✨ Features

- User registration and login
- Admin login
- Report lost items
- Report found items
- View lost and found reports
- Search items by keyword
- Find possible lost/found matches
- Submit claims for found items
- Admin claim management
- Admin item status management
- File-based data storage
- Console-based user interface

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming
- File Handling
- Exception Handling
- Array and String Processing
- Console-based Interface

## 📂 Project Structure

```text
Campus-Lost-Found-Matcher/
│
├── src/
│   ├── Main.java
│   ├── User.java
│   ├── Admin.java
│   ├── LostItem.java
│   ├── FoundItem.java
│   ├── Claim.java
│   ├── MatchingService.java
│   ├── SearchService.java
│   └── ItemStatusManager.java
│
├── data/
│   ├── users.txt
│   ├── lost_items.txt
│   ├── found_items.txt
│   └── claims.txt
│
└── README.md
▶️ How to Run
Requirements
Java JDK 8 or later
Any Java IDE or terminal
Run using Terminal

Open the project folder and compile the Java files:

javac -d out src/*.java

Then run:

java -cp out Main
🔐 Admin Login
Email: admin@campus.com
Password: admin123
👤 User Workflow
Register
   ↓
Login
   ↓
Report Lost / Found Item
   ↓
Search Items
   ↓
Find Possible Matches
   ↓
Submit Claim
   ↓
Admin Review
   ↓
Item Status Update
🎯 Objective

The main objective of this project is to provide a simple and organized digital solution for managing lost and found items on a college campus.

🚀 Future Improvements
Graphical user interface
Database integration
Email notifications
Image upload for items
Advanced matching algorithm
Mobile application support
👨‍💻 Project Type

Java Console Application

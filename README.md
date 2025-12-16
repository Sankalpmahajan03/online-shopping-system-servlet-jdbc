# 🛒 Online Shopping System (Java Servlets & JDBC)

## 📌 Project Description

The **Online Shopping System** is a Java-based web application that simulates the core functionality of an e-commerce platform. It is developed using **Java Servlets** for request handling and **JDBC** for database interaction with **MySQL**.

The application allows users to log in, browse products by category, manage a session-based shopping cart, place orders, and view transaction history. The project follows a **layered architecture** using the **DAO pattern** and basic **MVC principles**, making it easy to understand, maintain, and extend.

This project is built for **learning and academic purposes** to gain hands-on experience with Java web development.

---

## 🚀 Features

* User authentication using Servlets and HTTP sessions
* Category-wise product listing from MySQL database
* Session-based shopping cart (add, view, remove items)
* Order placement and transaction processing
* View order and transaction history
* Secure database operations using `PreparedStatement`

---

## 🛠 Tech Stack

* **Programming Language:** Java
* **Backend:** Java Servlets, JDBC
* **Frontend:** HTML, JSP (basic UI handling)
* **Database:** MySQL
* **Server:** Apache Tomcat
* **Tools & IDE:** Eclipse, Git, GitHub

---

## 🏗 Application Architecture

* **Model:** Java entity classes (User, Product, Category, Order, Transaction, etc.)
* **DAO Layer:** Handles all database operations using JDBC
* **Controller:** Servlets for request handling and business logic
* **View:** JSP/HTML for displaying data

Design patterns used:

* DAO (Data Access Object)
* MVC (Model–View–Controller – basic implementation)

---

## 📂 Project Structure

```
OnlineShoppingSystem/
│
├── src/
│   ├── controller/        # Servlets (Controllers)
│   ├── dao/               # DAO classes for DB operations
│   ├── model/             # Entity / Bean classes
│   └── util/              # Database connection utility
│
├── webapp/
│   ├── jsp/               # JSP pages
│   ├── css/               # Stylesheets
│   └── images/            # Images
│
├── lib/
│   └── mysql-connector-j.jar
│
├── web.xml
└── README.md
```
---

## 🗄 Database Tables 

* users
* categories
* products
* orders
* order_items
* transactions

---

## 🎯 Learning Outcomes

* Understanding of **Servlet lifecycle and session management**
* Hands-on experience with **JDBC and MySQL integration**
* Implementation of **DAO pattern** in real applications
* Practical exposure to **Java web application architecture**

---

## 🔮 Future Enhancements

* Password encryption and role-based access control
* JSP-based UI improvements using Bootstrap
* Payment gateway integration
* Product search and filtering
* Connection pooling using `DataSource`

---

## ⚠️ Disclaimer

This project is created **for educational purposes only** and does not implement real-world security standards for payment systems.

---

## 📜 License

This project is open for learning and academic use.
---
💖 Contribute & Support

If you enjoyed this project or found it helpful:

⭐ Star this repository on GitHub

🍴 Fork and experiment with the project

💬 Share feedback or suggest improvements

❤️ Spread the word to help others learn!

Every ⭐ and feedback motivates me to build more awesome projects! 🚀
---

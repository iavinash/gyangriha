# **Gyan-Griha**(Student Subscription Management System)

This project is a comprehensive **Student Subscription Management System** built using **Spring Boot** and **ReactJS**. It allows users to manage subscriptions, attendance, and profiles while providing an admin dashboard for advanced management and monitoring functionalities.

---

## **Features**

### **User Management**
- **User Registration**
- **User Login**: 
  - Secure JWT-based authentication and session management.
- **User Dashboard**: 
  - View subscription details: Type, Amount Paid, Remaining Duration.
  - Attendance history.

### **Subscription Management**
- Configurable subscription plans stored in the database:
- **Subscription Purchase**:
  - Integrated payment gateway.
  - Receipt generation and email confirmation.
- **Subscription Renewal**:
  - Automatic reminders and manual renewal options.
- **Subscription Cancellation**:
  - User-initiated or admin-initiated cancellations.

### **Attendance Management**
- Attendance marking
- Real-time attendance status.
- Admin dashboard for viewing, analyzing, and exporting attendance data.

### **Admin Dashboard**
- **User Search**:
  - By mobile number, name, email, or ID Proof number.
- **User Management**:
  - Paginated table of subscriptions sorted by expiration.
  - Update or delete user details
  - Send reminders (SMS/Email) for subscription renewals.
- **Attendance Management**:
  - Mark and analyze attendance data.
  - Export attendance reports.

---

## **Tech Stack**

### **Frontend**
- **ReactJS**: Dynamic and interactive user interface.

### **Backend**
- **Spring Boot**: REST API implementation and business logic.
- **Security**: 
  - JWT for authentication and authorization.
  - Password hashing and secure input handling.

### **Database**
- **PostgreSQL/MySQL**: Configurable relational database.
- **Audit Logging**: Tracks updates and deletions for user and subscription data.

---

## **Installation and Setup**

### **Prerequisites**
1. **Java 17+**
2. **Node.js 18+**
3. **Maven 3.6+**
4. **PostgreSQL/MySQL**

### **Backend Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/student-subscription-management.git
   cd student-subscription-management/backend
   ```
2. Configure the `application.properties` file for your database:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   ```
3. Build and run the Spring Boot application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### **Frontend Setup**
1. Navigate to the frontend directory:
   ```bash
   cd ../frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the React application:
   ```bash
   npm start
   ```
4. Open the app in your browser at `http://localhost:3000`.

---

## **API Endpoints**

### **Auth APIs**
| Method | Endpoint             | Description               |
|--------|-----------------------|---------------------------|
| POST   | `/api/auth/register` | Register a new user       |
| POST   | `/api/auth/login`    | Login and generate a JWT  |

### **User APIs**
| Method | Endpoint                       | Description                    |
|--------|---------------------------------|--------------------------------|
| GET    | `/api/users/dashboard`         | Get user subscription details |
| PUT    | `/api/users/profile`           | Update user profile           |

### **Admin APIs**
| Method | Endpoint                             | Description                                   |
|--------|-------------------------------------|----------------------------------------------|
| GET    | `/api/admin/users`                  | Get all users (paginated)                   |
| GET    | `/api/admin/users/search`           | Search users by mobile/email/ID proof        |
| PUT    | `/api/admin/users/{id}/subscription`| Update user subscription                    |
| POST   | `/api/admin/users/{id}/attendance`  | Mark user attendance                        |

---

## **Security**
- **JWT Authentication**:
  - Token generation during login.
  - Token validation for protected routes.
- **Password Encryption**:
  - Passwords are hashed and securely stored.
- **Input Validation**:
  - All inputs are sanitized to prevent injection attacks.

---

## **Development Notes**
### **Configuration**
- Subscription plans and discount coupons are fully configurable in the database.

### **Extensibility**
- Easily extendable for mobile apps or additional subscription plans.
- Designed for scalability and performance with growing user loads.

---

## **Testing**

1. Run the test suite:
   ```bash
   mvn test
   ```
2. Includes test cases for:
   - User registration and login.
   - JWT authentication and protected routes.
   - Subscription management and reminders.

---

## **Contributions**
Contributions are welcome! Feel free to fork the repository and submit a pull request.

---

## **License**
This project is licensed under the MIT License. See the `LICENSE` file for more details.

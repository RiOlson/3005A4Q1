# 3005A4Q1
DBMS Assignment 4

## Database Setup
Create a new database.

Run [studentTable](https://github.com/RiOlson/3005A4Q1/blob/main/Database%20Scripts/studentTable.sql) to create the students table.
Run [initData](https://github.com/RiOlson/3005A4Q1/blob/main/Database%20Scripts/initData.sql) to populate the table with initial data.

## Compiling and Running
In the [StudentDatabase java file](https://github.com/RiOlson/3005A4Q1/blob/main/src/StudentDatabase.java), change the following code so the url matches the name of your database, and change user to be your own username and password to be your own password.

```java 
private static final String url = "jdbc:postgresql://localhost:5432/A4Q1";
private static final String user = "USERNAME";
private static final String password = "PASSWORD";
```
Before compiling, you must have the PostgreSQL JDBC driver installed.
Ensure the database is running for the program to function correctly.

### Running program in IDE
If running in an IDE, add the .jar file as an external library module.

Run the program in the IDE, and the program should execute immediately.

### Running program in terminal (Windows)
First find and copy the path to the JDBC .jar file.

Second locate and enter the directory where the .java file is stored.

When running, you will be given a menu of actions listed 1-5 to choose from.

### Compile
```bash
javac -cp "path/to/JDBC/jar" StudentDatabase.java
```

### Run
```bash
java -cp ".;path/to/JDBC/jar" StudentDatabase
```
## Program Functions
### getAllStudents()
Retrieves and outputs data of all students in the database.

### addStudent(first_name, last_name, email, enrollment_date)
Adds a student to the database.

### updateStudentEmail(student_id, new_email)
Changes the e-mail of a given student by their student ID.

### deleteStudent(student_id)
Removes a student from the database.

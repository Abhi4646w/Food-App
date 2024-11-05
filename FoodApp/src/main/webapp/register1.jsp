<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - Food Delivery App</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(120deg, #87CEEB, #ffffff); /* Gradient background */
            color: #333;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        h1 {
            color: #4682b4; /* Consistent header color */
            margin-top: 30px; /* Space above header */
        }
        form {
            background-color: rgba(255, 255, 255, 0.95); /* Slightly transparent white */
            padding: 20px;
            border-radius: 10px; /* Consistent border radius */
            display: inline-block;
            margin-top: 50px; /* Space above form */
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* Subtle shadow */
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input {
            padding: 10px;
            width: 75%;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            padding: 10px 20px;
            background-color: #4682b4; /* Consistent button color */
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        button:hover {
            background-color: #4169e1; /* Darker shade on hover */
        }
        p {
            margin-top: 20px;
            color: #333; /* Dark text for readability */
        }
        a {
            color: #4682b4; /* Consistent link color */
        }
    </style>
</head>
<body>
    <h1>Account Credentials Not Found. Please Register</h1>
    <form action="InsertData" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required>
        
        <label for="mobile">Mobile Number:</label>
        <input type="tel" id="mobile" name="mobile" required>

        <button type="submit">Register</button>
    </form>

    <h3><p>Already have an account? <a href="login.html">Login here</a></p></h3>
</body>
</html>
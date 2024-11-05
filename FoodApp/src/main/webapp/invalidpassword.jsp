<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Food Delivery App</title>
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
            margin-top: 30px;
            color: #4682b4; /* Consistent header color */
        }
        form {
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
            display: inline-block;
            margin-top: 50px;
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
    <h1>Sorry, your password was incorrect. Please login again.</h1>
    <form action="Login" method="POST">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Login</button>
        <p class="forgot-password"><a href="resetpassword.html">Forgotten Password?</a></p>
    </form>

    <h3><p>Don't have an account? <a href="register.html">Register here</a></p></h3>
</body>
</html>
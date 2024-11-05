<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Placed - Festo</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(120deg, #87CEEB, #ffffff); /* Gradient background matching previous pages */
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 90%;
            max-width: 600px;
            margin: 100px auto; /* Centered container */
            background-color: rgba(255, 255, 255, 0.95);
            padding: 20px;
            border-radius: 10px; /* Consistent border radius */
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        h1 {
            color: #4682b4; /* Consistent header color */
        }
        p {
            font-size: 18px;
            margin-top: 20px;
            color: #333;
        }
        a {
            color: #4682b4; /* Consistent link color */
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
    <% int oid = (int) session.getAttribute("OrderId"); %>
        <h1>Thank You!</h1>
        <p>Your order has been placed successfully.</p>
        <p>Order ID: <strong><%= oid %></strong></p>
        <p>We hope you enjoy your meal!</p>
       <h3><p><a href="home1.jsp">Back to Home</a></p></h3>
    </div>
</body>
</html>
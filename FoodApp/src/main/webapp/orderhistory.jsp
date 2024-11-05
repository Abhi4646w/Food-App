<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodapp.model.User, java.util.List, com.foodapp.model.Orders, com.foodapp.dao.RestaurantDAO,
com.foodapp.daoimpl.RestaurantDAOimpl, com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order History - Festo</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #87CEEB, #ffffff); /* Gradient background */
            color: #333;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 10px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2);
            position: sticky;
            top: 0;
            z-index: 1000;
        }
        .header img {
            height: 50px;
            margin-right: 10px;
        }
        .header h1 {
            font-size: 24px;
            margin: 0;
            color: #4682b4; /* Consistent header color */
        }
        .nav-links {
            display: flex;
            align-items: center;
        }
        .nav-links a {
            margin: 0 15px;
            color: #4682b4; /* Consistent link color */
            text-decoration: none;
        }
        .order-list {
            margin: 20px;
            padding: 20px;
            background-color: rgba(255, 255, 255, 0.95);
            border-radius: 10px; /* Consistent border radius */
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2);
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #4682b4; /* Consistent header color */
            color: white; /* White text for header */
        }
        tr:hover {background-color: #f1f1f1;}
        .view-detail {
            background-color: #4682b4; /* Button color */
            color: white; 
            border: none; 
            padding: 5px 10px; 
            border-radius: 5px; 
            cursor: pointer; 
        }
        .view-detail:hover {
            background-color: #5a9bd4; /* Hover effect */
        }
    </style>
</head>
<body>
<% User u = (User) session.getAttribute("object"); %>
<% String pass = (String)session.getAttribute("password");%>
<% if (u != null || u.getPassword().equals(pass)) { %>
    <div class="header">
        <div class="logo">
            <img src="https://i.postimg.cc/kGy7NRFM/pixelcut-export-2.jpg" alt="Festo Logo">
        </div>
        <div class="nav-links">
            <p>Hello, <%= u.getUsername() %>!</p>
            <a href="cart.jsp">Cart</a>
            <a href="home1.jsp">Home</a>
            <a href="LogoutServlet">Logout</a>
        </div>
    </div>

    <div class="order-list">
        <h2>Your Order History</h2>
        <% List<Orders> orderList = (List<Orders>) session.getAttribute("orderHistory"); %>
        <% if (orderList != null && !orderList.isEmpty()) { %>
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Restaurant Name</th>
                        <th>Price</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Orders order : orderList) { %>
                        <tr>
                        <%
                        RestaurantDAO r = new RestaurantDAOimpl();
                        Restaurant res = r.getRestById(order.getRestaurantId());
                        %>
                            <td><%= order.getOrderId() %></td>
                            <td><%= order.getDate() %></td>
                            <td><%= res.getName() %></td>
                            <td>$<%= order.getTotalAmount() %></td>
                            <td><%= order.getStatus() %></td>
                            <td>
                                <form action="ShowItems" method="POST">
                                    <input type="hidden" name="orderId" value="<%= order.getOrderId() %>">
                                    <button type="submit" class="view-detail">View Detail</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No orders found.</p>
        <% } %>
    </div>

<% } else { %>

    <!-- If user is not logged in -->
    <div class="header">
        <div class="logo">
            <img src="https://i.postimg.cc/kGy7NRFM/pixelcut-export-2.jpg" alt="Festo Logo">
        </div>
        <div class="nav-links">
             <a href="login.html">Login</a> 
             <a href="register.html">Register</a> 
         </div> 
    </div>

    <!-- Message for not logged in users -->
    <div class="order-list">
         <h2>Please log in to view your order history.</h2> 
     </div>

<% } %>

</body>  
</html>  
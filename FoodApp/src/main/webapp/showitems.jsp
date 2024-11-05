<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodapp.model.Orders, com.foodapp.model.OrderItems, java.util.List, com.foodapp.dao.OrdersDAO,
com.foodapp.daoimpl.OrdersDAOimpl, com.foodapp.daoimpl.OrderItemsDAOimpl, com.foodapp.dao.OrderItemsDAO,
com.foodapp.model.Menu, com.foodapp.daoimpl.MenuDAOimpl, com.foodapp.dao.MenuDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Details - Festo</title>
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
        }
        .header h1 {
            font-size: 24px;
            margin: 0;
            color: #4682b4; /* Consistent header color */
        }
        .order-detail {
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
    </style>
</head>
<body>

<% 
    List<OrderItems> itemslist = (List<OrderItems>)session.getAttribute("itemsList");
    int orderId = Integer.parseInt(request.getParameter("orderId"));
%>

<div class="header">
    <h1>Order Details</h1>
</div>

<div class="order-detail">
    <% if (itemslist != null) { %>
        <h2>Order ID:<%= orderId %> </h2>
        <h3>Ordered Items:</h3>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <% double totalamount = 0; %>
                <% for (OrderItems item : itemslist) { %>
                    <tr>
                        <%
                        MenuDAO m = new MenuDAOimpl();
                        Menu menu = m.getMenuById(item.getMenuId());
                        %>
                        <td><%= menu.getItemName() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>$<%= item.getSubTotal() %></td>
                        <% totalamount += item.getSubTotal(); %>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <h3>Total Amount: $<%= totalamount %></h3>
    <% } else { %>
        <p>Order not found.</p>
    <% } %>
</div>

</body>
</html> 
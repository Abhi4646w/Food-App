<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodapp.daoimpl.Cart, com.foodapp.model.CartItem" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - Festo</title>
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
        .cart-container {
            width: 90%;
            max-width: 1200px; /* Centered container */
            margin: 30px auto; 
            background-color: rgba(255, 255, 255, 0.95);
            padding: 20px;
            border-radius: 10px; /* Consistent border radius */
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1); /* Deeper shadow for modern look */
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            text-align: center;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4682b4; /* Consistent header color */
            color: white; /* White text for header */
        }
        tr:hover {background-color: #f1f1f1;}
        
        /* Card style for items */
        .item-card {
            padding: 10px; 
            border-radius: 10px; 
            box-shadow: 0px 2px 5px rgba(0,0,0,.1); 
            margin-bottom: 15px; /* Space between items */
        }

        .quantity-input {
            width: 60px; 
            padding: 5px; 
            border-radius: 5px; 
        }

        .action-buttons button {
            padding: 8px; 
            margin-left:5px; 
           border:none; 
           border-radius :5px ; 
           cursor:pointer ; 
           color:white; 
       }

       .update-button {
           background-color:#4CAF50; /* Green */
       }

       .delete-button {
           background-color:#f44336; /* Red */
       }

       .total-price {
           text-align:right; 
           font-size :20px; 
           font-weight :bold; 
       }

       .checkout-button {
           padding :10px ; 
           margin-top :10px ; 
           border:none ; 
           border-radius :5px ; 
           cursor:pointer ; 
           color:white ; 
           font-size :16px ; 
           background-color :#4682b4 ; /* Consistent button color */
       }

       .empty-cart {
           text-align:center; 
           font-size :24px; 
           color :#ff6347; 
           margin-top :50px; 
       }

       @media (max-width :768px) {
           .header, .cart-container {
               flex-direction :column; 
               align-items:flex-start; 
           }
           .nav-links {
               flex-direction :column; 
               align-items:flex-start; 
           }
           table, th, td {
               font-size :14px; 
           }
       }
    </style>
</head>
<body>
    <% 
        // Retrieve the user from the session
        com.foodapp.model.User user = (com.foodapp.model.User) session.getAttribute("object");
    %>
    <div class="header">
        <div class="logo">
            <img src="https://i.postimg.cc/kGy7NRFM/pixelcut-export-2.jpg" alt="Festo Logo">
        </div>
        <h1>Festo Shopping Cart</h1>
        <div class="nav-links">
             <% if (user != null) { %>
                <p>Hello, <%= user.getUsername() %>!</p>
               
                <a href="OrderHistory">Order History</a>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <a href="resetpassword.html">Change Password</a>
                        <a href="LogoutServlet">Logout</a>
                    </div>
                </div>
             <% } else { %>
                <a href="login.html">Login</a>
                <a href="register.html">Register</a>
             <% } %>
         </div>
    </div>

   <div class="cart-container">
    <% 
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null && !cart.getItems().isEmpty()) { 
    %>
        <table>
            <thead>
                <tr>
                    <th>Item Name</th>
                    <th>Price ($)</th>
                    <th>Quantity</th>
                    <th>Subtotal ($)</th>
                    <th>Actions</th>
                </tr>
             </thead>  
             <tbody>  
                <% 
                    double total = 0.0;
                    for (CartItem item : cart.getItems().values()) { 
                        double subtotal = item.getPrice() * item.getQuantity();
                        total += subtotal;
                %>
                    <tr class="item-card">
                        <form action="AddToCartServlet" method="POST">
                            <td><%= item.getName() %></td>
                            <td><%= String.format("%.2f", item.getPrice()) %></td>
                            <td><input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" class="quantity-input"></td>
                            <td><%= String.format("%.2f", subtotal) %></td>
                            <td class="action-buttons">
                                <input type="hidden" name="menuId" value="<%= item.getItemId() %>">
                                <button type="submit" name="action" value="update" class="update-button">Update</button>
                                <button type="submit" name="action" value="delete" class="delete-button">Delete</button>
                            </td>
                        </form>  
                    </tr>  
                <% } %>  
             </tbody>  
         </table>

         <!-- Total Price Display -->
         <div class="total-price">Total: $<%= String.format("%.2f", total) %></div>

         <!-- Checkout Button -->
         <div>
         <a href="checkout.jsp"><button class="checkout-button">Proceed to Checkout</button></a>
         <a href="menu.jsp"><button class="checkout-button">Add Another Item</button></a>
         </div>

    <% } else { %>  
         <!-- Empty Cart Message -->
         <div class="empty-cart">
             Your cart is currently empty.
             <!-- Link to browse restaurants -->
             <a href="home1.jsp"><button class="continue-button">Browse Restaurants</button></a>  
         </div>  
    <% } %>  
   </div>

</body>  
</html>  
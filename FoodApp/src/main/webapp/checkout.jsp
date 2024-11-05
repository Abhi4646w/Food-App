<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.foodapp.daoimpl.Cart, com.foodapp.model.CartItem, com.foodapp.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout - Festo</title>
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
        .checkout-container {
            width: 90%;
            max-width: 600px; /* Centered container */
            margin: 30px auto; 
            background-color: rgba(255, 255, 255, 0.95);
            padding: 20px;
            border-radius: 10px; /* Consistent border radius */
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1); /* Deeper shadow for modern look */
        }
        .checkout-container h2 {
            text-align: center; 
            color: #4682b4; /* Consistent heading color */
        }
        form {
            display: flex; 
            flex-direction: column; 
        }
        label {
            margin-top: 15px; 
            font-weight: bold; 
        }
        input[type="text"], textarea {
            padding: 10px; 
            margin-top: 5px; 
            border: 1px solid #ccc; 
            border-radius: 5px; 
            resize: vertical; 
        }
        .payment-method {
            margin-top: 10px; 
        }
        .payment-method input {
            margin-right: 10px; 
        }
        .confirm-button {
           padding :10px ; 
           margin-top :20px ; 
           border:none ; 
           border-radius :5px ; 
           background-color :#4682b4 ; /* Consistent button color */
           color:white ; 
           cursor:pointer ; 
           font-size :16px ; 
           transition :background-color .3s ease;
       }
       .confirm-button:hover {
           background-color :#315f7b ; /* Darker shade on hover */
       }
       .total-amount {
           text-align:right; 
           font-size :20px; 
           font-weight :bold; 
           margin-top :10px; 
       }

       @media (max-width :768px) {
           .header, .checkout-container {
               flex-direction :column; 
               align-items:flex-start; 
           }
           .nav-links {
               flex-direction :column; 
               align-items:flex-start; 
           }
           .nav-links a {
               margin :5px 0; 
           }
           .confirm-button {
               width :100%; /* Full width button on small screens */
           }
       }
    </style>
</head>
<body>
    <% 
        // Retrieve the user from the session
        User user = (User) session.getAttribute("object");
        
        // Retrieve the cart from the session
        Cart cart = (Cart) session.getAttribute("cart");
        
        // Calculate the total amount
        double totalAmount = 0.0;
        if (cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }   
        }

        // Generate CSRF token if not present
        String csrfToken = (String) session.getAttribute("csrfToken");
        if (csrfToken == null) {
            csrfToken = java.util.UUID.randomUUID().toString();
            session.setAttribute("csrfToken", csrfToken);
        }
    %>
    
    <div class="header">
        <div class="logo">
            <img src="https://i.postimg.cc/kGy7NRFM/pixelcut-export-2.jpg" alt="Festo Logo">
        </div>
        <h1>Festo Checkout</h1>
        <div class="nav-links">
             <% if (user != null) { %>
                <p>Hello, <%= user.getUsername() %>!</p>
                <a href="cart.jsp">Cart</a>
                <a href="OrderHistory">Order History</a>
                <div class="dropdown">
                    <div class="dropdown-content">
                        <a href="resetpassword.html">Change Password</a>
                        <a href="logoutServlet">Logout</a>
                    </div>
                </div>
             <% } else { %>
                <a href="login.jsp">Login</a>
                <a href="register.jsp">Register</a>
             <% } %>
         </div>
    </div>

    <div class="checkout-container">
        <h2>Checkout</h2>
        
        <!-- Total Amount Display -->
        <div class="total-amount">
          Total Amount: $<%= String.format("%.2f", totalAmount) %>
      </div>

      <!-- Checkout Form -->
      <form action="CheckOut" method="POST">
          <!-- CSRF Token -->
          <input type="hidden" name="csrfToken" value="<%= csrfToken %>" />
          
          <label for="address">Shipping Address:</label>
          <textarea id="address" name="address" rows="4" required></textarea>

          <label>Payment Method:</label>
          <div class="payment-method">
              <input type="radio" id="creditCard" name="paymentMethod" value="Credit Card" required>
              <label for="creditCard">Credit Card</label>
          </div>
          <div class="payment-method">
              <input type="radio" id="paypal" name="paymentMethod" value="PayPal" required>
              <label for="paypal">PayPal</label>
          </div>
          <div class="payment-method">
              <input type="radio" id="cod" name="paymentMethod" value="Cash on Delivery" required>
              <label for="cod">Cash on Delivery</label>
          </div>

          <!-- Confirm Payment Button -->
          <button type="submit" class="confirm-button">Confirm Payment</button>
      </form>

    </div>

</body>  
</html>  
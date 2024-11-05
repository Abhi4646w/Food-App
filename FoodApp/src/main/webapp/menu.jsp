<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "com.foodapp.model.Menu, java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu - Festo</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #87CEEB, #ffffff);
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
        .header img {
            height: 50px;
            margin-right: 10px;
        }
        .header h1 {
            font-size: 24px;
            margin: 0;
            color: #4682b4;
        }
        .menu-items {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 20px; 
        }
        .item-card {
            background-color: rgba(255, 255, 255, 0.95);
            width: 240px; /* Decreased width */
            margin: 20px; 
            padding: 15px; 
            border-radius: 10px; 
            box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center; 
            transition: transform 0.3s ease, box-shadow 0.3s ease; 
        }
        .item-card:hover {
            transform: translateY(-5px); 
            box-shadow: 0px 8px 25px rgba(0, 0, 0, 0.2); 
        }
        .item-card img {
            width: 100%; 
            border-radius: 10px; 
            height: auto; 
            max-height: 160px; 
            object-fit: cover; 
        }
        .item-info-row {
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
            margin-top: 10px; 
        }
        .item-info-row h3 {
            color: #333; 
            margin: 0; 
            font-size: 18px; 
        }
        .item-info-row .price {
            font-weight: bold; 
            color: #ff6347; 
        }
        .quantity-rating {
            display: flex; 
            justify-content: flex-start; 
            align-items: center; 
        }
        .quantity-rating label {
            margin-right: 5px; 
        }
        .quantity-rating input[type="number"] {
            width: 55px; 
            padding: 4px; 
            border: 1px solid #ccc; 
            border-radius: 5px; 
        }
        .add-to-cart {
           background-color:#4682b4; 
           color:white; 
           padding :10px ; 
           border :none ; 
           border-radius :5px ; 
           cursor:pointer ; 
           transition :background-color .3s ease;
       }
       .add-to-cart:hover {
           background-color :#315f7d ; 
       }
       .availability-container {
           display:flex;
           justify-content: space-between;
           align-items:center;
           margin-top:10px;
       }
       .availability {
           color :green ; 
           font-weight :bold ; 
       }
       .unavailable {
           color:red ; 
           font-weight:bold ; 
       }

       @media (max-width:768px) {
           .item-card {
               width: calc(100% - 40px); /* Responsive full width minus margins */
               margin-left:auto;
               margin-right:auto;
           }
       }
    </style>
</head>
<body>
    <div class="header">
        <div class="logo">
            <img src="https://i.postimg.cc/kGy7NRFM/pixelcut-export-2.jpg" alt="Festo Logo">
        </div>
        <h1>Festo Menu</h1>
    </div>

    <div class="menu-items">
        <% 
            List<Menu> menuItems = (List<Menu>) session.getAttribute("menulist");
            if (menuItems != null && !menuItems.isEmpty()) {
                for (Menu item : menuItems) {
        %>
                <form action="AddToCartServlet" method="POST" class="item-card">
                    <!-- Hidden input to pass the menuId -->
                    <input type="hidden" name="menuId" value="<%= item.getMenuId() %>">
                    
                    <img src="<%= item.getImgPath() %>" alt="<%= item.getItemName() %>">
                    
                    <!-- First Row: Item Name and Price -->
                    <div class="item-info-row">
                        <h3><%= item.getItemName() %></h3>
                        <p class="price"><%= item.getPrice() %></p>
                    </div>
                    
                    <!-- Second Row: Quantity and Ratings -->
                    <div class="item-info-row">
                        <div class="quantity-rating">
                            <label for="quantity-<%= item.getMenuId() %>">Qty:</label>
                            <input type="number" id="quantity-<%= item.getMenuId() %>" name="quantity" min="1" value="1">
                        </div>
                        <p>Rating:<%= item.getRatings() %>/5</p> <!-- Adjusted rating display -->
                    </div>
                    
                    <!-- Third Row: Availability and Add to Cart -->
                    <div class="availability-container">
                        <p class="<%= item.getIsAvailable() == 1 ? "availability" : "unavailable" %>">
                            <%= item.getIsAvailable() == 1 ? "Available" : "Unavailable" %>
                        </p>
                        <button type="submit" class="add-to-cart">Add to Cart</button>
                        <input type = "hidden" name = "action" value = "add">
                    </div>
                </form>
        <% 
                }
             } else { %>
                <p>No menu items available.</p>
         <% } %>
    </div>
</body>
</html>
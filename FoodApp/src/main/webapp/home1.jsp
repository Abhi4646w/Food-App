<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import = "com.foodapp.model.User, java.util.List, com.foodapp.model.Restaurant" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - Festo</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(120deg, #87CEEB, #ffffff); /* Gradient background */
            color: #333;
            margin: 0;
            padding: 0;
        }
        
        /* Header Styles */
        .header {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 10px 20px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            position: sticky;
            top: 0;
            z-index: 1000;
        }
        .header img {
            height: 50px;
            margin-right: 10px;
        }
        
        .user-info {
            display: flex;
            align-items: center;
        }
        .user-info p {
            margin-right: 20px;
            color: #4682b4;
        }

        /* Navigation Links */
        .nav-links {
            display: flex;
            align-items: center;
        }
        .nav-links a {
            margin: 0 15px;
            color: #4682b4;
            text-decoration: none;
            transition: color 0.3s;
        }
        .nav-links a:hover {
            color: violet;
        }

        /* Dropdown for User Profile */
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown img {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            cursor: pointer;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            right: 0;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            padding: 10px;
            border-radius: 5px;
            z-index: 1;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .dropdown-content a {
            color: #4682b4;
            padding: 8px 10px;
            text-decoration: none;
            display: block;
            border-bottom: 1px solid #ccc;
        }
        .dropdown-content a:last-child {
            border-bottom: none;
        }

        /* Restaurant Cards */
        .restaurant-cards {
            display: flex; 
            flex-wrap: wrap; 
            justify-content: center; 
            margin: 20px; 
        }

        .card {
            background-color: #ffffff; 
            width: 300px; 
            margin: 20px; 
            border-radius: 10px; 
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); 
            overflow: hidden; 
            transition: transform 0.3s ease, box-shadow 0.3s ease; 
        }

        .card:hover {
           transform : translateY(-5px) scale(1.02); 
           box-shadow :0 8px rgba(0,0,0,.2); 
       }

       .card img {
           width :100%; 
           height :200px; 
           object-fit :cover; 
       }

       .card-content {
           padding :15px; 
           color:#333; 
       }

       .card h3 {
           font-size :1.5rem; 
           margin-top :10px; 
           color:#4682b4; 
       }

       /* Display values in one line */
       .info-line {
           display:flex; /* Use flexbox to align items */
           justify-content:flex-start; /* Align items to start */
           align-items:center; /* Center vertically */
       }

       /* Star Rating Styles */
       .stars {
           color:#FFD700; /* Gold color for stars */
           margin-right :10px; /* Space between stars and delivery time */
       }

       /* General paragraph styles */
       .card p {
           color:#555; 
           font-size:.9rem; 
           margin :5px ; 
       }
    </style>
</head>
<body>
    <% User u = (User) session.getAttribute("object"); %>
    <% String pass = (String)session.getAttribute("password"); %>
    <% if (u != null && u.getPassword().equals(request.getParameter("password")) || u.getPassword().equals(pass)) { %>
        <div class="header">
             <div class="logo">
                <img src="https://i.ibb.co/zhhqTvv/logo.jpg" alt="Festo Logo">
             </div>
             <div class="nav-links">
                <p>Hello, <%= u.getUsername() %>!</p>
                <a href="cart.jsp">Cart</a>
                <a href="OrderHistory">Order History</a>
                <a href="LogoutServlet">Logout</a>
             </div>
         </div>

         <div class="restaurant-cards">
             <% List<Restaurant> rlist = (List<Restaurant>) session.getAttribute("reslist"); %>
             <% if (rlist != null) { %>
                 <% for (Restaurant r : rlist) { %>
                     <div class="card">
                         <!-- Wrap image in a form to submit on click -->
                         <form action="MenuServlet" method="POST" style="margin-bottom: 0;">
                             <input type="hidden" name="restaurantId" value="<%= r.getRestaurantId() %>">
                             <button type="submit" style="border:none; padding:0; background:none;">
                                 <img src="<%= r.getImgPath() %>" alt="<%= r.getName() %>" style="cursor:pointer;">
                             </button>
                         </form>
                         <div class="card-content">
                             <h3><%= r.getName() %></h3>
                             <!-- Display rating with one star and delivery time -->
                             <div class="info-line">
                                  <!-- Display one star -->
                                 <p><%= r.getRatings() %><span class="stars">&#9733</span> | <%= r.getDeliveryTime() %> mins</p> <!-- Display rating and delivery time -->
                             </div>

                             <!-- Display cuisine type only -->
                             <p><%= r.getCuisiveType() %></p> <!-- Display cuisine type -->
                         </div>
                     </div>
                 <% } %>
             <% } else { %>
                 <p>No restaurants available.</p> <!-- Optional message if no restaurants are available -->
             <% } %>
         </div>

    <% } else { %>
         <!-- Optional message or redirect for users not logged in -->
         <p>Please log in to view this page.</p> <!-- You can redirect to login page instead -->
    <% } %>

</body>
</html>
<%@page import="com.incapp.dao.AdminDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%
 String aname=(String)session.getAttribute("aname");
if(aname==null){
	session.setAttribute("msg","LoginFirst");
	 response.sendRedirect("admin.jsp");
}
else{%>

<!DOCTYPE html>
<html>

<head>
    <title>GarageWorks</title>
    <link rel="icon" href="resource/autoworks-logo.png" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js"></script>

    <!-- fontawesome -->
    <link rel="stylesheet" href="resource/fontawesome/fontawesome.min.css">
    <script src="resource/fontawesome/fontawesome.min.js"></script>
    <!-- fontawesome END -->

    <!-- Lightbox CSS & Script -->
    <script src="resource/lightbox/ekko-lightbox.js"></script>
    <link rel="stylesheet" href="resource/lightbox/ekko-lightbox.css" />

    <!-- AOS CSS & Script -->
    <script src="resource/aos/aos.js"></script>
    <link rel="stylesheet" href="resource/aos/aos.css"/>

    <!-- custom css-->
    <link rel="stylesheet" href="resource/custom.css" />
    <!-- custom css END-->

    <meta name="author" content="Rahul Chauhan" />
    <meta name="description" content="This is a Auto Service website" />
    <meta name="keywords" content="best Auto Service comapny in noida" />
</head>

<body>
         <nav class="navbar navbar-expand-sm bg-light sticky-top px-4">
        <a class="navbar-brand" href="index.jsp">
            Garage<strong class="c-logo">Works</strong>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <i class="fas fa-bars"></i> Menu
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav ml-auto">
                <!-- <ul class="navbar-nav mr-auto"> -->
                <!-- <ul class="navbar-nav mx-auto"> -->
                <li class="nav-item">
                    <a class="nav-link" href="adminHome.jsp">Home</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="adminAcceptedGarage.jsp">Accepted</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="adminHome.jsp">Pending</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="adminRejectedGarage.jsp">Rejected</a>
                </li>
               
            </ul>
            <div>
                Welcome:<b> <%= aname %> </b>
               <a href="logout">LogOut</a>
                 </div>
        </div>
    </nav>
   <section class="container" py-2>
        <% 
          String garageEmail=request.getParameter("email");
           AdminDao d=new AdminDao();
          HashMap garageOwnersdetails=d.getGarageEmail(garageEmail);
          	   %>
        	   <div class="bg-light p-2 m-2">
        	   <h3>Garage Name:<%= garageOwnersdetails.get("gname") %> </h3>
        	   <img src="getGaragePhoto?email=<%=garageEmail %>&photo_no=photo1" height="200px"/>
               <img src="getGaragePhoto?email=<%=garageEmail %>&photo_no=photo2" height="200px"/>
              
        	   <h4>
        	   Name:<b><%= garageOwnersdetails.get("name") %></b>
        	   Email:<b><%= garageOwnersdetails.get("email") %></b>
        	  Phone:<b><%= garageOwnersdetails.get("phone") %></b>
        	 
        	   </h4>
        	   <h4>
        	   State:<b><%= garageOwnersdetails.get("state") %></b>
        	   City:<b><%= garageOwnersdetails.get("city") %></b>
        	  Area:<b><%= garageOwnersdetails.get("area") %></b>
        	 Shop_No:<b><%= garageOwnersdetails.get("shop_no") %></b>
        	   </h4>
        	    <h4>
        	   Status:<b><%= garageOwnersdetails.get("status") %></b>
        	  	   </h4>
        	  	   
        	     </div>
        	  
           
           
   </section>
    <footer>
            Design & Develop by <a href="https://www.incapp.in">INCAPP</a> &copy; Reights reserved &nbsp;&nbsp;&nbsp; 
            Social Media:
            <a href="http://www.facebook.com/incapp" target="_blank" ><i class="fab fa-facebook"></i></a>
            <a href="http://www.instagram.com/incapp.in" target="_blank" ><i class="fab fa-instagram"></i></a>
            <a href="http://www.youtube.com/incapp" target="_blank" ><i class="fab fa-youtube"></i></a>
    </footer>

    <a id="myTopBtn"><i class="fas fa-arrow-circle-up"></i></a>
</body>
<script>
    AOS.init();
</script>

<script>
    //script for light box
    $(document).on('click', '[data-toggle="lightbox"]', function(event) {
          event.preventDefault();
          $(this).ekkoLightbox();
      });
  </script>

<script>
    //script for scroll to top
    $("#myTopBtn").click(function () {
        $("html, body").animate({scrollTop: 0}, 1000);
      });
  </script>


</html>
<%} %>

<%@page import="store.model.Book" %>
<%@page import="java.util.List" %>
		<%@page import="store.service.FactoryProvider" %>
    <%@page import="store.dao.Bookdao" %>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="components/ViewBooks/fonts/icomoon/style.css">

    <link rel="stylesheet" href="components/ViewBooks/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="components/ViewBooks/css/bootstrap.min.css">
    
    <!-- Style -->
    <link rel="stylesheet" href="components/ViewBooks/css/style.css">

    <title>Bookify-Books</title>
  </head>
  <body>

  

  <div class="content">
    
    <div class="container">
      <h2 class="mb-5">Available Books</h2>
      

      <div class="table-responsive">

        <table class="table table-striped custom-table">
          <thead>
            <tr>
              
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Description</th>
              <th scope="col">Price</th>
              
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
  <%
          Bookdao bdao = new Bookdao(FactoryProvider.getFactory());
          List<Book> list = bdao.getAllBook();
  for(Book b:list)	{		
          %>
          <tr >
      
            <td>
              <%= b.getbId() %>
            </td>
            <td><a href="#"><%= b.getbName() %></a></td>
            <td>
              
              <small class="d-block"><%= b.getbDesc() %></small>
            </td>
            <td><%= b.getbPrice() %></td>
           
            <td><a href="#" class="more">Details</a></td>
  
  </tr>

          <% } %>

        
      
      
          
        </tbody>
      </table>
      </div>


    </div>

  </div>
    
    

    <script src="components/ViewBooks/js/jquery-3.3.1.min.js"></script>
    <script src="components/ViewBooks/js/popper.min.js"></script>
    <script src="components/ViewBooks/js/bootstrap.min.js"></script>
    <script src="components/ViewBooks/js/main.js"></script>
  </body>
</html>
<%@page import="store.model.Users"%>
<%@page import="store.dao.Genredao" %>
			<%@page import="store.dao.Bookdao" %>
            <%@page import="store.service.FactoryProvider" %>
            <%@page import="java.util.List" %>
            <%@page import="store.model.Genre" %>
<%@page import="store.model.Book" %>
<%
    Users user1 = (Users) session.getAttribute("current-user");
   

    Genredao ggdao = new Genredao(FactoryProvider.getFactory());
    List<Genre> gglist = ggdao.getGenres();

%>
<style>
.dropdownn {
  position: relative;
  display: inline-block;
}

.dropdownn-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  padding: 12px 16px;
  z-index: 1;
}

.dropdownn:hover .dropdownn-content {
  display: block;
}
</style>

<nav class="navbar navbar-expand-lg navbar-dark  " style="background-color:#006A6E!important;">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">Bookify</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item active">
                    <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                </li>

<li class="nav-link">

            
                
                    <div class="dropdownn">
  <span style="text-color:#FFFFFF!important">Genre</span>
  <div class="dropdownn-content">
 
   <% for (Genre g : gglist) {
                            %>
                            <a href="index.jsp?genre=<%= g.getGenreId()%>" class="dropdown-item "><%= g.getGenreTitle()%></a>


                            <%    }
                            %>
  
  </div>
</div>
                </li>
               

            </ul>
           

            <ul class="navbar-nav ml-auto">


                <li class="nav-item active">
                    <a class="nav-link" href="#!" data-toggle="modal" data-target="#cart">  <i class="fa fa-cart-plus"  style="font-size: 20px;"></i> <span class="ml-0 cart-items">( 0 )</span>  </a>
                </li>



                <%                    if (user1 == null) {

                %>

                <li class="nav-item active">
                    <a class="nav-link" href="login.jsp">Login </a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="register.jsp">Register </a>
                </li>


                <%                        } else {


                %>
                <li class="nav-item active">
                    <a class="nav-link" href="<%=  user1.getUserType().equals("admin") ? "admin.jsp" : "normal.jsp"%>"><%= user1.getUserName()%> </a>
                </li>

                <li class="nav-item active">
                    <a class="nav-link" href="LogoutServlet">Logout </a>
                </li>



                <%    }

                %>




            </ul>

        </div>

    </div>
</nav>
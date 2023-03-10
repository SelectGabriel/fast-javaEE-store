<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>LOL Lavanderia Online | Iní­cio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        
        <!-- Bootstrap CSS & Style -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
    </head>
  
    <body>
        <div class="d-lg-flex half">
            <div class="bg order-1 order-md-2" style="background-image: url('img/bg_1.jpg');"></div>
            <div class="contents order-2 order-md-1">

                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-7">
                            <h3><strong>Entre na LOL, sua Lavanderia Online</strong></h3>
                            <p class="mb-4">Não acumule mais roupa suja: tenha suas roupas limpinhas no conforto do seu lar sem esforço!</p>
                            <form action="LoginServlet?action=login" method="post">
                                <div class="form-group first">
                                    <label for="username">Login</label>
                                    <input type="text" class="form-control" placeholder="seu-email@gmail.com" id="login" name="login">
                                </div>
                                <div class="form-group last mb-3">
                                    <label for="password">Senha</label>
                                    <input type="password" class="form-control" placeholder="******" id="password" name="pswd">
                                </div>

                                <div class="d-flex mb-5 align-items-center">
                                    <label class="control control--checkbox mb-0"><span class="caption">Lembre-me</span>
                                        <input type="checkbox" checked="false"/>
                                        <div class="control__indicator"></div>
                                    </label>
                                </div>
                                
                                <div class="d-flex mb-5 align-items-center">
                                    <a href="${pageContext.request.contextPath}/register.jsp" class="forgot-pass">Ainda não possui conta? <strong>Clique aqui</strong></a>
                                </div>
                                
                                <input type="submit" value="Log In" class="btn btn-block btn-primary">

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>

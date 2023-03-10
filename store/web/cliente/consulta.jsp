<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>

    <head>
        <title>LOL Lavanderia Online</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">
        <link rel="stylesheet" href="css/styleConsulta.css">

    </head>

    <body>
        <jsp:include page="/cliente/cabecalho.jsp" />
        <c:if test="${message != null}">
            <script>
                alert("Pedido não encontrado");
            </script>
        </c:if>
        <div class="container">

            <div class="row he-1000 justify-content-center align-items-center">
                <div class="col-3">
                    <form action="${pageContext.request.contextPath}/ClienteServlet?action=consultaPedidoCliente" method="post">
                        <div class="mb-3">
                            <label for="pedido" class="form-label" >Pedido</label>
                            <input type="text" class="form-control" name="pedido">
                        </div>
                        <input type="submit" value="Pesquisar" class="btn btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </body>

</html>
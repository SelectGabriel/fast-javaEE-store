<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>LOL Lavanderia Online | Roupas</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <!-- Bootstrap CSS & Style -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">
        <!-- Bootstrap CSS & Style -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
    </head>
    <body>
        <jsp:include page="/funcionario/cabecalho.jsp" />

        <main class="container">
            <div class="container">
                <div class="row align-items-center justify-content-center">
                    <div class="col-md-7">
                        <h3><strong>Editar Roupa - ID: <c:out value="${roupa.id}"/></strong></h3>
                        <form action="${pageContext.request.contextPath}/FuncionarioServlet?action=confirmarEditarRoupa&id=<c:out value="${roupa.id}"/>" method="post">
                            <div class="form-group">
                                <label for="inputNome">Nome</label>
                                <input type="text" class="form-control" id="inputNome" value="<c:out value="${roupa.nome}"/>" name="nome">
                            </div>
                            <div class="form-group">
                                <label for="preco por pessoa">Preço (R$)</label>
                                <input type="text" class="form-control" id="money"  value="<c:out value="${roupa.preco}"/>" name="preco">
                            </div>
                            <div class="form-group">
                                <label for="prazo">Prazo (Em dias)</label>
                                <input type="text" class="form-control" id="prazo" value="<c:out value="${roupa.prazo}"/>" name="prazo">
                            </div>
                            <button type="submit" class="btn btn-outline-success">Salvar</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/scripts.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

        <script>
            $(document).ready(function(){
                $('#money').mask('#.##0,00', {reverse: true});
            });
        </script>
    </body>
</html>

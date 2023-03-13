<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    </head>
    <body>
        <jsp:include page="/funcionario/cabecalho.jsp" />

        <main class="container">
            <h2 class="mb-4">
                Cadastro de roupas
            </h2>
            <div class="mt-5">
                <h3 class="mb-3 h4">Lista</h3>
                <table class="table table-hover">
                    <thead class="c-thead">
                        <tr class="text-center">
                            <th scope="col">ID</th>
                            <th scope="col">Peça</th>
                            <th scope="col">Preço</th>
                            <th scope="col">Prazo (em dias)</th>
                            <th scope="col">ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="itens" items="${roupas}"> 
                            <tr class="c-clickable text-center table-secondary" >
                                <th scope="row"><c:out value="${itens.id}"/></th>
                                <td><c:out value="${itens.nome}"/></td>
                                <td>R$<fmt:formatNumber value="${(itens.preco / 100)}" type="number" minFractionDigits= "2" maxFractionDigits="2"/></td>
                                <td><c:out value="${itens.prazo}"/></td>
                                <td>
                                    <button type="button" class="btn btn-outline-primary"  data-href="${pageContext.request.contextPath}/FuncionarioServlet?action=editarRoupa&id=${itens.id}" onclick="window.location.href = this.dataset.href">Editar</button>
                                    <button type="button" class="btn btn-outline-danger" data-href="${pageContext.request.contextPath}/FuncionarioServlet?action=confirmarRemoverRoupa&id=${itens.id}" onclick="if (confirm('Deseja mesmo remover?'))
                                            window.location.href = this.dataset.href">Remover</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="button" class="btn btn-outline-success"  data-href="${pageContext.request.contextPath}/PagesServlet?action=novoRoupa" onclick="window.location.href = this.dataset.href">Novo</button>
            </div>
        </div>
    </main>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/scripts.js"></script>
</body>
</html>

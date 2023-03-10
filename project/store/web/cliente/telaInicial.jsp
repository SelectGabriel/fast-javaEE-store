<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>

    <head>
        <title>LOL Lavanderia Online</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <!-- Bootstrap CSS & Style -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">
    </head>
    <meta charset="UTF-8">

    <body>
        <% if(session.getAttribute("logado") == null) { %>
        <jsp:forward page="../index.jsp"> 
            <jsp:param name="erro" value="nao logado" /> 
        </jsp:forward>
        <%}%>

        <jsp:include page="/cliente/cabecalho.jsp" />
        
        <div class="container">

            <main class="container">
                <h2 class="mb-4">
                    Meus Pedidos em aberto
                </h2>

                <div class="mt-5">
                    <table class="table table-hover">
                        <thead class="c-thead">
                            <tr class="text-center">
                                <th scope="col">Número</th>
                                <th scope="col">Data de Criação</th>
                                <th scope="col">Status</th>
                                <th scope="col">ação</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pedidoAbertoCliente" items="${requestScope.pedidosAbertosCliente}"> 
                                <tr class="c-clickable text-center table-warning" id="tr1" >
                                    <th scope="row"><c:out value="${pedidoAbertoCliente.numero}"/></th>
                                    <td><fmt:formatDate value="${pedidoAbertoCliente.dthrcriacao.getTime()}" pattern="dd/MM/yyyy HH:mm"/></td>
                                    <td><span class="badge badge-sm badge-primary c-status" id="span1"><c:out value="${pedidoAbertoCliente .status.nome}"/></span></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/PedidoServlet?action=cancelar&id=${pedidoAbertoCliente.id}">
                                            <button type="button"  class="btn btn-outline-danger" onclick="confirm('Deseja mesmo cancelar?'); document.getElementById('span1').textContent = 'CANCELADO'; document.getElementById('tr1').className = 'c-clickable text-center table-danger';">Cancelar</button>
                                        </a>
                                    </td>
                                    <td class="clicavel" data-href="${pageContext.request.contextPath}/ClienteServlet?action=verPedidoId&id=${pedidoAbertoCliente.id}" onclick="window.location.href = this.dataset.href">Ver</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>
        </div>

    </body>

</html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>LOL Lavanderia Online | Ver Pedido</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <!-- Bootstrap CSS & Style -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">
    </head>
    <body>                       
        <c:choose>
            <c:when test="${ sessionScope.logado.role == 'cliente' }">
                    <jsp:include page="/cliente/cabecalho.jsp" />
            </c:when>
            <c:when test="${ sessionScope.logado.role == 'funcionario' }">
                    <jsp:include page="/funcionario/cabecalho.jsp" />
            </c:when>
        </c:choose>
        <main class="container">
            <h2 class="mb-4">
                Numero pedido: #<c:out value="${requestScope.pedido.numero}"/>
            </h2>

            <form action="listaPedidos.html" method="POST">

                <div class="form-group row">
                    <label for="atendimento-id" class="col-2 col-form-label">Id do Pedido:</label>
                    <div class="col-10">
                        <input type="text" id="pedido-id" class="form-control-plaintext" readonly value="<c:out value="${requestScope.pedido.id}"/>" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="atendimento-id" class="col-2 col-form-label">Nome Cliente:</label>
                    <div class="col-10">
                        <input type="text" id="pedido-id" class="form-control-plaintext" readonly value="<c:out value="${requestScope.pedido.pessoa.nome}"/>" />
                    </div>
                </div>

                <c:forEach var="roupa" items="${pedido.produtos}"> 
                    <div class="form-group row">
                        <label for="atendimento-cliente" class="col-2 col-form-label">Item:</label>
                        <div class="col-10">
                            <input type="text" id="camiseta" class="form-control-plaintext" readonly value="<c:out value="${roupa.produto.nome}"/>" />
                            <p class="card-text">Quantidade: <c:out value="${roupa.quantidade}"/></p>
                            <p class="card-text">Subtotal: R$<fmt:formatNumber value="${(roupa.preco / 100) * roupa.quantidade}" type="number" minFractionDigits= "2" maxFractionDigits="2"/> </p>
                        </div>
                    </div>

                </c:forEach>
                <div class="form-group row">
                    <label for="atendimento-tipo" class="col-2 col-form-label">Total do Pedido:</label>
                    <div class="col-10">
                        <input type="text" id="total-pedido" class="form-control-plaintext" readonly
                               value="R$ <fmt:formatNumber value="${pedido.valor / 100}" type="number" minFractionDigits= "2" maxFractionDigits="2"/>" />
                    </div>
                </div>
                <div class="form-group row">
                    <label for="atendimento-status" class="col-2 col-form-label">Status:</label>
                    <div class="col-10">
                        <input type="text" id="atendimento-produto" class="form-control-plaintext" readonly
                               value="<c:out value="${pedido.status.nome}"/>"/>
                    </div>
                </div>
                    <c:choose>
                        <c:when test="${ sessionScope.logado.role == 'funcionario' }">
                            <c:choose>
                                <c:when test="${(fn:replace(requestScope.pedido.status.nome,' ', '') == 'EMABERTO')}">
                                    <a href="${pageContext.request.contextPath}/PedidoServlet?action=recolher&id=${requestScope.pedido.id}"><button type="button" class="btn btn-success" >Recolher</button></a></td>
                                </c:when>
                                <c:when test="${(fn:replace(requestScope.pedido.status.nome,' ', '') == 'RECOLHIDO')}">
                                    <a href="${pageContext.request.contextPath}/PedidoServlet?action=lavar&id=${requestScope.pedido.id}"><button type="button" class="btn btn-success" >Lavar</button></a></td>
                                </c:when>
                                <c:when test="${(fn:replace(requestScope.pedido.status.nome,' ', '') == 'PAGO')}">
                                    <a href="${pageContext.request.contextPath}/PedidoServlet?action=finalizar&id=${requestScope.pedido.id}"><button type="button" class="btn btn-success" >Finalizar</button></a></td>
                                </c:when>
                            </c:choose>
                        </c:when>
                        <c:when test="${ sessionScope.logado.role == 'cliente' }">
                            <c:choose>
                                <c:when test="${(fn:replace(requestScope.pedido.status.nome,' ', '') == 'EMABERTO')}">
                                <button type="button" onclick="if (confirm('Deseja mesmo cancelar?'))
                                                            window.location.href = this.dataset.href" data-href="${pageContext.request.contextPath}/PedidoServlet?action=cancelar&id=${requestScope.pedido.id}" class="btn btn-danger" >Cancelar</button></td>
                                                    
                                </c:when>
                                <c:when test="${(fn:replace(requestScope.pedido.status.nome,' ', '') == 'AGUARDANDOPAGAMENTO')}">
                                <a href="${pageContext.request.contextPath}/PedidoServlet?action=pagar&id=${requestScope.pedido.id}"><button type="button" class="btn btn-success" >Pagar</button></a></td>
                                </c:when>
                            </c:choose>
                        </c:when>
                    </c:choose>
            <br>
            <br> 
                    <c:choose>
                        <c:when test="${ sessionScope.logado.role == 'funcionario' }">
                            <button type="button" onclick="location.href = '${pageContext.request.contextPath}/PagesServlet?action=listaPedidos'" class="btn btn-outline-dark" style="width:50%">Voltar</button>        
                        </c:when>
                        <c:when test="${ sessionScope.logado.role == 'cliente' }">
                            <button type="button" onclick="location.href = '${pageContext.request.contextPath}/PagesServlet?action=listaPedidosCliente'" class="btn btn-outline-dark" style="width:50%">Voltar</button>        
                        </c:when>
                    </c:choose>
            </form>
        </main>

    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <head>
        <title>LOL Lavanderia Online | Meus Pedidos</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">

    </head>
    <body>
        <jsp:include page="/cliente/cabecalho.jsp" />
        <main class="container">
            <h2 class="mb-4">
                Meus Pedidos
            </h2>

            <!-- Filtro de pedidos -->
            <div class="mt-3">
                <div class="form-label">
                    <label for="filtro-pedidos">
                        <i class="fas fa-filter"></i>
                        <span class="mx-2">Filtrar pedido por status:</span>
                    </label>
                    <select id="filtro-atendimentos" class="form-control" onchange="location = this.value;">
                        <option>Selecione um status:</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosCliente">Todos</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=EM+ABERTO">Abertos</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=REJEITADO">Rejeitados</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=CANCELADO">Cancelados</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=RECOLHIDO">Recolhidos</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=AGUARDANDO+PAGAMENTO">Aaguardando Pagamento</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=PAGO">Pagos</option>
                        <option value="${pageContext.request.contextPath}/PagesServlet?action=listaPedidosStatusCliente&status=FINALIZADO">Finalizados</option>
                    </select>
                </div>

                <div class="mt-5">
                    <h3 class="mb-3 h4">Todos os Pedidos</h3>
                    <table class="table table-hover">
                        <thead class="c-thead">
                            <tr class="text-center">
                                <th scope="col">N??mero</th>
                                <th scope="col">Data de Cria????o</th>
                                <th scope="col">Status</th>
                                <th scope="col">A????o</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pedidosCliente" items="${requestScope.pedidosCliente}">
                                <tr class="c-clickable text-center table-danger <c:out value="${fn:replace(pedidosCliente.status.nome,' ', '')}"/>">
                                    <th scope="row"><c:out value="${pedidosCliente.numero}"/></th>
                                    <td><fmt:formatDate value="${pedidosCliente.dthrcriacao.getTime()}" pattern="dd/MM/yyyy HH:mm"/></td>
                                    <td><span class="badge badge-sm badge-danger c-status"><c:out value="${pedidosCliente.status.nome}"/></span></td>
                                    <td style='text-align:center; vertical-align:middle'>
                                        <c:choose>
                                            <c:when test="${ sessionScope.logado.role == 'cliente' }">
                                                <c:choose>
                                                    <c:when test="${(fn:replace(pedidosCliente.status.nome,' ', '') == 'EMABERTO')}">
                                                    <button type="button" onclick="if (confirm('Deseja mesmo cancelar?'))
                                                            window.location.href = this.dataset.href" data-href="${pageContext.request.contextPath}/PedidoServlet?action=cancelar&id=${pedidosCliente.id}" class="btn btn-danger" >Cancelar</button></td>
                                                    

                                                    </c:when>
                                                    <c:when test="${(fn:replace(pedidosCliente.status.nome,' ', '') == 'AGUARDANDOPAGAMENTO')}">
                                                    <a href="${pageContext.request.contextPath}/PedidoServlet?action=pagar&id=${pedidosCliente.id}"><button type="button" class="btn btn-success" >Pagar</button></a></td>
                                                    </c:when>
                                                </c:choose>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <td class="clicavel" data-href="${pageContext.request.contextPath}/ClienteServlet?action=verPedidoId&id=${pedidosCliente.id}" onclick="window.location.href = this.dataset.href">Ver</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/scripts.js"></script>
        <style>
            .EMABERTO{
                --bs-table-bg:rgba(255, 255, 0, 0.300);
            }
            .REJEITADO{
                --bs-table-bg:rgba(255, 0, 0, 0.300);
            }
            .RECOLHIDO{
                --bs-table-bg:rgba(128, 128, 128, 0.300);
            }
            .AGUARDANDOPAGAMENTO{
                --bs-table-bg:rgba(0, 0, 255, 0.300);
            }
            .PAGO{
                --bs-table-bg:rgba(255, 123, 0, 0.514);
            }
            .FINALIZADO{
                --bs-table-bg:rgba(0, 128, 0, 0.300);
            }
        </style>
    </body>
</html>

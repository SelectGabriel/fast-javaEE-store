<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>LOL Lavanderia Online | Novo Pedido</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/stylePedido.css">
    </head>

    <body>
        <jsp:include page="/cliente/cabecalho.jsp" />

        <div class=container>
            <div class="col-md-7">
                <h3>Novo Pedido</h3>
                <p>Escolha as peças que deseja lavar e confirme seu pedido:</p>
            </div>
            <div class="row">
                <div class="col-75">
                    <div class="container">
                       
                        <c:forEach var="itens" items="${roupas}"> 
                            <div class="row">
                                <div class="col-50">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="form-check">
                                                <h5 class="card-title"><c:out value="${itens.nome}"/></h5> 
                                                <p>R$ <fmt:formatNumber value="${(itens.preco / 100)}" type="number" minFractionDigits= "2" maxFractionDigits="2"/> por peça</p>
                                                <p class="card-text">Prazo de entrega: ${itens.prazo} dias úteis</p>
                                            </div>                                        
                                        </div>
                                    </div>
                                </div>
                                <div class="col-25" style="display:flex; align-items: center;">
                                    <a class="btn btn-outline-primary" onclick="adicionarItemPedido(${itens.id}, '${itens.nome}', ${itens.preco}, ${itens.prazo})">Adicionar</a>
                                </div>
                            </div>
                        </c:forEach>
                        
                    </div>
                </div>
                <div class="col-25">
                    <div class="container">
                        <form action="${pageContext.request.contextPath}/PedidoServlet?action=fazerpedido" onsubmit="return conferirPedido()" method="post">
                            <h4>Checkout:</h4>
                            <hr>
                            <div id="lista_itens">
                            </div>

                            <hr>

                            <p>Itens <span class="price" style="color:black"><b><a id="total_itens">0</a></b></span></p>
                            <p>Total <span class="price" style="color:black"><b><a id="valor">R$ 00.00</a></b></span></p>
                            <p>Prazo de entrega: <a id="prazo">0</a> dias úteis</p>

                            <input type="submit" style="width:100%" name="botao_confirmar"value="Confirmar orçamento" class="btn btn-outline-success btn-block">     
                            <input type="submit" style="width:100%" name="botao_rejeitar" value="Rejeitar orçamento" class="btn btn-outline-danger btn-block">     
                        </form>
                    </div>

            </div>
        </div>
        <script src="js/scripts.js"></script>
        <script>
            var total_itens = 0;
            var valor = 0;
            var prazo = 0; 
        </script>

    </body>
</html>

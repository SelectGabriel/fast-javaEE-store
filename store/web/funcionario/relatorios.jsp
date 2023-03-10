<!DOCTYPE html>

<html>
    <title>LOL Lavanderia Online</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="css/stylePedido.css">
    <link rel="stylesheet" href="css/styleRelatorio.css">

</head>


<body>
     <jsp:include page="/funcionario/cabecalho.jsp" />
    <div class="container">
       

        <div class="row">
            <div class="col-md-10 offset-md-1">
                <h1 class="mb-5 h1">Relatórios</h1>
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th scope="row">Relatório de clientes</th>
                            <td></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/RelatorioServlet?action=relatorioClientes"><button type="button" class="btn btn-info"> Gerar </button></a></td>
                        </tr>
                        <tr>
                            <th scope="row">Relatório de cliente fiéis</th>
                            <td></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/RelatorioServlet?action=relatorioClientesFieis"><button type="button" class="btn btn-info"> Gerar </button></a></td>
                        </tr>
                        <tr>
                        <th scope="row">Relatório de receita diária</th>
                        <form method="get" action="${pageContext.request.contextPath}/RelatorioServlet" >
                        <td>
                            <input type="hidden" id="action" name="action" value="relatorioReceitas">
                            <label for="inputCategory">Data Início</label><br/>
                            <input id="datade" name="datade" type="date"><br/>
                            <label for="inputCategory">Data Fim</label><br/>
                            <input id="dataate" name="dataate" type="date" >

                        </td>
                        <td>
                            <button type="submit" class="btn btn-info"> Gerar </button></a>
                        </td>
                        </form>
                        </tr>
                         <tr>
                        <th scope="row">Relátorio de pedidos</th>
                        <form method="get" action="${pageContext.request.contextPath}/RelatorioServlet" >
                        <td>
                            <input type="hidden" id="action" name="action" value="relatorioPedidos">
                            <label for="inputCategory">Data Início</label><br/>
                            <input id="datade" name="datade" type="date"><br/>
                            <label for="inputCategory">Data Fim</label><br/>
                            <input id="dataate" name="dataate" type="date" >

                        </td>
                        <td>
                            <button type="submit" class="btn btn-info"> Gerar </button></a>
                        </td>
                        </form>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>

</body>

</html>
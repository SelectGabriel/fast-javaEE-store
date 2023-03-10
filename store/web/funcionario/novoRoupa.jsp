<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <h3><strong>Nova Roupa</strong></h3>
                        <form action="${pageContext.request.contextPath}/FuncionarioServlet?action=adicionarRoupa" method="post">
                            <div class="form-group">
                                <label for="inputNome">Nome</label>
                                <input type="text" class="form-control" id="inputNome" placeholder="Blusa" value="" name="nome">
                            </div>
                            <div class="form-group">
                                <label for="preco por peca">Preço (R$)</label>
                                <input type="text" class="form-control" id="money" placeholder="10,00" value="" name="preco">
                            </div>
                            <div class="form-group">
                                <label for="prazo">Prazo (em dias)</label>
                                <input type="text" class="form-control" id="prazo" placeholder="1" value="" name="prazo">
                            </div>
                            <input type="submit" value="Salvar" class="btn btn-outline-success">
                            <button type="button" class="btn btn-outline-warning" data-href="${pageContext.request.contextPath}/PagesServlet?action=roupas" onclick="confirm('Deseja mesmo cancelar?'); window.location.href = this.dataset.href">Cancelar</button>
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
            $(document).ready(function () {
                $('#money').mask('#.##0,00', {reverse: true});
            });
        </script>
    </body>
</html>

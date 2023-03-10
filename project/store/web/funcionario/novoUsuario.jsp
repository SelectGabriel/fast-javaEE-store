<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>LOL Lavanderia Online | Usuarios</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

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
                        <h3><strong>Novo Usuário</strong></h3>
                        <form action="${pageContext.request.contextPath}/FuncionarioServlet?action=confirmaAdicionarUsuario" method="post">
                            <div class="form-group">
                                <label for="inputNome">Nome</label>
                                <input type="text" class="form-control" id="inputNome" placeholder="Fofomeno, Ronaldo o" name="nome" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" placeholder="fofomeno@ronaldo.com" name="email" required>
                            </div>
                            <div class="form-group">
                                <label for="dtnc">CPF</label>
                                <input type="text" class="form-control" id="CPF" placeholder="99957775999" name="cpf" required>
                            </div>
                            <div class="form-group">
                                <label for="dtnc">Senha</label>
                                <input type="password" class="form-control" id="dtnc" placeholder="*******" name="senha" required>
                            </div>
                            <button type="submit" class="btn btn-outline-success">Salvar</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <script src="../js/bootstrap.min.js"></script>
        <script src="../js/scripts.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
        
        <script>
            $(document).ready(function () { 
                var $seuCampoCpf = $("#CPF");
                $seuCampoCpf.mask('000.000.000-00', {reverse: true});
            });
        </script>
    </body>
</html>

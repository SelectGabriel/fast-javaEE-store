<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>LOL Lavanderia Online | Registre-se</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <!-- Bootstrap CSS & Style -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
    </head>

    <body>
        <div class="d-lg-flex half">
            <div class="bg order-1 order-md-1" style="background-image: url('img/bg_1.jpg');"></div>
            <div class="contents order-2 order-md-1">
                <div class="container">
                    <div class="row align-items-center justify-content-center">
                        <div class="col-md-7">
                            <h3><strong>Registre-se</strong></h3>
                            <p class="mb-4">Primeira vez acessando? Crie agora sua conta:</p>

                            <form class="form-detail" action="LoginServlet?action=cadastrar" method="post" id="register">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" id="CPF" name="cpf" placeholder="CPF" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="nome" placeholder="Nome completo" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="email" class="form-control" name="email" placeholder="email@email.com" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">                                    
                                    <input type="text" class="form-control" id="TELEFONE" name="telefone" placeholder="(00)0000-0000" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="cep" placeholder="CEP" id="cep" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="rua" placeholder="Rua" id="rua" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="number" class="form-control" name="numero" placeholder="Número" style="height: 40px"/>
                                    <input type="text" class="form-control" name="complemento" placeholder="Complemento" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="cidade" placeholder="Cidade" id="cidade" style="height: 40px"/>
                                    <input type="text" class="form-control" name="estado" placeholder="Estado" id="uf" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-3">                                    
                                    <input type="password" class="form-control" name="senha1" placeholder="Senha" style="height: 40px"/>
                                </div>
                                <div class="input-group mb-4">
                                    <input type="password" class="form-control" name="senha2" placeholder="Repetir senha" style="height: 40px"/>
                                </div>
                                <input type="submit" value="Cadastrar" class="btn btn-block btn-primary">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
            <script>

        $(document).ready(function() {

            function limpa_formulário_cep() {
                // Limpa valores do formulário de cep.
                $("#rua").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");
                $("#ibge").val("");
            }
            
            //Quando o campo cep perde o foco.
            $("#cep").blur(function() {

                //Nova variável "cep" somente com dígitos.
                var cep = $(this).val().replace(/\D/g, '');

                //Verifica se campo cep possui valor informado.
                if (cep !== "") {

                    //Expressão regular para validar o CEP.
                    var validacep = /^[0-9]{8}$/;

                    //Valida o formato do CEP.
                    if(validacep.test(cep)) {

                        //Preenche os campos com "..." enquanto consulta webservice.
                        $("#rua").val("...");
                        $("#bairro").val("...");
                        $("#cidade").val("...");
                        $("#uf").val("...");
                        $("#ibge").val("...");

                        //Consulta o webservice viacep.com.br/
                        $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                            if (!("erro" in dados)) {
                                //Atualiza os campos com os valores da consulta.
                                $("#rua").val(dados.logradouro);
                                $("#bairro").val(dados.bairro);
                                $("#cidade").val(dados.localidade);
                                $("#uf").val(dados.uf);
                                $("#ibge").val(dados.ibge);
                            } //end if.
                            else {
                                //CEP pesquisado não foi encontrado.
                                limpa_formulário_cep();
                                alert("CEP não encontrado.");
                            }
                        });
                    } //end if.
                    else {
                        //cep é inválido.
                        limpa_formulário_cep();
                        alert("Formato de CEP inválido.");
                    }
                } //end if.
                else {
                    //cep sem valor, limpa formulário.
                    limpa_formulário_cep();
                }
            });
        });

    </script>
    
        <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>

        <script>
            $(document).ready(function () {
                var $seuCampoCpf = $("#CPF");
                $seuCampoCpf.mask('000.000.000-00', {reverse: true});
                
                var $campoTelefone= $("#TELEFONE");
                $campoTelefone.mask('(00)0 0000-0000');
            });
        </script>
    </body>
</html>

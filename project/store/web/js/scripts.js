/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

//evento tabelas
$('.c-clickable').click(function() {
  window.location = $(this).data('href'), '_blank';
});

$('a[title="Excluir"]').click(function(e) {
  e.preventDefault();
  if (confirm('Tem certeza que quer excluir este item?')) {
    $(this).parent().parent().remove();
  }
});

function adicionarItemPedido(id_item, nome_item, preco_item, prazo_item) {
    let lista_itens_element = document.getElementById('lista_itens');
    let total_itens_element = document.getElementById('total_itens');
    let valor_element = document.getElementById('valor');
    let prazo_element = document.getElementById('prazo');
    
    let bloco = "<p><a>" + nome_item + "</a> <span class=\"price\">R$ " + (preco_item / 100).toFixed(2) + "</span></p><input type=\"hidden\" name=\"itens[]\" value=\"" + id_item +"\"/>";
    
    console.log(total_itens);
                
    lista_itens_element.innerHTML = lista_itens_element.innerHTML + bloco;
    
    total_itens = total_itens + 1;
    total_itens_element.innerHTML = total_itens;
    
    valor = valor + preco_item;
    valor_element.innerHTML = "R$ " + (valor / 100).toFixed(2);
            
    prazo = prazo_item >= prazo ? prazo_item : prazo;
    prazo_element.innerHTML = prazo;
 }

function conferirPedido(){
    if (total_itens <= 0){
        alert("Primeiro adicione itens ao pedido.")
        return false;
    }
    return true;
}


  // function para pagar pedido
function pagarPedido() {
      alert("Pagamento efetuado com sucesso. Status alterado para PAGO"); 
      location.href = "listaPedidos.html";
  }

//evento para filtro de tabelas
$('#filtro-pedidos').change(function () {
    $('tr').show();
    switch ($(this).val()) {
        case 'abertos':
            $('td span.badge-primary').parent().parent().hide();
            break;
        case 'rejeitados':
            $('tbody tr').hide();
            $('tr.table-danger').show();
            break;
        case 'cancelados':
            $('tbody tr').hide();
            $('td span.badge-danger').parent().parent().show();
            break;
        case 'recolhidos':
            $('tbody tr').hide();
            $('td span.badge-secondary').parent().parent().show();
            break;
        case 'aguardando':
            $('tbody tr').hide();
            $('td span.badge-primary').parent().parent().show();
            break;
        case 'pagos':
            $('tbody tr').hide();
            $('td span.badge-success').parent().parent().show();
            break;
        case 'finalizados':
            $('tbody tr').hide();
            $('td span.badge-success').parent().parent().show();
            break;
    }
});
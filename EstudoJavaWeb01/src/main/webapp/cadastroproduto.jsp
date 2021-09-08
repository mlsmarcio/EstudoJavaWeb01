
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>	
    

	<style type="text/css">
		table {
			width: 100%;
		}
	</style>
	<meta charset="ISO-8859-1">
	<title>Cadastro de Produtos</title>

</head>

<body>

	<div class="container" style="margin-top: 100px;">
		<jsp:include page="rodape.jsp">
			<jsp:param name="destino" value="acessoliberado.jsp" />
			<jsp:param name="titulo" value="Voltar" />
		</jsp:include>	
			
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6 alert-success">
		        <div id="card" class="card card-container">
		        
				 	<h1 class="text-center ">Cadastro de Produtos</h1>
					
					 <h4>${msg}</h4>
										
					<form action="salvarProduto" method="post" class="form-signin" id="formProduct" onsubmit="return (validarCampos() ? true :false);">
					<div class="form-group">
						<input type="hidden" readonly="readonly" id="id" name="id" value="${product.id}" class="form-control" placeholder="Código">
					</div>
					<div class="form-group">
						<label for="nome">Nome</label>
						<input type="text" id="nome" name="nome" value="${product.nome}" class="form-control"  autofocus placeholder="Nome">
					</div>
					<div class="form-group">
						<label for="quantidade">Quantidade</label>
						<input type="text" id="quantidade" name="quantidade" value="${product.quantidade}" class="form-control"  
							pattern="^[0-9]{8}[.]([0-9]{2}" placeholder="Quantidade 00.00">
					</div>
					
					<div class="form-group">
						<label for="valor">Valor</label>
						<input type="number" id="valor" name="valor" value="${product.valor}" class="form-control"  
							pattern="[0-9]+([\.,][0-9]+)?"  step="0.01" placeholder="Valor 00.00">
					</div>
					<button class="btn btn-info" type="submit" >Salvar</button>
					<button class="btn btn-info" type="submit" 
						onclick="document.getElementById('formProduct').action='salvarProduto?acao=reset'" >Cancelar</button>
					</form>
				</div>
				
				<br/>
				<h4>Listagem de Produtos</h4>
				<div class="table-responsive">
					<table class="table table-condensed table-hover table-responsive" style="border-spacing: 15px;">
						<thead>
							<th>ID</th>
							<th>NOME</th>
							<th>QUANTIDADE</th>							
							<th>VALOR</th>
							<th>EXCLUIR</th>
							<th>EDITAR</th>
						</thead>
						<tbody>
						<c:forEach items="${produtos}" var="product">
							<tr>
								<td><c:out value="${product.id}"></c:out> </td>
								<td><c:out value="${product.nome}"></c:out> </td>
								<td><c:out value="${product.quantidade}"></c:out> </td>
								<td><c:out value="${product.valor}"></c:out> </td>
								<td><a class="btn btn-danger btn-xs" href="salvarProduto?acao=delete&product=${product.id}">
										<span class="glyphicon glyphicon-remove"></span>
									</a>
								</td>
								<td>
									<a class="btn btn-info btn-xs" href="salvarProduto?acao=editar&product=${product.id}">
 										<span class="glyphicon glyphicon-edit"></span> 
									</a>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>
		
		<br/>
		
		<jsp:include page="rodape.jsp">
			<jsp:param name="destino" value="acessoliberado.jsp" />
			<jsp:param name="titulo" value="Voltar" />
		</jsp:include>	
		
	</div>
	
	<script type="text/javascript">
		function validarCampos(){
			if (document.getElementById("nome").value == ''){
				alert("informe o Nome");	
				document.getElementById("nome").focus();
				return false;
				
			}else if (document.getElementById("quantidade").value == ''){
				alert("informe a Quantidade");
				return false;
				
			}else if (document.getElementById("valor").value == ''){
				alert("informe o Valor");
				return false;
			}
			return true;
		}
	</script>	
</body>
</html>
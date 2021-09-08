<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="resources/css/bootstrap.min.css">
<script src="resources/js/jquery.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<meta charset="ISO-8859-1">
<style>
img {
	max-height: 160px;
}
</style>
<!--   style="max-height: 160px;" -->

<title></title>
</head>
<body class="bg-success">
	<div class="container">
		<h3 class="text-center">Seja bem vindo ao sistema</h3>
	</div>

	<div class="container">
		<div class="panel panel-success">
			<a href="salvarUsuario?acao=listartodos" class="bg-image hover-zoom">
				<img src="resources/img/users.jpg" alt="Cadastro de Usuários"
				data-toggle="tooltip" title="Cadastro de Usuários"
				data-placement="bottom" class="img-thumbnail float-left">
			</a> 
			<a href="salvarProduto?acao=listartodos" class="bg-image hover-zoom">
				<img src="resources/img/produto.jpg" alt="Cadastro de Produtos"
				title="Cadastro de Produtos" data-toggle="tooltip"
				data-placement="bottom" class="img-thumbnail float-left">
			</a>
			
		</div>
	</div>
	
	<jsp:include page="rodape.jsp" />
	
	<script>
		$(document).ready(function() {
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
</body>
</html>
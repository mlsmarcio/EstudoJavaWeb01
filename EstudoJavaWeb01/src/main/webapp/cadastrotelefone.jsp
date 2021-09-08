
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<title>Cadastro de Telefones</title>

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

					<h1 class="text-center ">Cadastro de Telefones</h1>

					<h4>${msg}</h4>

					<form action="salvarTelefones" method="post" class="form-signin"
						id="formUser" onsubmit="return (validarCampos() ?true :false);">
						<div class="form-row">

							<div class="form-group col-md-6">
								<label for="idUser">Id:</label> 
								<input type="text" id="idUser" name="idUser" class="form-control" readonly value="${userEscolhido.id}">
							</div>

							<div class="form-group col-md-6">
								<label for="userName">Usuário:</label> 
								<input type="text" id="userName" name="userName" class="form-control" readonly value="${userEscolhido.nome}">
							</div>
							
							<div class="form-group col-md-6">
								<label for="telefone">Telefone:</label> 
								<input type="tel" id="telefone" name="telefone" class="form-control" required
									pattern="[0-9]{2} [0-9]{1} [0-9]{4}-[0-9]{4}" placeholder="Ex.: 99 9 9999-9999">
							</div>
							<div class="form-group col-md-6">
								<label for="tipo">Tipo:</label>
								<select name="tipo" id="tipo" size="1" class="form-control">
									<option value="Casa">Casa</option>
									<option value="Contato">Contato</option>
									<option value="Celular">Celular</option>
								</select>
							</div>
							
	
							<div class="form-group col-md-12">
								<button class="btn btn-info" type="submit">Salvar</button>
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="col-sm-3"></div>
		</div>

		<br />
		<h4>Listagem de Registros</h4>
		<div class="table-responsive">
			<table class="table table-condensed table-hover table-responsive"
				style="border-spacing: 15px;">
				<thead>
					<th>ID</th>
					<th>NÚMERO</th>
					<th>TIPO</th>
					<th>EXCLUIR</th>
				</thead>
				<tbody>
					<c:forEach items="${telefones}" var="fone">
						<tr>
							<td><c:out value="${fone.id}"></c:out></td>
							<td><c:out value="${fone.numero}"></c:out></td>
							<td><c:out value="${fone.tipo}"></c:out></td>
							<td><a class="btn btn-danger btn-xs"
								href="salvarTelefones?acao=delete&fone=${fone.id}&user=${fone.idUsuario}"> <span
									class="glyphicon glyphicon-remove"></span>
							</a></td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<jsp:include page="rodape.jsp">
			<jsp:param name="destino" value="acessoliberado.jsp" />
			<jsp:param name="titulo" value="Voltar" />
		</jsp:include>

	</div>
	<script type="text/javascript">
	
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert("informe o Número");
				document.getElementById("numero").focus();
				return false;
			}
			if (document.getElementById("tipo").value == '') {
				alert("informe o Tipo");
				document.getElementById("tipo").focus();
				return false;
			}
			
			return true;
		}
	</script>
</body>
</html>

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
<title>Cadastro de Usuário</title>

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

					<h1 class="text-center ">Cadastro de Usuários</h1>

					<h4>${msg}</h4>

					<form action="salvarUsuario" method="post" class="form-signin"
						id="formUser" onsubmit="return (validarCampos() ?true :false);">
						<div class="form-row">
							<div class="form-group col-md-12">
								<input type="hidden" readonly="readonly" id="id" name="id"
									value="${user.id}" class="form-control" placeholder="Código">
							</div>
							<div class="form-group col-md-12">
								<label for="nome">Nome</label> <input type="text" id="nome"
									name="nome" value="${user.nome}" class="form-control" required
									autofocus placeholder="Nome">
							</div>
							<div class="form-group col-md-12">
								<label for="telefone">Telefone (81 9 9929-0519)</label> <input
									type="tel" id="telefone" name="telefone"
									value="${user.telefone}" class="form-control" required
									pattern="[0-9]{2} [0-9]{1} [0-9]{4}-[0-9]{4}"
									placeholder="Telefone DDD N NNNN-NNNN">
							</div>
	
							<div class="form-row">
								<div class="form-group col-md-6">
									<label for="cep">CEP </label> 
									<input type="text" id="cep"	name="cep" value="${user.cep}" class="form-control" required
										pattern="\d{5}-\d{3}" placeholder="00000-000" onblur="consultaCep()">
								</div>
								<div class="form-group col-md-6">
									<label for="uf">Estado</label> 
									<select id="uf" name="uf"	class="form-control">
										<option value="AC" ${user.uf == 'AC' ? 'selected' :''} >Acre</option>
										<option value="AL" ${user.uf == 'AL' ? 'selected' :''} >Alagoas</option>
										<option value="AP" ${user.uf == 'AP' ? 'selected' :''} >Amapá</option>
										<option value="AM" ${user.uf == 'AM' ? 'selected' :''} >Amazonas</option>
										<option value="BA" ${user.uf == 'BA' ? 'selected' :''} >Bahia</option>
										<option value="CE" ${user.uf == 'CE' ? 'selected' :''} >Ceará</option>
										<option value="DF" ${user.uf == 'DF' ? 'selected' :''} >Distrito Federal</option>
										<option value="ES" ${user.uf == 'ES' ? 'selected' :''} >Espírito Santo</option>
										<option value="GO" ${user.uf == 'GO' ? 'selected' :''} >Goiás</option>
										<option value="MA" ${user.uf == 'MA' ? 'selected' :''} >Maranhão</option>
										<option value="MT" ${user.uf == 'MT' ? 'selected' :''} >Mato Grosso</option>
										<option value="MS" ${user.uf == 'MS' ? 'selected' :''} >Mato Grosso do Sul</option>
										<option value="MG" ${user.uf == 'MG' ? 'selected' :''} >Minas Gerais</option>
										<option value="PA" ${user.uf == 'PA' ? 'selected' :''} >Pará</option>
										<option value="PB" ${user.uf == 'PB' ? 'selected' :''} >Paraíba</option>
										<option value="PR" ${user.uf == 'PR' ? 'selected' :''}>Paraná</option>
										<option value="PE" ${user.uf == 'PE' ? 'selected' :''}>Pernambuco</option>
										<option value="PI" ${user.uf == 'PI' ? 'selected' :''}>Piauí</option>
										<option value="RJ" ${user.uf == 'RJ' ? 'selected' :''}>Rio de Janeiro</option>
										<option value="RN" ${user.uf == 'RN' ? 'selected' :''}>Rio Grande do Norte</option>
										<option value="RS" ${user.uf == 'RS' ? 'selected' :''}>Rio Grande do Sul</option>
										<option value="RO" ${user.uf == 'RO' ? 'selected' :''}>Rondônia</option>
										<option value="RR" ${user.uf == 'RR' ? 'selected' :''}>Roraima</option>
										<option value="SC" ${user.uf == 'SC' ? 'selected' :''}>Santa Catarina</option>
										<option value="SP" ${user.uf == 'SP' ? 'selected' :''}>São Paulo</option>
										<option value="SE" ${user.uf == 'SE' ? 'selected' :''}>Sergipe</option>
										<option value="TO" ${user.uf == 'TO' ? 'selected' :''}>Tocantins</option>
									</select>
								</div>
	
								<div class="form-group col-md-9">
									<label for="rua">Endereço</label> 
									<input type="text" value="${user.rua}"	class="form-control" id="rua" name="rua">
								</div>
								<div class="form-group col-md-3">
									<label for="ibge">IBGE</label> 
									<input type="text" value="${user.ibge}"class="form-control" id="ibge" name="ibge">
								</div>
	
								<div class="form-group col-md-6">
									<label for="cidade">Cidade</label> 
									<input type="text" value="${user.cidade}" class="form-control" id="cidade" name="cidade">
								</div>
								<div class="form-group col-md-6">
									<label for="bairro">Bairro</label> 
									<input type="text" id="bairro" name="bairro" value="${user.bairro}" 
									class="form-control" placeholder="Bairro">
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<label for="login">Login</label> 
								<input type="text" id="login" name="login" value="${user.login}" class="form-control"
								 required	placeholder="Login">
							</div>
							<div class="form-group col-md-6">
								<label for="senha">Senha</label> <input type="password"
									id="senha" name="senha" value="${user.senha}"
									class="form-control" required placeholder="Senha">
							</div>
							<div class="form-group col-md-12">
								<button class="btn btn-info" type="submit">Salvar</button>
								<button class="btn btn-info" type="submit"
									onclick="document.getElementById('formUser').action='salvarUsuario?acao=reset'">Cancelar</button>
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
					<th>NOME</th>
					<th>LOGIN</th>
					<th>FONE</th>
					<th>EXCLUIR</th>
					<th>EDITAR</th>
					<th>FONES</th>
				</thead>
				<tbody>
					<c:forEach items="${usuarios}" var="user">
						<tr>
							<td><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.nome}"></c:out></td>
							<td><c:out value="${user.login}"></c:out></td>
							<td><c:out value="${user.telefone}"></c:out></td>
							<td><a class="btn btn-danger btn-xs"
								href="salvarUsuario?acao=delete&user=${user.id}"> <span
									class="glyphicon glyphicon-remove"></span>
							</a></td>

							<td><a class="btn btn-info btn-xs"
								href="salvarUsuario?acao=editar&user=${user.id}"> <span
									class="glyphicon glyphicon-edit"></span>
							</a></td>

							<td><a class="btn btn-info btn-xs"
								href="salvarTelefones?acao=telefoneAdd&user=${user.id}"> <span
									class="glyphicon glyphicon-phone-alt" title="Telefones"></span>
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
	
		function consultaCep(){
			let cep = $('#cep').val();
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
		}
	
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert("informe o Nome");
				document.getElementById("nome").focus();
				return false;

			} else if (document.getElementById("telefone").value == '') {
				alert("informe o Telefone");
				document.getElementById("telefone").focus();
				return false;

			} else if (document.getElementById("login").value == '') {
				alert("informe o Login");
				document.getElementById("login").focus();
				return false;

			} else if (document.getElementById("senha").value == '') {
				alert("informe o Senha");
				document.getElementById("senha").focus();
				return false;
			}
			return true;
		}
	</script>
</body>
</html>
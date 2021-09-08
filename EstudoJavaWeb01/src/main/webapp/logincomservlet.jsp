<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="resources/css/bootstrap.min.css">
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>	

	<style type="text/css">
		body{
			background-color: #dff0d8;
		}
	</style>
	<title>Insert title here</title>
	
</head>
<body>
    
	<div class="container" style="margin-top: 100px;">
		<div class="row">
			<div class="col-sm-3"></div>
			<div class="col-sm-6" style="border:solid 1px; border-color:#508c35;">
		        <div id="card" class="card card-container">
            		<h3>CONTROLE DE ACESSO</h3>
            		<p>Por favor entre com suas credenciais para logar.</p>
          		</div>
				<form action="LoginServlet" method="post" class="login-form">
				
				<div class="form-group">
					<input type="text" id="login" name="login" placeholder="LOGIN" autocomplete="off" autofocus="autofocus"> 
				</div>
				<div class="form-group">
					<input type="password" id="senha" name="senha" placeholder="SENHA">
				</div>
				<div class="form-group">
					<button type="submit" class="btn-success" ><span title="Login" class="glyphicon glyphicon-user btn-sm"></span>Entrar</button>
				</div>
				</form>
	      	</div>
	      	<div class="col-sm-3"></div>
		</div>
			
		<a href="index.jsp" class="btn btn-link btn-md">
			<span class="glyphicon glyphicon-home sm"></span>
			Início
		</a>
    </div>
</body>
</html>
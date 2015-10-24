<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crud Autor</title>
<link href="css/bootstrap.min.css" rel="stylesheet">


</head>
<body>

	<div class="container">

		<div role="tabpanel">


			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">

				<li role="presentation"
						<c:if test="${empty TabAddEdit}">						 
									class="active"
						</c:if>						
				><a href="#Lista" aria-controls="home"
					role="tab" data-toggle="tab">Lista</a></li>

				<li role="presentation"
				<c:if test="${!empty TabAddEdit}">						 
									class="active"
						</c:if>
				
				><a href="#Adicionar"
					aria-controls="profile" role="tab" data-toggle="tab">Adicionar/Editar</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<div role="tabpanel" id="Lista"
						<c:if test="${empty TabAddEdit}">						 
									class="tab-pane active"
						</c:if>
						<c:if test="${!empty TabAddEdit}">						 
									class="tab-pane"
						</c:if>
				
				>

					<br />
					<p>
						<a href="author?op=cadastra" class="btn btn-primary"
							role="button"><span class="glyphicon glyphicon-plus"></span>
							Adicionar</a>
					</p>
					<br />


					<div class="table - responsive">
						<!-- Default panel contents -->
						<div class="panel-heading">Autor</div>


						<c:choose>
							<c:when test="${!empty MensagemAuthor}">
								<br />
								<div class="alert alert-success" role="alert">
									${MensagemAuthor}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>

							</c:when>
							<c:when test="${!empty MensagemErro}">
								<br />
								<div class="alert alert-danger" role="alert">
									${MensagemErro}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>

							</c:when>
						</c:choose>


						<!-- Table -->
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Código Autor</th>
									<th>Nome</th>
									<th>Sobre Nome</th>
									<th>E-mail</th>
									<th>Telefone</th>
									<th>Nacionalidade</th>
									<th>Data Nascimento</th>
									<th>Sexo</th>
									<th>Endereço</th>
									<th>Cônjuge</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${!empty authors}">
										<c:forEach items="${authors}" var="author">
											<tr>
												<td>${author.id}</td>
												<td>${author.name}</td>
												<td>${author.lastName}</td>
												<td>${author.email}</td>
												<td>${author.phone}</td>
												<td>${author.nacionality}</td>
												<td>${author.birthDate}</td>
												<td>${author.gender}</td>
												<td>${author.address}</td>
												<td>${author.spouse}</td>
												<td style="white-space: nowrap;"><a
													href="author?op=editar&id=${author.id}"> <span
														class="glyphicon glyphicon-edit"></span> Editar
												</a></td>
												<td style="white-space: nowrap;"><a
													href="author?op=deletar&id=${author.id}"> <span
														class="glyphicon glyphicon-trash"></span> Excluir
												</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="11">
												<div class="alert alert-info" role="alert">
													<b>Nenhuma</b> Categoria cadastrado.
												</div>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<br />			
				</div>


				<div role="tabpanel"  id="Adicionar"
						<c:if test="${!empty TabAddEdit}">						 
									class="tab-pane active"
						</c:if>
						<c:if test="${empty TabAddEdit}">						 
									class="tab-pane"
						</c:if>
						>
					<form method="post" action="author?op=confirmar">
						<br /> <br /> <br />
						<div class="form-group">
							<label>Código Autor</label> <input type="text"
								class="form-control" id="txtIdAutor" name="txtIdAutor"
								placeholder="Código Autor" readonly="readonly"
								<c:if test="${!empty author}">						 
						 					value = ${author.id}
							    </c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>

						<div class="form-group">
							<label>Nome</label> <input type="text" class="form-control"
								id="txtNome" name="txtNome"
								placeholder="Nome"
								<c:if test="${!empty author}">						 
						 					value = "${author.name}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>SobreNome</label> <input type="text" class="form-control"
								id="txtSobreNome" name="txtSobreNome"
								placeholder="SobreNome"
								<c:if test="${!empty author}">						 
						 					value = "${author.lastName}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>E-mail</label> <input type="text" class="form-control"
								id="txtEmail" name="txtEmail"
								placeholder="E-mail"
								<c:if test="${!empty author}">						 
						 					value = "${author.email}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>Telefone</label> <input type="text" class="form-control"
								id="txtTelefone" name="txtTelefone"
								placeholder="Telefone"
								<c:if test="${!empty author}">						 
						 					value = "${author.phone}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>Nacionalidade</label> <input type="text" class="form-control"
								id="txtNacionalidade" name="txtNacionalidade"
								placeholder="Nacionalidade"
								<c:if test="${!empty author}">						 
						 					value = "${author.nacionality}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>Data Nascimento</label> <input type="text" class="form-control"
								id="txtDataNascimento" name="txtDataNascimento"
								placeholder="Data Nascimento (dd/MM/AAAA)"
								<c:if test="${!empty author}">						 
						 				 value="${author.birthDate}"   
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						

						<div class="form-group">
							<label for="txtNome">Sexo</label> <label class="radio-inline">
								<input type="radio" id="rdSexo" name="rdSexo" value="Masculino">Masculino
							</label> <label class="radio-inline"> <input type="radio"
								name="rdSexo" id="rdSexo" value="Feminino">
							</label>
						</div>					

						<div class="form-group">
							<label>Endereço</label> <input type="text" class="form-control"
								id="txtEndereco" name="txtEndereco"
								placeholder="Endereço"
								<c:if test="${!empty author}">						 
						 					value = "${author.address}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<div class="form-group">
							<label>Cônjuge</label> <input type="text" class="form-control"
								id="txtConjuge" name="txtConjuge"
								placeholder="Cônjuge"
								<c:if test="${!empty author}">						 
						 					value = "${author.spouse}"
								</c:if>
								<c:if test="${empty author}">						 
						 					value = ""
							    </c:if>>
						</div>
						
						<input class="btn btn-success" type="submit" id="sign-in"
							value="confirmar">
					</form>
				</div>
			</div>
		</div>

	</div>


	<div class="container">

		<hr>

		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>eStore &copy; Your Website 2014</p>
			</div>
		</div>
		</footer>

	</div>
	<!-- /.container -->

	<!-- jQuery -->
	<script src="js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>
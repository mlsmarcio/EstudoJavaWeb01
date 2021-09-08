package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanLogin;
import dao.DaoUsuario;

@WebServlet("/salvarUsuario")
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public Usuario() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String idUsuario = request.getParameter("user");
			Long id = 0L;
			if (idUsuario != null && !idUsuario.isEmpty()) {
				id = Long.parseLong(idUsuario);
			}

			if (acao.equalsIgnoreCase("delete")) {
				daoUsuario.delete(id);

				request.setAttribute("usuarios", daoUsuario.listar());
				
			} else if (acao.equalsIgnoreCase("editar")) {
				BeanLogin usuarioConsulta = daoUsuario.consultar(id);
				request.setAttribute("user", usuarioConsulta);

			} else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("usuarios", daoUsuario.listar());
			}
			request.getRequestDispatcher("/cadastrousuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BeanLogin usuario = new BeanLogin();
		String id = request.getParameter("id");
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setNome(request.getParameter("nome"));
		usuario.setTelefone(request.getParameter("telefone"));
		usuario.setCep(request.getParameter("cep"));
		usuario.setRua(request.getParameter("rua"));
		usuario.setBairro(request.getParameter("bairro"));
		usuario.setCidade(request.getParameter("cidade"));
		usuario.setUf(request.getParameter("uf"));
		usuario.setIbge(request.getParameter("ibge"));

		String mensagemValidacao = "";
		String acao = request.getParameter("acao");
		boolean limpar = false;
		boolean atualizaDados = false;
		boolean validouLogin = false;

		// VERIFICA SE A AÇÃO É RESETAR
		limpar = (acao != null && acao.equalsIgnoreCase("reset") ? true : false);

		if (!limpar) {

			// VALIDAÇÃO DADOS OBRIGATÓRIOS
			if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
				mensagemValidacao = "Informe o Nome!";
			}
			if (usuario.getLogin() == null || usuario.getLogin().trim().isEmpty()) {
				mensagemValidacao = mensagemValidacao + "\nInforme o Login!";
			}
			if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
				mensagemValidacao = mensagemValidacao + "\nInforme a senha!";
			}
			if (usuario.getTelefone() == null || usuario.getTelefone().trim().isEmpty()) {
				mensagemValidacao = mensagemValidacao + "\nInforme o Telefone!";
			}
			
			atualizaDados = (id == null || id.isEmpty() ? false : true);
			
			usuario.setId((atualizaDados ? Long.parseLong(id) : null));
			
			if (mensagemValidacao == null || mensagemValidacao.trim().isEmpty()) {
				
				// VALIDAÇÃO DO LOGIN
				if (atualizaDados) {
					validouLogin = daoUsuario.validarLoginUpdate(usuario.getId(), usuario.getLogin());

				} else {
					validouLogin = daoUsuario.validarLogin(usuario.getLogin());
				}
				
				// INSERE OU ATUALIZA DADOS
				if (atualizaDados && validouLogin) {
					daoUsuario.atualizar(usuario);

				} else if (!atualizaDados && validouLogin) {
					daoUsuario.salvar(usuario);

				} else {
					mensagemValidacao = "Login (" + usuario.getLogin() + ") já cadastrado.";
				}
			}
		}
		
		if (!mensagemValidacao.trim().isEmpty()) {
			request.setAttribute("msg",
					"<div class='alert alert-info'>"
							+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
							+ "<strong>Atenção!</strong> " + mensagemValidacao + "</div>");
			request.setAttribute("user", usuario);
		}

		try {
			// LISTA OS USUÁRIOS 
			if ((mensagemValidacao.trim().isEmpty() && atualizaDados) || !atualizaDados ) {
				request.setAttribute("usuarios", daoUsuario.listar());
			}
			request.getRequestDispatcher("/cadastrousuario.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

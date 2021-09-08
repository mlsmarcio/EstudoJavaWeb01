package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanLogin;
import beans.BeanTelefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class Telefone
 */
@WebServlet("/salvarTelefones")
public class Telefone extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DaoUsuario daoUsuario = new DaoUsuario();
	private DaoTelefone daoTelefone = new DaoTelefone();
	
    public Telefone() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String idUser = request.getParameter("user");
			BeanLogin usuario = daoUsuario.consultar(Long.parseLong(idUser));

			if (acao.equalsIgnoreCase("delete")) {
				String idFone = request.getParameter("fone");
				if (idFone != null && !idFone.isEmpty()) {
					daoTelefone.delete(Long.parseLong(idFone));
					request.setAttribute("msg", "Deletado com Sucesso!");
				}
			}
			
			request.getSession().setAttribute("userEscolhido", usuario);
			request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
			RequestDispatcher view = request.getRequestDispatcher("/cadastrotelefone.jsp");
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			BeanLogin usuario = (BeanLogin) request.getSession().getAttribute("userEscolhido");
			BeanTelefone telefone = new BeanTelefone();
			telefone.setNumero(request.getParameter("telefone"));
			telefone.setTipo(request.getParameter("tipo"));
			telefone.setIdUsuario(usuario.getId());
			
			daoTelefone.salvar(telefone);
			
			RequestDispatcher view = request.getRequestDispatcher("/cadastrotelefone.jsp");
			request.getSession().setAttribute("userEscolhido", usuario);
			request.setAttribute("telefones", daoTelefone.listar(usuario.getId()));
			request.setAttribute("msg", "Salvo com Sucesso!");
			view.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

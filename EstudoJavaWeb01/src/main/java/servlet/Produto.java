package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoProduto;
import beans.BeanLogin;
import beans.BeanProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();
	
    public Produto() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
    	super.init( config );
    	System.out.println(" SERVLET INICIADA ");
    }
    
    public void destroy() {
    	super.destroy();
    	System.out.println(" SERVLET FINALIZADA ");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String idProduto = request.getParameter("product");
			Long id = 0L;
			if (idProduto != null && !idProduto.isEmpty()) {
				id = Long.parseLong(idProduto);
			}
			
			if (acao.equalsIgnoreCase("delete")) {
				daoProduto.delete(id);
				request.setAttribute("produtos", daoProduto.listar());
			}
			else if (acao.equalsIgnoreCase("editar")) {
				BeanProduto produtoConsulta = daoProduto.consultar(id);
				request.setAttribute("product", produtoConsulta);
				
			}else if (acao.equalsIgnoreCase("listartodos")) {
				request.setAttribute("produtos", daoProduto.listar());
			}
			
			request.getRequestDispatcher("/cadastroproduto.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BeanProduto produto = new BeanProduto();
		String id = request.getParameter("id");
		produto.setNome(request.getParameter("nome"));
		String quantidade = request.getParameter("quantidade");
		if (!quantidade.trim().isEmpty() && quantidade != null) {
			produto.setQuantidade(Double.parseDouble(quantidade));
		}
		String valor = request.getParameter("valor");
		if (!valor.trim().isEmpty() && valor != null) {
			produto.setValor(Double.parseDouble(valor));		
		}
		
		String mensagemValidacao = "";
		String acao = request.getParameter("acao");
		boolean limpar = false;
		boolean atualizaDados = false;
		boolean validouNome = false;

		// VERIFICA SE A AÇÃO É RESETAR
		limpar = (acao != null && acao.equalsIgnoreCase("reset") ? true : false);

		if (!limpar) {

			// VALIDAÇÃO DADOS OBRIGATÓRIOS
			if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
				mensagemValidacao = "Informe o Nome!";
			}
			if (produto.getQuantidade() == null || produto.getQuantidade() <= 0) {
				mensagemValidacao = mensagemValidacao + "\nInforme a Quantidade!";
			}
			if (produto.getValor() == null || produto.getValor() <= 0) {
				mensagemValidacao = mensagemValidacao + "\nInforme o Valor!";
			}
			
			atualizaDados = (id == null || id.isEmpty() ? false : true);
			
			produto.setId((atualizaDados ? Long.parseLong(id) : null));
			
			if (mensagemValidacao == null || mensagemValidacao.trim().isEmpty()) {
				
				// VALIDAÇÃO DO NOME
				if (atualizaDados) {
					validouNome = daoProduto.validarNomeUpdate(produto.getId(), produto.getNome());

				} else {
					validouNome = daoProduto.validarProduto(produto.getNome());
				}
				
				// INSERE OU ATUALIZA DADOS
				if (atualizaDados && validouNome) {
					daoProduto.atualizar(produto);

				} else if (!atualizaDados && validouNome) {
					daoProduto.salvar(produto);

				} else {
					mensagemValidacao = "Nome (" + produto.getNome() + ") já cadastrado.";
				}
			}
		}
		
		if (!mensagemValidacao.trim().isEmpty()) {
			request.setAttribute("msg",
					"<div class='alert alert-info'>"
							+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
							+ "<strong>Atenção!</strong> " + mensagemValidacao + "</div>");
			request.setAttribute("product", produto);
		}

		try {
			// LISTA OS PRODUTOS 
			if ((mensagemValidacao.trim().isEmpty() && atualizaDados) || !atualizaDados ) {
				request.setAttribute("produtos", daoProduto.listar());
			}
			request.getRequestDispatcher("/cadastroproduto.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		String acao = request.getParameter("acao");
//		if (acao != null && acao.equalsIgnoreCase("reset")) 
//		{
//			try {
//				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
//				request.setAttribute("produtos", daoProduto.listar());
//				view.forward(request, response);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} 
//		else 
//		{
//			BeanProduto produto = new BeanProduto();
//			String id = request.getParameter("id");
//			produto.setNome(request.getParameter("nome"));
//			produto.setQuantidade(Double.parseDouble(request.getParameter("quantidade")));
//			produto.setValor(Double.parseDouble(request.getParameter("valor")));
//			
//			String mensagemValidacao = "";
//			
//			// VALIDAÇÃO
//			if (id == null || id.isEmpty()) {
//				if (daoProduto.validarProduto(produto.getNome())){
//					daoProduto.salvar(produto);
//				}
//				else {
//					mensagemValidacao = "Produto (" + produto.getNome() + ") já cadastrado.";
//				}
//				
//			}else {
//				produto.setId(Long.parseLong(id));
//				if (daoProduto.validarNomeUpdate(produto.getId(), produto.getNome())) {
//					daoProduto.atualizar(produto);
//					
//				}else {
//					mensagemValidacao = "Produto (" + produto.getNome() + ") já cadastrado.";
//				}
//			}
//			
//			if (!(mensagemValidacao == null) && !mensagemValidacao.isEmpty()) {
//				request.setAttribute("msg", "<div class='alert alert-info'>"
//						+ "<a href='#' class='close' data-dismiss='alert' aria-label='close'>&times;</a>"
//						+ "<strong>Atenção!</strong> " + mensagemValidacao +"</div>");
//				request.setAttribute("product", produto);
//			}
//			
//			try {
//				RequestDispatcher view = request.getRequestDispatcher("/cadastroproduto.jsp");
//				request.setAttribute("produtos", daoProduto.listar());
//				view.forward(request, response);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

}

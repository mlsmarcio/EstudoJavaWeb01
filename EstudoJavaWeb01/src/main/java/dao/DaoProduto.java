package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanProduto;
import connection.SingleConnection;

public class DaoProduto {
	private Connection connection;
	
	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanProduto produto){
		try {
			String sql = "INSERT INTO produto(nome, quantidade, preco) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			insert.execute();
			connection.commit();
			
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public List<BeanProduto> listar() throws Exception{
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		BeanProduto produto  = null;
		String sql = "SELECT * FROM produto order by nome, id";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			produto = new BeanProduto();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade( resultSet.getDouble("quantidade"));
			produto.setValor(resultSet.getDouble("preco"));
			lista.add(produto);
		}
		
		return lista;
	}
	
	public void delete (Long id){
		try {
			String sql = "delete from produto where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public BeanProduto consultar(Long id) {
		BeanProduto produto= new BeanProduto();
		try {
			String sql = "select * from produto where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultado = statement.executeQuery();
			
			if (resultado.next()) {
				produto.setId(resultado.getLong("id"));
				produto.setNome(resultado.getString("nome"));
				produto.setQuantidade(resultado.getDouble("quantidade"));
				produto.setValor(resultado.getDouble("preco"));
			}
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return produto;
	}

	public boolean validarProduto(String nome) {
		boolean retorno = false;
		try {
			String sql = "select count(1) as qtd from produto where UPPER(nome)=UPPER(?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				retorno = resultado.getInt("qtd") == 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return retorno;
	}

	public boolean validarNomeUpdate(Long id, String nome) {
		boolean retorno = false;
		try {
			String sql = "select count(1) as qtd from produto where UPPER(nome)=UPPER(?) AND id <> ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, nome);
			statement.setLong(2, id);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {
				retorno = resultado.getInt("qtd") == 0;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return retorno;
	}

	public void atualizar(BeanProduto produto) {
		try {
			String sql = "UPDATE produto SET nome=?, quantidade=?, preco=? WHERE id=?";
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, produto.getNome());
			update.setDouble(2, produto.getQuantidade());
			update.setDouble(3, produto.getValor());
			update.setLong(4, produto.getId());
			update.execute();
			connection.commit();
			
		} catch (Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

}

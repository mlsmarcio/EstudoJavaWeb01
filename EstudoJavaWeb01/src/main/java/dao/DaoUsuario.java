package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanLogin;
import connection.SingleConnection;

public class DaoUsuario {
	private Connection connection;
	
	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanLogin usuario){
		try {
			String sql = "INSERT INTO usuario(login, senha, nome, telefone"
					+ ",cep, rua, bairro, cidade, uf, ibge) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getUf());
			insert.setString(10, usuario.getIbge());
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
	
	public List<BeanLogin> listar() throws Exception{
		List<BeanLogin> lista = new ArrayList<BeanLogin>();
		BeanLogin usuario  = null;
		String sql = "SELECT * FROM usuario order by nome, id";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			usuario = new BeanLogin();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setTelefone(resultSet.getString("telefone"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setUf(resultSet.getString("uf"));
			usuario.setIbge(resultSet.getString("ibge"));
			lista.add(usuario);
		}
		
		return lista;
	}
	
	public void delete (Long id){
		try {
			String sql = "delete from usuario where id=?";
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

	public BeanLogin consultar(Long id) {
		BeanLogin user= new BeanLogin();
		try {
			String sql = "select * from usuario where id=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultado = statement.executeQuery();
			
			if (resultado.next()) {
				user.setId(resultado.getLong("id"));
				user.setLogin(resultado.getString("login"));
				user.setSenha(resultado.getString("senha"));
				user.setNome(resultado.getString("nome"));
				user.setTelefone(resultado.getString("telefone"));
				user.setCep(resultado.getString("cep"));
				user.setRua(resultado.getString("rua"));
				user.setBairro(resultado.getString("bairro"));
				user.setCidade(resultado.getString("cidade"));
				user.setUf(resultado.getString("uf"));
				user.setIbge(resultado.getString("ibge"));
			}
			
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return user;
	}

	public boolean validarLogin(String login) {
		boolean retorno = false;
		try {
			String sql = "select count(1) as qtd from usuario where login=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
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

	public boolean validarLoginUpdate(Long id, String login) {
		boolean retorno = false;
		try {
			String sql = "select count(1) as qtd from usuario where login=? AND id <> ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
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

	public void atualizar(BeanLogin usuario) {
		try {
			String sql = "UPDATE usuario SET login=?, senha=?, nome=?, telefone=? "
					+ ", cep=?, rua=?, bairro=?, cidade=?, uf=?, ibge=? WHERE id=?";
			PreparedStatement update = connection.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getNome());
			update.setString(4, usuario.getTelefone());
			
			update.setString(5, usuario.getCep());
			update.setString(6, usuario.getRua());
			update.setString(7, usuario.getBairro());
			update.setString(8, usuario.getCidade());
			update.setString(9, usuario.getUf());
			update.setString(10, usuario.getIbge());
			
			update.setLong(11, usuario.getId());
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

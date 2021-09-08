package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefone {
	private Connection connection;
	
	public DaoTelefone() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(BeanTelefone telefone){
		try {
			String sql = "INSERT INTO telefone(numero, tipo, usuario) values(?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getIdUsuario());
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
	
	public List<BeanTelefone> listar(Long user) throws Exception{
		List<BeanTelefone> lista = new ArrayList<BeanTelefone>();
		BeanTelefone telefone  = null;
		String sql = "SELECT * FROM telefone WHERE usuario=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, user);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			telefone = new BeanTelefone();
			telefone.setId(resultSet.getLong("id"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));
			telefone.setIdUsuario(resultSet.getLong("usuario"));
			lista.add(telefone);
		}
		return lista;
	}
	
	public void delete (Long id){
		try {
			String sql = "delete from telefone where id=?";
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
	
}

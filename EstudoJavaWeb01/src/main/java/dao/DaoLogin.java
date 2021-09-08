package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import connection.SingleConnection;

public class DaoLogin {

	private Connection connection;
	
	public DaoLogin() {
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception{
		String sql = "SELECT * FROM usuario WHERE login=? and senha=?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1,  login);
		statement.setString(2, senha);
		ResultSet resultSet = statement.executeQuery();
		return resultSet.next();
		
	}
}

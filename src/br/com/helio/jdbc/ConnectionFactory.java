package br.com.helio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	Connection connection = null;

	public Connection getConnection() {
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/guestx?useTimezone=true&serverTimezone=UTC",
					"guestx", "g4Linha8AnGol@");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return connection;
	}
}
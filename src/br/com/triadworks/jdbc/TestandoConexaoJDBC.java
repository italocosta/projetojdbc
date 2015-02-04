package br.com.triadworks.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public class TestandoConexaoJDBC {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("Conectado, bem vindo ao Banco de Dados com Java!");
		connection.close();

	}

}

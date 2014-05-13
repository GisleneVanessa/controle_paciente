package main;

import java.sql.*;

public class Conexao {

	public static Connection getClonexao() throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection con = DriverManager.getConnection("jdbc:sqlite:/Users/rafaelbelo/Workspace/AP4/controlepacientes.db");
		return con;
	}
}

package db;

import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) { //estou criando uma stream (conex�o), com o arquivo db.properties
			Properties props = new Properties(); //instanciando uma classe Properties, que ir� pegar a conex�o acima, e carregar o arquivo.
			props.load(fs); //carregando o arquivo
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties(); //o m�todo LoadProperties e instancio localmente um props.
				String url = props.getProperty("dburl"); // uso a conex�o (stream) props para pegar a propriedade dburl do arquivo, e crio uma String url. N�o entendo por que disso, porque se o arquivo j� contem a url, pra que seper�-la?
				conn = DriverManager.getConnection(url, props); 
				
				// estou sendo redundante pois props contem url. O certo seria algum m�todo que recebesse s� o props. mas vamos assumir assim.
				// o m�todo DriverManager possui 3 interfaces:
				//public static Connection getConnection(String url)
				//public static Connection getConnection(String url, Properties info) - a que estamos usando
				//public static Connection getConnection(String url, String user, String password) - poder�amos usar essa para pedir a senha

			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	

	
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
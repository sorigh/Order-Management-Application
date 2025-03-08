package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa utilizata pentru initializare conexiunii cu baza de date sql
 * <a href="http://dsrl.coned.utcluj.ro/">...</a>
 * <a href="http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/">...</a>
 */
public class ConnectionFactory {
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/tpdb";
	private static final String USER = "root";
	private static final String PASS = "root";

	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * constructor
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * creaza conexiunea cu baza de date
	 * @return conexiunea generata
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * accesarea conexiunii cu baza de date
	 * @return conexiunea realizata
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * inchiderea conexiunii cu baza de date pe baza conexiunii
	 * @param connection conexiunea la baza de daye
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}

package databaseLayer;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionSql {
	private static String CONNECTION_STRING = "";
	@SuppressWarnings("unused")
	private static Connection instance;

	private DBConnectionSql() {
	}

	public static Connection SQLiteConnection() throws SQLException {
		if (CONNECTION_STRING.length() == 0) {
			CONNECTION_STRING = "jdbc:sqlite:" + System.getProperty("user.dir") + File.separator + "db" + File.separator
					+ "CarRental.sqlite";
		}
		return DriverManager.getConnection(CONNECTION_STRING);
	}
}

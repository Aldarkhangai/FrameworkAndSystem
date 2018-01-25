package databaseLayer;

public class DBConnectionFile {
	private static String CONNECTION_STRING = "";
	private static DBConnectionFile instance;

	DBConnectionFile(String conn) {
		CONNECTION_STRING = conn;
	}

	public DBConnectionFile getInstance(String conn) {
		if (instance == null)
			return new DBConnectionFile(conn);
		else
			return instance;
	}

	public String getConnString() {
		return CONNECTION_STRING;
	}

}

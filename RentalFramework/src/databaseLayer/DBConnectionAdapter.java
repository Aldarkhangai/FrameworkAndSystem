package databaseLayer;

import java.io.File;

public class DBConnectionAdapter {
	DBConnectionFile connFile;
	DBConnectionSql connSql;
	
	public DBConnectionFile getConnectionFile(){
		String str = System.getProperty("user.dir") + File.separator + "\\src\\storage" + File.separator;
		connFile = new DBConnectionFile(str).getInstance(str);
		return connFile;
	}
	public DBConnectionSql getConnectionSql(){
		return connSql;
	}
}

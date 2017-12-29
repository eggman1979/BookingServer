package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import exceptions.DALException;
import exceptions.DBConnectException;

public class DBConnector {


	/**
	 * To connect to a MySQL-server
	 * 
	 * @param url must have the form
	 * "jdbc:mysql://<server>/<database>" for default port (3306)
	 * OR
	 * "jdbc:mysql://<server>:<port>/<database>" for specific port
	 * more formally "jdbc:subprotocol:subname"
	 * @throws DBConnectException 
	 *		 *
	 */

	public static Connection connectToDatabase(String url, String username, String password) throws DBConnectException

	{
		// call the driver class' no argument constructor
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			// get Connection-object via DriverManager
			return (Connection) DriverManager.getConnection(url, username, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new DBConnectException(e);
		}

	}

	private static Connection conn;
	private static Statement stm;

	public DBConnector(String server, int port, String database,
			String username, String password)
					throws DBConnectException
	{

		try{
			conn	= connectToDatabase("jdbc:mysql://"+server+":"+port+"/"+database+"?autoReconnect=true",
					username, password);
			stm		= conn.createStatement();
			
		}catch (SQLException e) {
			throw new DBConnectException(e);
		}
	}

	public DBConnector() throws DBConnectException
	{
		this(Constants.server, Constants.port, Constants.database,
				Constants.username, Constants.password);
	}

	public static ResultSet doQuery(String cmd) throws DALException, SQLException
	{
		if(stm.isClosed()){
			stm = conn.createStatement();
		}
		try { return stm.executeQuery(cmd); }
		catch (SQLException e) { throw new DALException(e);}
	}

	public static int doUpdate(String cmd) throws DALException, SQLException
	{
		if(stm.isClosed()){	
			stm = conn.createStatement();
		}
		try { return stm.executeUpdate(cmd); }
		catch (SQLException e) { throw new DALException(e); }
	}


	public static void close(){
		try {
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static boolean isConnected(){
		if( conn == null){
			return false;
		}
		else{
			return true;
		}
	}
	

}


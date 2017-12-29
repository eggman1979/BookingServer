package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.IVaskeBlokDAO;
import data.VaskeBlok;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;

public class VaskeblokDAO implements IVaskeBlokDAO{

	public VaskeblokDAO(){
		try {
			new DBConnector();
		} catch (DBConnectException e) {
		
			e.printStackTrace();
		}
	}


	public void opretVaskeBlokke(int antalBlokke, int startTid, int vaskeInterval, int boligselskabID) throws DBConnectException, DALException, SQLException {
	
		for (int i = 1 ; i <= antalBlokke; i++) {
			int blokStartTid = i *vaskeInterval + startTid;
			String insert = "INSERT INTO VASKEBLOK(boligforening_id, vaske_blok_id, starttid) VALUES('" + boligselskabID +
					"', '" + i  + "', '" + blokStartTid + "')";

			DBConnector.doUpdate(insert);

			
		}
	}

	public List<VaskeBlok> getAllVaskeBlok(int boligselskabID) throws DBConnectException, DALException  {
		

		List<VaskeBlok> list = new ArrayList<>();

		try {
			ResultSet rs = DBConnector.doQuery("SELECT * FROM VASKEBLOK WHERE boligforening_id = " + boligselskabID);

			while(rs.next()){
				list.add(new VaskeBlok(rs.getInt("boligforening_id"), rs.getInt("vaske_blok_id"), rs.getInt("starttid")));
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Der opstod en fejl i databasen");
		}finally{
		
		}

	}
}

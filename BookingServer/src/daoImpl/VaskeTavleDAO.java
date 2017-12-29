package daoImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.IVaskeTavleDAO;
import data.VaskeTavle;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;

public class VaskeTavleDAO  implements IVaskeTavleDAO{

	public VaskeTavleDAO(){
	
			try {
				new DBConnector();
			} catch (DBConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public List<VaskeTavle> getAllVaskeTavler() throws DBConnectException, DALException, SQLException {

		
		List<VaskeTavle> list = new ArrayList<VaskeTavle>();

		ResultSet rs = DBConnector.doQuery("SELECT * FROM VASKETAVLE");

		try{
			while(rs.next()){
				list.add( new VaskeTavle(rs.getInt("tavle_id"), rs.getInt("boligForening_id"),
						rs.getInt("antal_blokke_per_dag")));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Der opstod en fel i databasen");
		}finally {
			DBConnector.close();			
		}
		return list;

	}


	/**
	 *  Metode der henter alle tavler der er knyttet til en boligforening.
	 * @param boligforeningID
	 * @return Returnerer en liste over tavler fra en specifik boligforening
	 * @throws DBConnectException 
	 * @throws DALException 
	 */
	public List<VaskeTavle> getVaskeTavler(int boligforeningID) throws DBConnectException, DALException{
		
		List<VaskeTavle> list = new ArrayList<>();

		try {
			ResultSet rs = DBConnector.doQuery("SELECT * FROM VASKETAVLE WHERE boligforening_id = " + boligforeningID);

			while(rs.next()){
				VaskeTavle vt = new VaskeTavle(rs.getInt("tavle_id"), rs.getInt("boligForening_id"),
						rs.getInt("antal_blokke_per_dag"));
				list.add(vt);
			}
		}catch (SQLException e) 
		{
			e.printStackTrace();
			throw new DALException("Der opstod en fejl i databasen");
		}
		return list;
	}

	public VaskeTavle getVaskeTavle(int tavleID) throws DBConnectException, DALException, SQLException{

		List<VaskeTavle> list = getAllVaskeTavler();

		for (VaskeTavle vaskeTavle : list) {
			if(vaskeTavle.getTavleID() == tavleID){
				return vaskeTavle;
			}
		}
	
		return null;
	}

	public void opretVaskeTavle(VaskeTavle vaskeTavle) throws DALException, DBConnectException, SQLException{
		

		String statement = "insert into VASKETAVLE(boligforening_id, antal_blokke_per_dag) VALUES(" + vaskeTavle.getBoligForeningID() +", "+ vaskeTavle.getAntalBlokkePrDag() + ")";
		DBConnector.doUpdate(statement);
	}

	public void updateVaskeTavle(VaskeTavle vaskeTavle) throws DBConnectException, DALException, SQLException{
		
		String statement = "UPDATE VASKETAVLE set antal_blokke_per_dag =" + vaskeTavle.getAntalBlokkePrDag() + " WHERE boligforening_id = " + vaskeTavle.getAntalBlokkePrDag();
		DBConnector.doUpdate(statement);
		

	}
}

package daoImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.IBoligforeningDAO;
import data.BoligForening;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;

public class BoligforeningDAO implements IBoligforeningDAO{
	
	public BoligforeningDAO(){
		try {
			new DBConnector();
		} catch (DBConnectException e) {
			e.printStackTrace();
		}
	}
	
	public List<BoligForening> getAllBoligForening() throws DBConnectException, DALException, SQLException {
		
		List<BoligForening> bf = new ArrayList<BoligForening>();

		ResultSet rs = null;
		try {
			rs = DBConnector.doQuery("SELECT * FROM BOLIGFORENING");
		} catch (DALException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				bf.add(new BoligForening(rs.getString("navn"), rs.getInt("antal_tavler"), rs.getInt("boligforening_id")));	
			}
		} catch (SQLException e) {
			throw new DALException("Der skete en databasefejl");
		}
		
		return bf;
	}

	public BoligForening getBoligForening(int id) throws DBConnectException, DALException, SQLException {

		List<BoligForening> bf = getAllBoligForening();
		for (BoligForening boligForening : bf) {
			if(boligForening.getId() == id){
				return boligForening;
			}
		}
		//		Hvis boligselskab ikke findes kastes en DALException
		throw new DALException("Der findes ingen boligforening med id " + id);
	}

	public void opretBoligforening(BoligForening bf) throws DBConnectException, DALException, SQLException  {
		

		DBConnector.doUpdate("INSERT INTO BOLIGFORENING(navn, antal_tavler) VALUES('" + bf.getNavn()+ "'," + bf.getAntalTavler() +");");
		
	}


	//TODO Er ikke implementeret, da denne på nuværende tidspunkt ikke er nødvendig
	public void updateBoligforening(int id){

	}

	public void deleteBoligForening(int boligforeningID) throws DALException, DBConnectException, SQLException{
	
		String deleteString = "DELETE FROM BOLIGFORENING WHERE boligforening_id = " + boligforeningID;
		try {
			DBConnector.doUpdate(deleteString);
		} catch (DALException e) {
			throw new DALException("Boligforeningen kunne ikke oprettes");
		}
		
	}


}

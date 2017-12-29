package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.IBrugerDAO;
import data.Bruger;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;


public class BrugerDAO implements IBrugerDAO {

	
	 public BrugerDAO(){
		 try {
				new DBConnector();
			} catch (DBConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	public List<Bruger> getAllBrugere() throws DBConnectException, DALException, SQLException {
		
		List<Bruger> bf = new ArrayList<Bruger>();

		ResultSet rs = DBConnector.doQuery("SELECT * FROM BRUGERE");
		try{
			while(rs.next()){
				bf.add(new Bruger(rs.getInt("bruger_id"),rs.getInt("boligforening_id"), rs.getString("fornavn") + " " + rs.getString("efternavn"), rs.getInt("antalNøgler")));	
			}
		}catch(SQLException e){
			throw new DALException("Der opstod en fejl i databasen, brugere kunne ikke hentes");
		}
		
		return bf;
	}

	public Bruger getBruger(int id) throws DBConnectException, DALException, SQLException {

		List<Bruger> brugere = getAllBrugere();
		for (Bruger bruger : brugere) {
			if(bruger.getBrugerID() == id){
				return bruger;
			}
		}
		throw new DALException("Brugeren med pågældende id findes ikke i databasen");
	}
	
	public void opretBruger(Bruger bruger) throws DBConnectException, DALException, SQLException {
		

		String statement = "INSERT INTO BRUGERE(boligforening_id, fornavn, mellemnavn, efternavn, antalNøgler) VALUES('" 
		+ bruger.getBoligForeningID() + "','" 
				+ bruger.getNavn() +"', NULL,'" + bruger.getNavn() + "', "
				+ bruger.getANTAL_NOEGLER() + ");" ;
		
		DBConnector.doUpdate(statement);

	

	}

	public Bruger login(int id, String pass) throws DBConnectException, DALException, SQLException  {
	
		String statement = "SELECT * FROM LOGIN WHERE bruger_id = " + id+" AND password = '" + pass +"';";
		
		Bruger bruger = null;
		
			ResultSet rs = DBConnector.doQuery(statement);

			try {
				if(rs.next()){
					bruger = getBruger(id);
				}
			} catch (SQLException e) {
				throw new DALException("Der opstod en fejl i databasen");
			}
			
			if(bruger == null){
				throw new DALException("Bruger ID og password stemmer ikke overens ");
			}
		
		return bruger;
	}

	public void deleteBruger(int brugerID) throws DALException, SQLException {
		
		String statement = "DELETE FROM BRUGERE WHERE bruger_id = " + brugerID+";";
		try {
			DBConnector.doUpdate(statement);
		} catch (DALException e) {
			throw new DALException(e);
				}
		
	}
}

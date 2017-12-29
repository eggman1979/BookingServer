package daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import daoInterfaces.IReservationDAO;
import data.Reservation;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;


public class ReservationDAO implements IReservationDAO{

	public ReservationDAO(){
	
		try {
			new DBConnector();
		
			
		} catch (DBConnectException e) {
			e.printStackTrace();
		}
	}


	public List<Reservation> getAllReservationer(int tavleID) throws DBConnectException, DALException, SQLException {

		List<Reservation> resList = new ArrayList<Reservation>();
		ResultSet rs = null;


		rs = DBConnector.doQuery("SELECT * FROM RESERVATION WHERE boligforening_id =" + tavleID);

		try {
			while(rs.next()){
				resList.add(new Reservation(rs.getInt("reservation_id"), rs.getInt("bruger_id"), rs.getLong("reservation_dato"),rs.getInt("vaske_blok_id"), rs.getInt("boligforening_id"), rs.getInt("tavle_id"),rs.getLong("tilfoejet_dato")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Der opstod en fejl i databasen");
		}


		return resList;
	}


	public void opretReservation(Reservation res) throws DBConnectException, DALException, SQLException{
		if(res != null){

			String reservationCheck = "SELECT * FROM RESERVATION WHERE boligforening_id = "+ res.getBoligforeningID() + " AND reservation_dato =" + res.getDato() + " AND tavle_id = " + res.getTavleID() + " AND vaske_blok_id = "+ res.getvaskeBlokID() +";";
			ResultSet rs = DBConnector.doQuery(reservationCheck);

			try {
				if (rs.isBeforeFirst() ) {    
					throw new DALException("Der findes en reservation allerede");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DALException("Der opstod en fejl i databasen");
			} 
			List<Reservation> resList = getAllReservationer(res.getBoligforeningID());
			int count = 0;
			for (Reservation reservation : resList) {
				if(res.getBrugerID() == reservation.getBrugerID()){
					count++;
				}
			}
			if(count <= 2){

				DBConnector.doUpdate("INSERT INTO RESERVATION(bruger_id, reservation_dato, vaske_blok_id, boligforening_id, tavle_id, tilfoejet_dato) VALUES(" + 
						res.getBrugerID() + "," +
						res.getDato()  + ", " +
						res.getVaskeBlokID() + ", " +
						res.getBoligforeningID() + ", " +
						res.getTavleID() + ", " +
						System.currentTimeMillis() +")");

			}else{
				throw new DALException("Bruger har ingen nøgler tilbage");
			}


		}
	}

	public boolean deleteReservation(int ReservationID) throws DBConnectException, DALException, SQLException{

		
		try {
			DBConnector.doUpdate("DELETE FROM RESERVATION where reservation_id = " + ReservationID);	
			return true;
		} catch (DALException e) {
			throw new DALException("Der skete en fejl, da reservationen skulle slettes");
		}





	}

	public List<Reservation> getReservationer(int boligforeningID, long tilfoejetDate) throws DBConnectException, DALException, SQLException {

		List<Reservation> resList = new ArrayList<Reservation>();
		ResultSet rs = null;

																							
		rs = DBConnector.doQuery("SELECT * FROM RESERVATION WHERE boligforening_id =" + boligforeningID + " AND tilfoejet_dato"  +" >= " + tilfoejetDate +";"  );

		try {
			while(rs.next()){
				resList.add(new Reservation(rs.getInt("reservation_id"), rs.getInt("bruger_id"), rs.getLong("reservation_dato"),rs.getInt("vaske_blok_id"), rs.getInt("boligforening_id"), rs.getInt("tavle_id"),rs.getLong("tilfoejet_dato")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Der opstod en fejl i databasen");
		}
		return resList;
	}
	
	public List<Reservation> getReservationTilDagsDato(long dato, int boligID) throws DALException, SQLException{
		
		String query = "SELECT * FROM RESERVATION WHERE reservation_dato <= " + dato + " AND boligforening_id = " + boligID + ";";
		List<Reservation> resList = new ArrayList<>();
		ResultSet rs = DBConnector.doQuery(query);
		try {
			while(rs.next()){
				resList.add(new Reservation(rs.getInt("reservation_id"), rs.getInt("bruger_id"), rs.getLong("reservation_dato"),rs.getInt("vaske_blok_id"), rs.getInt("boligforening_id"), rs.getInt("tavle_id"),rs.getLong("tilfoejet_dato")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DALException("Der opstod en fejl i databasen");
		}
		
		return resList;
	}
	
	public void reservationBatchDelete(List<Reservation> resList){
		for(Reservation res : resList){
			try {
				deleteReservation(res.getReservationID());
			} catch (DBConnectException | DALException | SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
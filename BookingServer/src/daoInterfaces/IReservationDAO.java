package daoInterfaces;

import java.sql.SQLException;
import java.util.List;
import data.Reservation;
import exceptions.DALException;
import exceptions.DBConnectException;

public interface IReservationDAO {

	public List<Reservation> getAllReservationer(int tavleID) throws DBConnectException, DALException, SQLException;
	public void opretReservation(Reservation res) throws DBConnectException, DALException, SQLException;
	public boolean deleteReservation(int ReservationID) throws DBConnectException, DALException, SQLException;
	public List<Reservation> getReservationer(int boligforeningID, long tilfoejetDate) throws DBConnectException, DALException, SQLException ;
	public List<Reservation> getReservationTilDagsDato(long dato, int boligID) throws DALException, SQLException;
	public void reservationBatchDelete(List<Reservation> resList);

}

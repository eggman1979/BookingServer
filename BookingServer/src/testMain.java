import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import daoImpl.ReservationDAO;
import data.Reservation;
import exceptions.DALException;

public class testMain {

	public static void main(String[] args) throws DALException, SQLException {

		LocalDateTime midnat = LocalDate.now().atStartOfDay();
LocalDateTime forrige = midnat.plusHours(LocalDateTime.now().getHour());
LocalDateTime naeste = forrige.plusHours(1);
	
	System.out.println(naeste.toString());
	}
}

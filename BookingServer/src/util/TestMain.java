package util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import daoImpl.ReservationDAO;
import database.DBConnector;
import exceptions.DALException;

public class TestMain {

	public static void main(String[] args) throws DALException, SQLException {
		LocalDateTime midnat = LocalDate.now().atStartOfDay();
		
	//	System.out.println(" antal reservationer " + new ReservationDAO().getReservationTilDagsDato(midnat.atZone(ZoneId.of("Europe/Copenhagen")).toInstant().toEpochMilli(),3).size());
	    System.out.println(new ReservationOprydning().findGamleReservationer(10).size());
DBConnector.close();
	}

}

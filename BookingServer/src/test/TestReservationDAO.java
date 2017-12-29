package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import daoImpl.BrugerDAO;
import daoImpl.ReservationDAO;
import data.Bruger;
import data.Reservation;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;

public class TestReservationDAO {

	ReservationDAO rDAO;
	Bruger bruger1, bruger2;
	BrugerDAO bDAO;
	Reservation res1,res2,res3,res4; 
	long dato = 1513551600000L;
	int sizeBefore = 0;
	int sizeAfter = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		new DBConnector();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		DBConnector.close();

	}

	@Before
	public void setUp() throws Exception {

		rDAO= new ReservationDAO();
		bDAO = new BrugerDAO();
		bruger1 = bDAO.getBruger(9);
		bruger2 = bDAO.getBruger(10);
	}

	@After
	public void tearDown() throws Exception {

		List<Reservation> list = rDAO.getReservationer(bruger1.getBoligForeningID(), dato);
		for(Reservation res : list){
			rDAO.deleteReservation(res.getReservationID());
		}

		res1 = null;
		res2 = null;
		res3 = null;
		res4 = null;
		sizeBefore = 0;
		sizeAfter = 0;
	}

	//Tester at reservationen kan oprettes
	@Test
	public void testOpretReservation() throws SQLException {
		List<Reservation> resList = null;


		res1= new Reservation(bruger1.getBrugerID(), dato, 10, bruger1.getBoligForeningID(), 1, -1L);
		try {
			sizeBefore = rDAO.getReservationer(2, dato).size();
			rDAO.opretReservation(res1);
			resList = rDAO.getReservationer(2, dato);
			sizeAfter = resList.size();
		} catch (DBConnectException | DALException e) {
			e.printStackTrace();
		}

		for (Reservation reservation : resList) {
			if(res1.equals(reservation)){
				res1 = reservation;
			}
		}
		assertEquals(sizeBefore+1, sizeAfter);
	}

	@Test
	public void testNoegleConstraint() throws SQLException{
		res1= new Reservation(bruger1.getBrugerID(), dato, 1, bruger1.getBoligForeningID(), 1, -1L);
		res2= new Reservation(bruger1.getBrugerID(), dato, 2, bruger1.getBoligForeningID(), 1, -1L);
		res3= new Reservation(bruger1.getBrugerID(), dato, 3, bruger1.getBoligForeningID(), 1, -1L);
		res4= new Reservation(bruger1.getBrugerID(), dato, 4, bruger1.getBoligForeningID(), 1, -1L);

		int count = 0;
		int expected = bruger1.getANTAL_NOEGLER();
		try {
			rDAO.opretReservation(res1);
			count++;
			rDAO.opretReservation(res2);
			count++;
			rDAO.opretReservation(res3);
			count++;
			rDAO.opretReservation(res4);
			count++;
		} catch (DBConnectException e) {

		} catch (DALException e) {
			//Bryder ud af try hvis constraint virker
		}

		assertEquals(expected, count);
	}

	@Test
	public void testDeleteReservation() throws DBConnectException, DALException, SQLException{
		res1= new Reservation(bruger1.getBrugerID(), dato, 1, bruger1.getBoligForeningID(), 1, -1L);

		rDAO.opretReservation(res1);
		List<Reservation> list =  rDAO.getReservationer(2, dato);
		Reservation sletRes = list.get(0);
		sizeBefore = list.size();
		rDAO.deleteReservation(sletRes.getReservationID());
		sizeAfter =  rDAO.getReservationer(2, dato).size();

		assertEquals(sizeBefore, sizeAfter+1);
	}

	@Test
	public void testduplikatReservationer() throws DBConnectException, DALException {
		res1= new Reservation(bruger1.getBrugerID(), dato, 1, bruger1.getBoligForeningID(), 1, -1L);
		boolean exceptionThrown = false;

		try{
			rDAO.opretReservation(res1);
			rDAO.opretReservation(res1);
		}catch (Exception e) {
// Hvis der kastes en exception, så fungerer systemet
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);

	}
}

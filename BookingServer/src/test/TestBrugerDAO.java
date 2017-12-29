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
import data.Bruger;
import exceptions.DALException;
import exceptions.DBConnectException;

public class TestBrugerDAO {

	
	BrugerDAO bDAO = new BrugerDAO();


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws DALException, SQLException, DBConnectException  {
             
		List<Bruger> brugere;
	
			brugere = bDAO.getAllBrugere();
			
			for(Bruger b : brugere){
				if(b.getNavn().contains("testAbe")){
					bDAO.deleteBruger(b.getBrugerID());
				}
			}

	}

	
	
	@Test
	public void testLogin() throws DBConnectException, DALException, SQLException {
		Bruger bruger = bDAO.login(3, "test123");
		assertNotNull(bruger);
	}

	@Test
	public void testLoginFail() throws DBConnectException, SQLException {
		Bruger bruger = null;
		try {
			bruger = bDAO.login(-1, "test123");
		} catch (DALException e) {

		}
		assertNull(bruger);
	}
	@Test
	public void testOpretBruger() throws DBConnectException, DALException, SQLException{
		Bruger bruger = new Bruger(2, "testAbe", 99);
		int expected = bDAO.getAllBrugere().size()+1; 
		bDAO.opretBruger(bruger);
		int actual = bDAO.getAllBrugere().size();
		assertEquals(expected, actual);	
	}
	
	@Test
	public void testDeleteBruger() throws DBConnectException, DALException, SQLException{
		int expected = bDAO.getAllBrugere().size();
		Bruger bruger = new Bruger(2, "testAbe", 99);
		bDAO.opretBruger(bruger);
		int brugerID = -1;
		for(Bruger b : bDAO.getAllBrugere()){
			if(b.getNavn().equals("testAbe testAbe")){
				brugerID = b.getBrugerID();
			}
		}
		bDAO.deleteBruger(brugerID);
		int actual = bDAO.getAllBrugere().size();
		
		assertEquals(expected, actual);
	}

}

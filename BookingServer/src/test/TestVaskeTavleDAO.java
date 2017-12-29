package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import daoImpl.BoligforeningDAO;
import daoImpl.VaskeTavleDAO;
import exceptions.DALException;
import exceptions.DBConnectException;

public class TestVaskeTavleDAO {
	BoligforeningDAO bDAO = new BoligforeningDAO();
	VaskeTavleDAO vDAO = new VaskeTavleDAO();

	@Test
	public void testGetVaskeTavler() throws DBConnectException, DALException, SQLException {
		int expected = bDAO.getBoligForening(2).getAntalTavler();
		int actual = vDAO.getVaskeTavler(2).size();
		
		assertEquals(expected, actual);
	}

}

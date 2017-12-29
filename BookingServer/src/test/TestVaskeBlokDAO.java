package test;

import static org.junit.Assert.*;

import org.junit.Test;

import daoImpl.VaskeTavleDAO;
import daoImpl.VaskeblokDAO;
import exceptions.DALException;
import exceptions.DBConnectException;

public class TestVaskeBlokDAO {

	
	VaskeblokDAO vbDAO = new VaskeblokDAO();
	VaskeTavleDAO vtDAO = new VaskeTavleDAO();
	
	@Test
	public void testGetAllVaskeBlokke() throws DBConnectException, DALException {
		
		int expected = vtDAO.getVaskeTavler(1).get(0).getAntalBlokkePrDag();
		int actual = vbDAO.getAllVaskeBlok(1).size();
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

}

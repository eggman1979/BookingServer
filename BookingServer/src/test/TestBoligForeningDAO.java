package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import daoImpl.BoligforeningDAO;
import data.BoligForening;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;

public class TestBoligForeningDAO {

	BoligForening bf;
	BoligforeningDAO bfDAO = new BoligforeningDAO();

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
		List<BoligForening> bfs = bfDAO.getAllBoligForening();

		for (BoligForening boligForening : bfs) {
			if(boligForening.getNavn().equals("testforening")){
				bfDAO.deleteBoligForening(boligForening.getId());
			}
		}
	}

	@After
	public void tearDown() throws Exception {

		List<BoligForening> bfs = bfDAO.getAllBoligForening();

		for (BoligForening boligForening : bfs) {
			if(boligForening.getNavn().equals("testforening")){
				bfDAO.deleteBoligForening(boligForening.getId());
			}
		}
	}

	@Test
	public void testOpretBF() throws DBConnectException, DALException, SQLException {
		bf = new BoligForening("testforening", 1);
		int expected =  bfDAO.getAllBoligForening().size()+1;
		bfDAO.opretBoligforening(bf);
		int actual = bfDAO.getAllBoligForening().size();
		assertEquals(expected, actual);
	}

	@Test
	public void testDeleteBF() throws DBConnectException, DALException, SQLException {
		bf = new BoligForening("testforening", 1);
		int expected =  bfDAO.getAllBoligForening().size();
		System.out.println(expected);
		bfDAO.opretBoligforening(bf);
		System.out.println(bfDAO.getAllBoligForening().size());
		
		for (BoligForening boligForening : bfDAO.getAllBoligForening()) {
			if(boligForening.getNavn().equals("testforening")){
				bfDAO.deleteBoligForening(boligForening.getId());
			}
		}
			
		int actual = bfDAO.getAllBoligForening().size();
		System.out.println(actual);
		assertEquals(expected, actual);
	}
}


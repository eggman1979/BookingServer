package services;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import com.mysql.jdbc.Driver;
import database.DBConnector;
import exceptions.DBConnectException;
import util.ReservationOprydning;

public class ContextListener
               implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
		        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
		        Driver d = null;
		        while(drivers.hasMoreElements()) {
		          
		              
		                try {
		                	  d = (Driver) drivers.nextElement();
							DriverManager.deregisterDriver(d);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            
		        }
		        System.out.println("Cleaning up connections");
		        AbandonedConnectionCleanupThread.checkedShutdown();		    
		
	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contex initializing");
		try {
			new DBConnector();
			
			new Thread() {
				
				public void run(){
					new ReservationOprydning().rensReservationer();
				}
			}.start();
			
		} catch (DBConnectException e) {
			e.printStackTrace();
		}
		
	}
}

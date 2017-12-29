package services;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import daoImpl.BoligforeningDAO;
import daoInterfaces.IBoligforeningDAO;
import data.BoligForening;
import database.DBConnector;
import exceptions.DALException;
import exceptions.DBConnectException;
import util.DebugLog;

import javax.ws.rs.Produces;  
@Path("/bfService")

public class BoligForeningService {
	IBoligforeningDAO bfDao ;
	public BoligForeningService() throws DBConnectException{
	bfDao= new BoligforeningDAO();	
	}
	
	
	  
	
	@GET 
	@Path("/boligforeninger") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getBoligForeninger() { 
		
		try {
			List<BoligForening> list = bfDao.getAllBoligForening();
			DebugLog.log("Alle boligforeninger bliv hentet");
			return Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod et problem på serveren").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der kunne ikke findes nogle boligforeninger").build();
		} catch (SQLException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Boligforeningen kunne ikke findes").build();
		} 
	}  
	
	
	@GET
	@Path("/boligforeninger/{bfid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBoligForening(@PathParam("bfid") int bfid) {
		try {
			BoligForening bf =  bfDao.getBoligForening(bfid);
			DebugLog.log(bf.getId() + " " +bf.getNavn() + "blev hentet");
			return Response.status(200).entity(bf).build();
		} catch (DBConnectException e) {
			e.printStackTrace();
			return Response.status(500).entity("Der opstod uventede problemer på serveren").build();
		} catch (DALException e) {
			e.printStackTrace();
			return Response.status(500).entity("Boligforeningen kunne ikke findes").build();
		} catch (SQLException e) {
			return Response.status(200).entity("Boligforeningen kunne ikke findes").build();
		}
	}
}



package services;


import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import daoImpl.BrugerDAO;
import daoInterfaces.IBrugerDAO;
import data.Bruger;
import exceptions.DALException;
import exceptions.DBConnectException;
import util.DebugLog;

import javax.ws.rs.Produces;  
@Path("/brService")

public class BrugerService {

	IBrugerDAO brugerDao = new BrugerDAO();  
	@GET 
	@Path("/brugere") 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response getBrugere() { 
		try {
			List<Bruger> list = brugerDao.getAllBrugere();
			DebugLog.log("Alle brugere blev hentet");
			return Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
			e.printStackTrace();
			return Response.status(500).entity("Der opstod en fejl på serveren").build();
		} catch (DALException e) {
			e.printStackTrace();
			return Response.status(500).entity("Brugere kunne ikke findes").build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(200).entity("Boligforeningen kunne ikke findes").build();
		} 
	}  
	
	
	@GET
	@Path("/brugere/{bruger_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBruger(@PathParam("bruger_id") int bruger_id) {
		try {
			Bruger bruger = brugerDao.getBruger(bruger_id);
			DebugLog.log(bruger.getBrugerID() + " " +bruger.getNavn() + "blev hentet");
			return Response.status(200).entity(bruger).build();
		} catch (DBConnectException e) {
	DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl på serveren").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Brugeren kunne ikke findes").build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(200).entity("Boligforeningen kunne ikke findes").build();
		}
	}
	
	
	@GET
	@Path("/brugere/login/{bruger_id}/{password}/{forening}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLogin(@PathParam("bruger_id") int bruger_id,@PathParam("password") String password, @PathParam("forening") String forening) {
		System.out.println("LOGIN FORSØGES");
		 Bruger bruger;
		try {
			bruger = brugerDao.login(bruger_id, password);
			DebugLog.log("Bruger " + bruger.getBrugerID() + " " + bruger.getNavn() + " har logged ind");
			 return Response.status(200).entity(bruger).build();
		} catch (DBConnectException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl på serveren").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Brugeren med det givne brugernavn og password kunne ikke findes").build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(200).entity("Boligforeningen kunne ikke findes").build();
		}
			}

}


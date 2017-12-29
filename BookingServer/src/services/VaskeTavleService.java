package services;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import daoImpl.VaskeTavleDAO;
import daoInterfaces.IVaskeTavleDAO;
import data.VaskeTavle;
import exceptions.DALException;
import exceptions.DBConnectException;
import util.DebugLog;

@Path("/vtService")
public class VaskeTavleService {

	IVaskeTavleDAO vtDao = new VaskeTavleDAO();

	@GET
	@Path("/vasketavler")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVaskeTavler(){
		try {
			List<VaskeTavle> list = vtDao.getAllVaskeTavler();
			DebugLog.log("Der blev hentet alle tavler");
			return Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl, tavler kunne ikke hentes").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl, tavler kunne ikke hentes").build();
		} catch (SQLException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(200).entity("Boligforeningen kunne ikke findes").build();
		}
	}

	@GET
	@Path("/vasketavler/{bfID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVaskeTavler(@PathParam("bfID") int bfID) {
		List<VaskeTavle> list;
		try {
			list = vtDao.getVaskeTavler(bfID);
			DebugLog.log("Der blev hentet tavler fra boligforeningen " + bfID);
			return Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl, tavler kunne ikke hentes til det pågældende boligforening").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Der opstod en fejl, tavler kunne ikke hentes til det pågældende boligforening").build();
		}
	}
	
}

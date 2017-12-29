package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import daoImpl.VaskeblokDAO;
import daoInterfaces.IVaskeBlokDAO;
import data.VaskeBlok;
import exceptions.DALException;
import exceptions.DBConnectException;
import util.DebugLog;

@Path("/vaskebloksservice")
public class VaskeBlokService {

	IVaskeBlokDAO vbDao = new VaskeblokDAO();

	@GET
	@Path("/vaskeblokke/{bfID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVaskeBlokke(@PathParam("bfID") int bfID){
		try {
			List<VaskeBlok> list = vbDao.getAllVaskeBlok(bfID);
			DebugLog.log("Vaskeblokke fra " + bfID + " er hentet");
			return  Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
				DebugLog.log(e.getStackTrace().toString());
				return Response.status(500).entity("Serveren kunne ikke finde vaskeblokke for boligforeningen").build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Serveren kunne ikke finde vaskeblokke for boligforeningen").build();
		}

	}
	
}

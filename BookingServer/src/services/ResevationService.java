package services;


import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import daoImpl.ReservationDAO;
import daoInterfaces.IReservationDAO;
import data.Reservation;
import exceptions.DALException;
import exceptions.DBConnectException;
import util.DebugLog;

@Path("/reservationService")

public class ResevationService {

	IReservationDAO resDAO = new ReservationDAO();

	
	@GET
	@Path("/reservationer/{tavleID}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reservation> getReservationFraTavle(@PathParam("tavleID") int tavleID) throws Exception{
		return resDAO.getAllReservationer(tavleID);
	}

	@GET
	@Path("/reservationer/{boligID}/{tilfoejetDato}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservationBruger( @PathParam("boligID") int boligID, @PathParam("tilfoejetDato") long tilfoejetDato){
	
		List<Reservation> list;
		try {
			list = resDAO.getReservationer(boligID, tilfoejetDato);
			DebugLog.log("Reservationerne fra boligforening " + boligID + " er blevet vist");
			return Response.status(200).entity(list).build();
		} catch (DBConnectException e) {
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity(e.getMessage()).build();
		} catch (DALException e) {
			DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity(e.getMessage()).build();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(200).entity("Der opstod problemer på serveren").build();
		}


	}
//
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/reservationer")
	public Response createReservation(String msg){
		JSONObject obj;
		try {
			obj = new JSONObject(msg);
			long dato = obj.getLong("dato");
			int brugerID = obj.getInt("brugerID");
			int tavleID = obj.getInt("tavleID");
			int vaskeBlokID = obj.getInt("vaskeBlokID");
			int boligforeningID = obj.getInt("boligselskabID");

			Reservation res = new Reservation(brugerID, dato, vaskeBlokID, boligforeningID, tavleID, 0);
			try {
				resDAO.opretReservation(res);
				DebugLog.log("Brugeren " + brugerID + " fra"+ boligforeningID + " har oprettet en reservation ");
				return Response.status(200).entity("Reservation gennemført").type(MediaType.APPLICATION_JSON).build();
			} catch ( Exception e) {
					DebugLog.log(e.getStackTrace().toString());
		
				return Response.status(500).entity(e.getMessage()).type(MediaType.APPLICATION_JSON).build();
			}
		} catch (JSONException e) {
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Reservationen lykkedes ikke").build();
		}

	}

	@DELETE
	@Path("/reservationer/{res_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReservaton(@PathParam("res_id") int ResID){
     
		try {
			//TODO Det kunne være bedre at logge hvilken reservation der er blevet slettet, da det er umuligt at kunne logge hvad og hvem der har slettet
			resDAO.deleteReservation(ResID);
			DebugLog.log("Der blev slettet en reservation");
			return Response.status(200).entity("Reservationen er slettet").type(MediaType.APPLICATION_JSON).build();
		} catch (DBConnectException e) {
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Reservationen blev ikke slettet").build();
		} catch (DALException e) {
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(500).entity("Reservationen blev ikke slettet").build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				DebugLog.log(e.getStackTrace().toString());
			return Response.status(200).entity("Der opstod problkemer på serveren").build();
		}
	}
}

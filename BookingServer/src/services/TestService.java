package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/testService")
public class TestService {

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response test(){
		return Response.status(200).entity("Der er forbindelse til serveren").build();
		
	}
	

}

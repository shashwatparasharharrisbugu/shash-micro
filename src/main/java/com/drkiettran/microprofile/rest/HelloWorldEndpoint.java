package com.drkiettran.microprofile.rest;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

@Path("/greetings")
public class HelloWorldEndpoint {

	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(HelloWorldEndpoint.class);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{year}/{month}/{day}")
	public Response doGetDate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("day") int day) {

		String date = year + "/" + month + "/" + day;
		Message message = new Message();
		message.setName("Thorntail");
		message.setMessage(date);
		
		// Adding current date to the message.
		Date todaysDate = new Date();
		message.setDate(todaysDate);
		LOGGER.log(Level.INFO, "Added current date to message object");
		
		return Response.status(200).entity(message).build();
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{year}/{month}/{day}")
	public Response doPutDate(@PathParam("year") int year, @PathParam("month") int month, @PathParam("day") int day,
			Message inMessage) throws UnknownHostException {
		System.out.println("inMessage: " + inMessage.getName() + ":" + inMessage.getMessage());

		String date = year + "/" + month + "/" + day;
		Message message = new Message();
		Greetings greetings = new Greetings();
		
		// Adding current date to the object.
		Date todaysDate = new Date();
		message.setDate(todaysDate);
		LOGGER.log(Level.INFO, "Added current date to message object");
		
		message.setName(inMessage.getName());
		message.setMessage(greetings.hello(inMessage.getName()));
		return Response.status(200).entity(message).build();
	}
}

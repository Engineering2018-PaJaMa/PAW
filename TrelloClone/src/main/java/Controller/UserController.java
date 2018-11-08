package Controller;

import java.util.Set;

import javax.annotation.security.PermitAll;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Representation.User;
import io.dropwizard.auth.Auth;



@Path("/user/{id}")
@Produces(MediaType.APPLICATION_JSON)
public class UserController
{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final Validator validator;

	public UserController(final Validator validator)
	{
		this.validator = validator;
	}

	@GET
	public String getUser()
	{
		LOGGER.info("get");
		return "User ";
	}

	@POST
	@Path("/register")
	public String register(@FormParam("username") String username, @FormParam("password") String password)
	{
		LOGGER.info("recive data");
		return "User " + username + ", Password: "+ password;
	}

	@POST
	@Path("/postparam")
	public String postParam(@FormParam("message") String message) {
		return "You posted " + message;
	}

}

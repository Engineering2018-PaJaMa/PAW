package Controller;

import java.io.IOException;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

import Converter.Converter;
import MongoDB.DatabaseController;

/**
 * Created by Paweł Szopa on 15/11/2018
 */
public interface EndpointController
{
	DatabaseController databaseController = new DatabaseController().setUpConnection();
	ObjectMapper objectMapper = new ObjectMapper();
	Converter converter = new Converter();

	@GET
	@Path("/")
	Document getAll();

	Optional<Document> get(String name);

	Object create(String json) throws IOException;

	Document delete(String name);
}

package Controller;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;

import Representation.DTO.Card;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardController implements EndpointController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private MongoCollection<Document> history;
	private Logger logger;

	public CardController(final Validator validator)
	{
		this.validator = validator;
		collection = databaseController.getCollection("cards");
		history = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(CardController.class);
	}

	@GET
	@Override
	public List<Document> getAll()
	{
		logger.info("Returning all cards");
		List<Document> cards = new ArrayList<>();

		for (Document d : collection.find())
		{
			cards.add(d);
		}
		return cards;
	}

	@GET
	@Path("/{name}")
	@Override
	public Optional<Document> get(@PathParam("name") final String name)
	{
		logger.info("Returning info from database for card {}", name);
		return Optional.ofNullable(collection.find(eq("name", name)).first());
	}

	@POST
	@Path("/create")
	@Override
	public Card create(final String json) throws IOException
	{
		Card card = objectMapper.readValue(json, Card.class);
		logger.info("Creating card {}", card.getName());
		history.insertOne(new Document("change", "Created card with name" + card.getName()));
		collection.insertOne(converter.convert(card));
		return card;
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting card {}", name);
		history.insertOne(new Document("change", "Deleted card with name" + name));
		return collection.findOneAndDelete(eq("name", name));
	}
}

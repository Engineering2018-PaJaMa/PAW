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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;

import Representation.DTO.Card;
import Representation.DTO.CardProperties;
import Representation.Visibility;

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
	@Path("/listParent/{listId}")
	@Override
	public List<Document> getByParentId(@PathParam("listId") Integer parentID) {
		List<Document> cards = new ArrayList<>();

		for (Document d : collection.find(eq("listId", parentID)))
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
		return Optional.ofNullable(collection.find(eq("name ", name)).first());
	}

	@POST
	@Path("/rename/{name}")
	public Card rename(@PathParam("name") final String name, final String json) throws IOException
	{
		Card card = objectMapper.readValue(json, Card.class);
		logger.info("Renaming card {}", name);
		history.insertOne(new Document("change", "Renaming card with name " + name + " to " + card.getName()));
		collection.findOneAndUpdate(new Document("name", name), new Document("$set", new Document("name", card.getName())));
		return card;
	}

	@POST
	@Path("/create")
	@Override
	public Card create(final String json) throws IOException
	{
		Card card = objectMapper.readValue(json, Card.class);
		logger.info("Creating card {}", card.getName());
		history.insertOne(new Document("change", "Created card with name " + card.getName()));
		collection.insertOne(converter.convert(card));
		return card;
	}

	@POST
	@Path("/modify")
	public Card modify(final String json) throws IOException
	{
		Card card = objectMapper.readValue(json, Card.class);
		logger.info("Modifying card {}", card.getName());
		history.insertOne(new Document("change", "Modifying card with name " + card.getName()));
		collection.findOneAndUpdate(new Document("name", card.getName()), new Document("$set", new Document("description", card.getDescription())));
		return card;
	}

	@PUT
	@Path("/archive/{name}")
	public Document archive(@PathParam("name") final String name)
	{
		logger.info("Archiving card {}", name);
		history.insertOne(new Document("change", "Archived card with name " + name));
		return collection.findOneAndUpdate(new Document("name", name), new Document("$set", new Document("visibility", Visibility.ARCHIVED.name())));
	}

	@PUT
	@Path("/position")
	public Document position(final String json) throws IOException
	{
		CardProperties cardProperties = objectMapper.readValue(json, CardProperties.class);
		logger.info("Positioning card {}", cardProperties.getCardName());
		history.insertOne(new Document("change", "Positioned card with name " + cardProperties.getCardName() + "to position " + cardProperties.getPosition()));
		return collection.findOneAndUpdate(
				new Document("name", cardProperties.getCardName()),
				new Document("$set", new Document("position", String.valueOf(cardProperties.getPosition()))));
	}

	@DELETE
	@Path("/{name}")
	@Override
	public Document delete(@PathParam("name") final String name)
	{
		logger.info("Deleting card {}", name);
		history.insertOne(new Document("change", "Deleted card with name " + name));
		return collection.findOneAndDelete(eq("name", name));
	}
}

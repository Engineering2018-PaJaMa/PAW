import java.time.LocalDate;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Converter.Converter;
import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.Listing;

public class ConnectDatabase
{
	private static Converter converter = new Converter();
	private static MongoClient mongoClient = MongoClients.create();
	private static MongoDatabase database = mongoClient.getDatabase("TrelloClone");

	public static void main(final String[] args)
	{
		addTestUser();
		addTestBoard();
	}

	private static void addTestUser()
	{
		database.createCollection("users");
		MongoCollection<Document> collection = database.getCollection("users");
		Document document = new Document("username", "admin").append("password", "admin").append("registerDate", LocalDate.now().toString());
		collection.insertOne(document);
	}

	private static void addTestBoard()
	{
		database.createCollection("boards");
		MongoCollection<Document> collection = database.getCollection("boards");

		Board testBoard = new Board(1, "nameBoard", "stateBoard", "visibilityBoard", 1, List.of(new Listing(1,
				"titleList",
				"descList",
				5,
				1, "stateList", "visibilityList", new Card(1, "titleCard", "descCard", 5, 1, new Comment(1, "titleComment", "messageComment")))));

		collection.insertOne(converter.convert(testBoard));
	}
}

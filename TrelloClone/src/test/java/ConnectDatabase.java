import java.time.LocalDate;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Converter.Converter;
import Representation.DTO.Board;
import Representation.DTO.Card;
import Representation.DTO.Comment;
import Representation.DTO.List;
import Representation.Visibility;

public class ConnectDatabase
{
	private static Converter converter = new Converter();
	private static MongoClient mongoClient = MongoClients.create();
	private static MongoDatabase database = mongoClient.getDatabase("TrelloClone");

	public static void main(final String[] args)
	{
		addTestUser();
		addTestBoard();
		addTestList();
		addTestCard();
		addTestComment();
		addTestHistory();
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
		Board testBoard = new Board(1, 1, "boardName", "boardDescription", Visibility.VISIBLE);
		collection.insertOne(converter.convert(testBoard));
	}

	private static void addTestList()
	{
		database.createCollection("lists");
		MongoCollection<Document> collection = database.getCollection("lists");
		List list = new List(1, 1, "listName", "listDesc", 1, Visibility.VISIBLE);
		collection.insertOne(converter.convert(list));
	}

	private static void addTestCard()
	{
		database.createCollection("cards");
		MongoCollection<Document> collection = database.getCollection("cards");
		Card card = new Card(1, 1, "cardName", "cardDescription", 1, Visibility.VISIBLE);
		collection.insertOne(converter.convert(card));
	}

	private static void addTestComment()
	{
		database.createCollection("comments");
		MongoCollection<Document> collection = database.getCollection("comments");
		Comment comment = new Comment(1, 1, "commentName", "commentDescription");
		collection.insertOne(converter.convert(comment));
	}

	private static void addTestHistory()
	{
		database.createCollection("history");
		MongoCollection<Document> collection = database.getCollection("history");
		Document document = new Document("change", "changingNothingToSomethingDuuh");
		collection.insertOne(document);
	}
}

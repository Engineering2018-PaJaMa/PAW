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
		Board testBoard = new Board(1, 1, "boardName", "boardDescription", "boardState", "boardVisibility");
		collection.insertOne(converter.convert(testBoard));
	}

	private static void addTestList()
	{
		database.createCollection("lists");
		MongoCollection<Document> collection = database.getCollection("lists");
		List list = new List(1, 1, "listName", "listDesc", 1, "listState", "listVisibility");
		collection.insertOne(converter.convert(list));
	}

	private static void addTestCard()
	{
		database.createCollection("cards");
		MongoCollection<Document> collection = database.getCollection("cards");
		Card card = new Card(1, 1, "cardName", "cardDescription", 1, "cardState", "cardVisibility");
		collection.insertOne(converter.convert(card));
	}

	private static void addTestComment()
	{
		database.createCollection("comments");
		MongoCollection<Document> collection = database.getCollection("comments");
		Comment comment = new Comment(1, 1, "commentName", "commentDescription");
		collection.insertOne(converter.convert(comment));
	}
}

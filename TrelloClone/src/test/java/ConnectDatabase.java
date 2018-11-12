import java.time.LocalDate;
import java.util.Set;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Converter.Converter;
import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.List;

public class ConnectDatabase
{
	private static Converter converter = new Converter();
	private static MongoClient mongoClient = MongoClients.create();
	private static MongoDatabase database = mongoClient.getDatabase("TrelloClone");

	public static void main(final String[] args)
	{
		createConnection();
		addTestUser();
		addTestBoard();
	}

	public static void createConnection()
	{
		database.createCollection("users");//, new CreateCollectionOptions().validationOptions(usersOptions));
		database.createCollection("boards");
	}

	private static void addTestUser()
	{
		MongoCollection<Document> collection = database.getCollection("users");
		Document document = new Document("username", "admin").append("password", "admin").append("registerDate", LocalDate.now().toString());
		collection.insertOne(document);
	}

	private static void addTestBoard()
	{
		MongoCollection<Document> collection = database.getCollection("boards");

		Board testBoard = new Board(1, "nameBoard", "stateBoard", "visibilityBoard", 1,
				Set.of(new List(1,
				"titleList",
				"descList",
				5,
				1,
				"stateList",
				"visibilityList", new Card(1, "titleCard", "descCard", 5, 1, new Comment(1, "titleComment", "messageComment",1)))));

		Document document = converter.convert(testBoard);
		collection.insertOne(document);

//		Document testDoc = new Document("aaaa", "aaa");
//		collection.insertOne(testDoc);
	}
}

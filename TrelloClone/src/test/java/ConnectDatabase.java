import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;

import Representation.Board;
import Representation.Card;
import Representation.Comment;
import Representation.List;

public class ConnectDatabase
{
	public static void main(final String[] args)
	{
		createConnection();
		addTestUser();
		addTestBoard();
		addTestList();
	}

	public static void createConnection()
	{
		// Creating a Mongo client
		CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				fromProviders(PojoCodecProvider.builder().automatic(true).build()));

		MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
		MongoClient mongoClient = MongoClients.create(settings);

		// Creating Credentials
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "TrelloClone", "password".toCharArray());

		// Accessing the database
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		System.out.println("Credentials ::" + credential);

		//Create Collection
		ValidationOptions usersOptions = new ValidationOptions().validator(Filters.or(Filters.exists("username"),
				Filters.exists("password"),
				Filters.exists("role"),
				Filters.exists("registerDate")));
		database.createCollection("users", new CreateCollectionOptions().validationOptions(usersOptions));
		database.createCollection("boards");
		database.createCollection("lists");
		database.createCollection("cards");
		database.createCollection("comments");
	}

	private static void addTestUser()
	{
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<Document> collection = database.getCollection("users");
		Document document = new Document("username", "admin").append("password", "admin").append("registerDate", "08.11.2018");
		collection.insertOne(document);
	}

	private static void addTestBoard()
	{
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<Board> collection = database.getCollection("boards", Board.class);
		//To jest fajne. Potrzebujemy tylko jakiegos modelMappera, zeby przerzucal pola z POJO'sow na pola do BSON'a
//		Mam pomysl jak to zrobic. Statyczny model mapper z metodami typu convert. Zwracajacy z boarda document. Kurwa sztos
		//TODO Model mapper Board.class -> Document.class
		Board testBoard = new Board(1, "nameBoard", "stateBoard", "visibilityBoard", 1, new List(1,
				"titleList",
				"descList",
				5,
				1,
				"stateList",
				"visibilityList",
				new Card(1, "titleCard", "descCard", 5, 1, new Comment(1, "titleComment", "messageComment"))));
		collection.insertOne(testBoard);
	}

	private static void addTestList()
	{
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<List> collection = database.getCollection("lists", List.class);

		List testList = new List(11, "listTittle", "listDesc", 1, 10, "listState", "listVisibility", new Card());
		collection.insertOne(testList);
	}
}

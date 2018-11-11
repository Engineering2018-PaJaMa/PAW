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
		System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		System.out.println("Credentials ::" + credential);

		//Create Collection
		ValidationOptions usersOptions = new ValidationOptions().validator(Filters.or(Filters.exists("username"),
				Filters.exists("password"),
				Filters.exists("role"),
				Filters.exists("registerDate")));
		database.createCollection("user", new CreateCollectionOptions().validationOptions(usersOptions));
		database.createCollection("boards");
		System.out.println("Collection created successfully");
	}

	private static void addTestUser()
	{
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<Document> collection = database.getCollection("user");

		Document document = new Document("username", "admin").append("password", "admin").append("registerDate", "08.11.2018");

		collection.insertOne(document);

		MongoCollection<Board> boardCollection = database.getCollection("boards", Board.class);

		Board test = new Board(1,
				"nameBoard",
				"stateBoard",
				"visibilityBoard",
				1,
				new List(1,
						"titleList",
						"descList",
						5,
						1,
						"stateList",
						"visibilityList",
						new Card(1, "titleCard", "descCard", 5, 1, new Comment(1, "titleComment", "messageComment"))));
		boardCollection.insertOne(test);
	}
}

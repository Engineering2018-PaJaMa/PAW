

import static MongoDB.ConnectToDB.database;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.ServerAddress;
import com.mongodb.MongoCredential;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;

import java.util.Arrays;

import org.bson.Document;

public class ConnectDatabase
{


	public static void main(final String[] args)
	{
		createConnection();
		addTestUser();
	}

	public static void createConnection() {
		// Creating a Mongo client
		MongoClient mongoClient = MongoClients.create();

		// Creating Credentials
		MongoCredential credential;
		credential = MongoCredential.createCredential("sampleUser", "TrelloClone", "password".toCharArray());
		System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		System.out.println("Credentials ::"+ credential);

		//Create Collection
		ValidationOptions usersOptions = new ValidationOptions().validator(Filters.or(
				Filters.exists("username"),
				Filters.exists("password"),
				Filters.exists("registerDate")));
		database.createCollection("user", new CreateCollectionOptions().validationOptions(usersOptions));

		System.out.println("Collection created successfully");

	}

	private static void addTestUser() {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("TrelloClone");
		MongoCollection<Document> collection = database.getCollection("user");

		Document document = new Document("username", "admin").append("password","admin").append("registerDate","08.11.2018");

		collection.insertOne(document);
	}



}

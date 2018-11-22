package MongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DatabaseController
{
	private String host = "localhost";
	private int port = 27017;

	private MongoClient mongo;
	private MongoDatabase database;

	public DatabaseController setUpConnection()
	{
		mongo = new MongoClient(new ServerAddress(host, port));
		database = mongo.getDatabase("TrelloClone");
		return this;
	}

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}
}
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

	public void setUpConnection()
	{
		mongo = new MongoClient(new ServerAddress(host, port));
		database = mongo.getDatabase("TrelloClone");
	}

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}

	private void setCollection(String collectionName)
	{
		database.createCollection(collectionName);
	}

	public void setDocument(MongoCollection<Document> collectionForDoc, Document docForSet)
	{
		collectionForDoc.insertOne(docForSet);
	}

	public void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	private void listAllCollection()
	{
		for (String name : database.listCollectionNames())
		{
			System.out.println(name);
		}
	}
}
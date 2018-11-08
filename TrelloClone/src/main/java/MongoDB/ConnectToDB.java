package MongoDB;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectToDB
{
	private static MongoClient mongo = new MongoClient("localhost", 27017);
	private static MongoCredential credential;
	public static MongoDatabase database = mongo.getDatabase("TrelloDB");

	public static void main(String args[])
	{
		creatingCredentials();
		listAllCollection();
	}

	private static void creatingCredentials()
	{
		// Creating Credentials
		credential = MongoCredential.createCredential("admin", "TrelloDB", "admin".toCharArray());
		System.out.println("Connected to the database successfully");

		// Accessing the database
		MongoDatabase database = mongo.getDatabase("myDb");
		System.out.println("Credentials ::" + credential);
	}

	public MongoCollection<Document> getCollection(String collectionName)
	{
		return database.getCollection(collectionName);
	}

	public static void setDocument(MongoCollection<Document> collectionForDoc, Document docForSet)
	{
		collectionForDoc.insertOne(docForSet);
	}

	public static void deleteDocument(MongoCollection<Document> collectionForDoc, Document docForDel)
	{
		collectionForDoc.deleteOne(docForDel);
	}

	private static void listAllCollection()
	{
		for (String name : database.listCollectionNames())
		{
			System.out.println(name);
		}
	}
}
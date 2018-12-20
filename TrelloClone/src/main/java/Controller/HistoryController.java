package Controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.MongoCollection;

import MongoDB.DatabaseController;

/**
 * Created by Paweł Szopa on 20/12/2018
 */
public class HistoryController
{
	private Validator validator;
	private MongoCollection<Document> collection;
	private Logger logger;
	private DatabaseController databaseController;

	public HistoryController(final Validator validator)
	{
		this.validator = validator;
		databaseController = new DatabaseController().setUpConnection();
		collection = databaseController.getCollection("history");
		logger = LoggerFactory.getLogger(HistoryController.class);
	}

	public List<Document> getAll()
	{
		logger.info("Returning all boards");
		List<Document> boards = new ArrayList<>();

		for (Document d : collection.find())
		{
			boards.add(d);
		}
		return boards;
	}
}

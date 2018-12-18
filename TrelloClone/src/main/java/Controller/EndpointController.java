package Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;

import Converter.Converter;
import MongoDB.DatabaseController;

/**
 * Created by Paweł Szopa on 15/11/2018
 */
public interface EndpointController {
    DatabaseController databaseController = new DatabaseController().setUpConnection();
    ObjectMapper objectMapper = new ObjectMapper();
    Converter converter = new Converter();

    List<Document> getAll();

    List<Document> getByParentId(String parentID);

    Optional<Document> get(String name);

    Object create(String json) throws IOException;

    Document delete(String name);
}

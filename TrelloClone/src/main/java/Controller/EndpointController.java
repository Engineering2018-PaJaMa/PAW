package Controller;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import Converter.Converter;
import MongoDB.DatabaseController;

/**
 * Created by Paweł Szopa on 10/11/2018
 */
public abstract class EndpointController
{
	final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	final DatabaseController databaseController = new DatabaseController().setUpConnection();
	final ObjectMapper objectMapper = new ObjectMapper();
	final Converter converter = new Converter();

	final Validator validator;

	public EndpointController(final Validator validator)
	{
		this.validator = validator;
	}
}

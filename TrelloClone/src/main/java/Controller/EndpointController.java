package Controller;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import MongoDB.DatabaseController;

/**
 * Created by Paweł Szopa on 10/11/2018
 */
public abstract class EndpointController
{
	final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	Validator validator;
	DatabaseController databaseController = new DatabaseController().setUpConnection();
}

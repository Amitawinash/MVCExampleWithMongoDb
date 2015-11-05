/**
 * 
 */
package controllers;

import java.util.ArrayList;
import java.util.Map;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistry;

import models.BookInformation;
import models.CarInformation;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author Amit
 *
 */
public class CarController extends Application {

	public static void addCar() {
		MongoClient client = new MongoClient("localhost");
		MongoDatabase database = client.getDatabase("car-db");
		MongoCollection<Document> collection = database
				.getCollection("CarInfo");
		// BookInformation bookInformation = prepareBookModel();
		Document document = new Document();
		Map<String, String> allSimple = params.allSimple();
		for (String key : allSimple.keySet()) {
			document.append(key, params.get(key));
		}
		collection.insertOne(document);
		client.close();
		index();
	}

	private static CarInformation prepareCarModel() {
		//BookInformation bookInformation = new BookInformation();
		CarInformation carIncormation = new CarInformation();
		
		
		carIncormation.setManufacturerName(params.get("ManufacturerName"));
		carIncormation.setPrice(Double.parseDouble(params.get("price")));
		carIncormation.setGear(params.get("gear"));
		carIncormation.setColour(params.get("colour"));
		carIncormation.setModel(params.get("model"));
		carIncormation.setSeat(params.get("seat"));
		return carIncormation;
	
	}

	public static void index() {
		MongoClient client = new MongoClient("localhost");
		MongoDatabase database = client.getDatabase("car-db");
		MongoCollection<Document> collection = database
				.getCollection("CarInfo");
		FindIterable<Document> findIterable = collection.find();
		renderArgs.put("items", findIterable);
		render();
		client.close();
	}

	public static void getCar() {

	}

	public static void updateCar(CarInformation carInformation) {

	}
}
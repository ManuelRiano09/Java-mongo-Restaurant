package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.riano.services.PrincipalService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;

public class CookContoller extends PrincipalService {

    @FXML
    private Button btnCreate;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtCategorie;

    @FXML
    private TextField txtCombo;

    @FXML
    private TextField txtRate;

    @FXML
    private TextArea txtComments;

    @FXML
    private void createCook(){
        DB database = connection();
        DBCollection collection = database.getCollection("Person");

        BasicDBObject query = new BasicDBObject();
        query.put("userName", RestaurantController.userName);

        BasicDBObject document = new BasicDBObject();
        document.put("delivery", new BasicDBObject("menu", txtName.getText()).append("price", txtPrice.getText()).append("categorie", txtCategorie.getText())
                .append("combo", txtCombo.getText()).append("rate", txtRate.getText()).append("comments", txtComments.getText()));

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);
        collection.update(query, updateObject);
        JOptionPane.showMessageDialog(null, "suggestion received");
        saveCookInRestaurant();

    }

    private void saveCookInRestaurant(){

        DB database = connection();
        DBCollection collection = database.getCollection("restaurant");

        BasicDBObject document = new BasicDBObject();
        document.put("comprobant", "restaurant");
        document.put("menu", txtName.getText());
        document.put("price", txtPrice.getText());
        document.put("categorie", txtCategorie.getText());
        document.put("combo", txtCombo.getText());
        document.put("rate", txtRate.getText());
        document.put("comments", txtComments.getText());
        collection.insert(document);

    }
}


package sample;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.riano.services.PrincipalService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class RegisterController extends PrincipalService {

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private void createAccount(){

        DB database = connection();
        DBCollection collection = database.getCollection("Person");
        DBCollection collection1 = database.getCollection("user");

        BasicDBObject document = new BasicDBObject();
        BasicDBObject document1 = new BasicDBObject();
        if (query()){
            if (txtUserName.getText().isEmpty() && txtPassword.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "The ID and password cannot be empty");
            }else{
                document.put("userName", txtUserName.getText());
                document.put("password", txtPassword.getText());
                document.put("email", txtEmail.getText());
                document.put("phone", txtPhone.getText());
                document1.put("userName", txtUserName.getText());
                document1.put("password", txtPassword.getText());
                collection1.insert(document1);
                collection.insert(document);
                JOptionPane.showMessageDialog(null, "User created");
            }
        }else{
            JOptionPane.showMessageDialog(null, "The user already exists");
            clean();
        }
    }

    private boolean query(){
        DB database = connection();
        DBCollection collection = database.getCollection("user");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("userName",txtUserName.getText());
        DBCursor cursor = collection.find(searchQuery);

        if (cursor.hasNext()) {
            return false;
        }else{
            return true;
        }
    }

    @FXML
    public void closeWindow(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.txtPhone.getScene().getWindow();
            myStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clean(){
        txtUserName.setText(null);
        txtPassword.setText(null);
        txtEmail.setText(null);
        txtPhone.setText(null);
    }



}

package sample;


import com.mongodb.*;
import com.riano.services.PrincipalService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class LoginController extends PrincipalService {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    public static String userId ;

    public static String password ;

    public static String email ;

    public static String phone ;
    @FXML
    public void Login(){

            DB database = connection();
            DBCollection collection = database.getCollection("user");
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("userName",txtUserName.getText() );
            searchQuery.put("password", txtPassword.getText());
            DBCursor cursor = collection.find(searchQuery);

            if (cursor.hasNext()) {
                DBObject dbObject = cursor.next();
                String id2 = dbObject.get("userName")+ "";
                String id = dbObject.get("password")+ "";
                goToRestautant();
                userId = txtUserName.getText();
                password = txtPassword.getText();
//                return id2;
            }else{
                JOptionPane.showMessageDialog(null, "User name or password incorrect");
            }
//        return null;
    }

    @FXML
    private void createAccount(){
        closeWindow();
    }

    public void closeWindow(){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterForm.fxml"));
            Parent root = loader.load();

            RegisterController controllador = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            //stage.setOnCloseRequest(e -> controllador.closeWindow());
            Stage myStage = (Stage) this.btnLogin.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void goToRestautant(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();


            //stage.setOnCloseRequest(e -> controllador.closeWindow());
            Stage myStage = (Stage) this.txtPassword.getScene().getWindow();
            myStage.close();




        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





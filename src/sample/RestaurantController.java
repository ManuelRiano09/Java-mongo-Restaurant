package sample;

import com.mongodb.*;
import com.riano.entities.MenuEntity;
import com.riano.services.PrincipalService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class RestaurantController extends PrincipalService {

    @FXML
     TableView<MenuEntity> tableFood;

    @FXML
    TableColumn colMenu;

    @FXML
    TableColumn colPrice;

    @FXML
    TableColumn colCategorie;

    @FXML
    TableColumn colCombo;

    @FXML
    TableColumn colRate ;

    @FXML
    private Button btn;

    @FXML
    private Button btnCallUser;

    @FXML
    public Label lblUserName;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhone;

    @FXML
    private Label lblUserName2;

    @FXML
    private Button btnUpdate;

    private ObservableList<MenuEntity> menus;

    public static String userName;


    @FXML
    private void addInfo(){
        menus = FXCollections.observableArrayList();
        colMenu.setCellValueFactory(new PropertyValueFactory("menu"));
        colPrice.setCellValueFactory(new PropertyValueFactory("price"));
        colCategorie.setCellValueFactory(new PropertyValueFactory("categorie"));
        colCombo.setCellValueFactory(new PropertyValueFactory("combo"));
        colRate.setCellValueFactory(new PropertyValueFactory("rate"));

//        MenuEntity combo1 = new MenuEntity("Hamburguesa", "1234", "Hamburguers", "papas", "4.5");
//        users.add(combo1);
        DB database = connection();
        DBCollection collection = database.getCollection("restaurant");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("comprobant", "restaurant" );
        DBCursor cursor = collection.find(searchQuery);

        if (cursor.hasNext()) {
            for (int i = 0; i< cursor.count(); i++){
                final MenuEntity combo1 = new MenuEntity();
                DBObject dbObject = cursor.next();
                String menu = dbObject.get("menu") + "";
                String price = dbObject.get("price") + "";
                String categorie = dbObject.get("categorie") + "";

                String combo = dbObject.get("combo") + "";
                String rate = dbObject.get("rate") + "";
                combo1.setMenu(menu);
                combo1.setPrice(price);
                combo1.setCategorie(categorie);
                combo1.setCombo(combo);
                combo1.setRate(rate);

                menus.add(combo1);
                tableFood.setItems(menus);
            }

        }else{
            JOptionPane.showMessageDialog(null, "no hay comidas");
        }
    }

    @FXML
    private void callUserName(){
        String userId = LoginController.userId;
        String password = LoginController.password;
        lblUserName.setText(userId);
        lblUserName2.setText(userId);
        txtUserName.setText(userId);
        txtPassword.setText(password);
        userName = lblUserName2.getText();
        this.btnCallUser.setVisible(false);
    }

    @FXML
    private void logOut(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            //stage.setOnCloseRequest(e -> controllador.closeWindow());
            Stage myStage = (Stage) this.lblUserName.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeAccount(){
        remove(this.lblUserName2.getText());
        JOptionPane.showMessageDialog(null, "Usser account deleted");
        logOut();
    }

    @FXML
    private void updateAccount(){
        update(lblUserName2.getText(), txtPassword.getText(), txtEmail.getText(), txtPhone.getText() , txtUserName.getText());
        lblUserName2.setText(txtUserName.getText());
        lblUserName.setText(txtUserName.getText());
        userName = lblUserName2.getText();
        JOptionPane.showMessageDialog(null, "Usser Updated");
    }

    @FXML
    private void goToCook(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CookForm.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

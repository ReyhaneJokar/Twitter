package client.resources;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeController extends Controller{

    @FXML
    private ImageView chatImageview;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView homeImageview;

    @FXML
    private ImageView searchImageview;

    @FXML
    private ImageView userImageview;

    @FXML
    void chatImageviewPressed(MouseEvent event) {

    }

    @FXML
    void searchImageviewPressed(MouseEvent event) {

    }

    @FXML
    void userImageviewPressed(MouseEvent event) {
        changeView("profile", event).getController();
        //newStageMaker("profile");

    }


//    public FXMLLoader newStageMaker(String fxmlFile){
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/" + fxmlFile + ".fxml"));
//
//        try {
//
//            Scene scene = new Scene(loader.load());
//            scene.setFill(Color.TRANSPARENT);
//
//            Stage stage = new Stage();
//            stage.setResizable(false);
//            stage.setMinWidth(500);
//            stage.setMinHeight(600);
//
////            stage.initStyle(StageStyle.UNDECORATED);
////            stage.initStyle(StageStyle.TRANSPARENT);
//
//            stage.setScene(scene);
//
//            stage.show();
//        }
//        catch(IOException e){
//            e.printStackTrace();
//        }
//
//        return loader;
//    }
}


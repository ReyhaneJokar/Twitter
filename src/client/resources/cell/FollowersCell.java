package client.resources.cell;

import client.ClientThread;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Response;
import model.exception.ResponseNotFoundException;
import model.exception.UserNotFoundException;
import model.request.user.FollowReq;
import model.user.User;

public class FollowersCell extends AnchorPane {

    protected final ClientThread clientThread = ClientThread.getClientThread();

    private User user;
    private Circle profileCircle;
    private Label nameLabel;
    private Label idLabel;
    private Button followButton;

    public FollowersCell(User user) {
        this.user = user;
        profileCircle = new Circle(27);
        profileCircle.setStroke(Color.GRAY);
        profileCircle.setFill(Color.SNOW);
        if (user.getProfile().getAvatar() != null){
            profileCircle.setFill(new ImagePattern(user.getProfile().getAvatar()));
        }
        nameLabel = new Label(user.getName());
        idLabel = new Label(user.getId());
        followButton = new Button("Follow");
        this.getChildren().addAll(profileCircle , nameLabel , idLabel , followButton);
        setConfig();
        setLocation();
        setActions();
    }

    private void setConfig(){
        this.setPrefSize(470,68);
        this.setMinSize(470,68);
        this.setMaxSize(470,68);
        this.setStyle("-fx-background-color:white;" + "-fx-border-color:#3E3B3B");

        nameLabel.setFont(Font.font("Arial Rounded MT Bold", FontWeight.BOLD,21));
        nameLabel.setStyle("-fx-text-fill:black;");
        idLabel.setFont(Font.font("Roboto",14));
        idLabel.setStyle("-fx-text-fill:gray;");

        followButton.setPrefSize(80,25);
        followButton.setTextFill(Paint.valueOf("#0da5f3"));
        followButton.setStyle("-fx-background-radius: 50");
        followButton.setStyle("-fx-background-color:#fafafa;" + "-fx-border-color:#0da5f3");

    }

    private void setLocation(){
        AnchorPane.setTopAnchor(profileCircle,8.0);
        AnchorPane.setLeftAnchor(profileCircle,10.0);

        AnchorPane.setTopAnchor(nameLabel,12.0);
        AnchorPane.setLeftAnchor(nameLabel,70.0);
        AnchorPane.setTopAnchor(idLabel,40.0);
        AnchorPane.setLeftAnchor(idLabel,70.0);
        AnchorPane.setTopAnchor(followButton , 20.0);
        AnchorPane.setRightAnchor(followButton , 20.0);
    }

    private void setActions(){
        followButton.setOnAction(event -> {
            //follow user
            clientThread.send(new FollowReq(clientThread.getId(), idLabel.getText()));
            try {
                Response response = clientThread.getReceiver().getResponse();

                if(response.isAccepted()) {

                    followButton.setText("Followed!");
                    followButton.setTextFill(Paint.valueOf("white"));
                    followButton.setStyle("-fx-background-color:#0da5f3;");
                }
                else{
                    throw new UserNotFoundException();
                }

            } catch (ResponseNotFoundException | UserNotFoundException e) {
                e.printStackTrace();
            }

        });
    }
}

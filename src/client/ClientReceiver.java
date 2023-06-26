package client;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Response;
import model.exception.InvalidObjectException;
import model.exception.ResponseNotFoundException;
import client.resources.FxClient;

public class ClientReceiver {
    //the latest response that was arrived from server
    private Response response;

    private FXMLLoader loader;
    private Stage stage;

    public ClientReceiver() {
        loader = new FXMLLoader(FxClient.class.getResource("Login.fxml"));
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    /**
     * response from server might have some delay
     * because of that, there is a loop in the method runs after 50 m seconds
     * which updates the socket to see if anything is received from server.
     */
    public synchronized Response getResponse() throws ResponseNotFoundException {
        for (int i = 0; i < 1000; i++) {
            if (this.response != null) {
                Response response = this.response;
                //setting the response field to null, to store next responses
                this.response = null;
                return response;
            } else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new ResponseNotFoundException();
    }


    public void getInput(Response response) throws InvalidObjectException {
        try {
            this.response = response;
        } catch (ClassCastException e) {
//            try {
//                receiveMessage((Message) transferable);
//
//                Platform.runLater(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        HomeController controller = getLoader().getController();
//                        controller.realTimeUpdate((Message) transferable);
//                    }
//                });

//            }
//            catch (ClassCastException ex)
//            {
//                throw new InvalidObjectException();
//            }
//            catch (IllegalArgumentException | NullPointerException | IllegalStateException ex)
//            {
//
//            }

//        }
        }
    }
}

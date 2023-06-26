package connect.api;

import connect.api.server.ConnectionHandler;
import connect.service.AuthenticationService;
import model.Response;
import model.exception.InvalidTypeException;
import model.exception.UserThreadNotFoundException;
import model.request.Authentication.AuthenticationReq;
import model.request.Authentication.LogInReq;
import model.request.Authentication.SignUpReq;

public class AuthenticationApi {

    private final AuthenticationService service;
    //sender object to send responses to client
    private final Sender sender;

    protected AuthenticationApi() {
        service = new AuthenticationService();
        sender = Sender.getSender();
    }

    protected void getRequest(AuthenticationReq request) throws InvalidTypeException {
        switch(request.getSubType()) {
            case LOGIN:
                login((LogInReq) request);
                break;
            case SIGN_UP:
                signup((SignUpReq) request);
                break;
            case LOG_OUT:
                System.out.println("not implemented yet");
                break;
            default:
                throw new InvalidTypeException();
        }
    }


    private void login(LogInReq request)
    {
        handleResponse(service.login(request) , request);
    }

    private void signup(SignUpReq request)
    {
        handleResponse(service.signup(request) , request);
    }

    private void handleResponse(Response response , AuthenticationReq request){
        try {
            sender.sendResponse(response , request.getUserThread());
        }
        catch (UserThreadNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if(response.isAccepted()) {
            //add new client to connections list
            ConnectionHandler.getConnectionHandler().addConnection(request.getSenderId() , request.getUserThread());

            //set serverThreads id
            request.getUserThread().verified(request.getSenderId());
        }
    }










}

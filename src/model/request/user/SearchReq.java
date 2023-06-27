package model.request.user;

public class SearchReq extends UserRequest{
    private final String searchText;

    public SearchReq(String senderId, String searchText) {
        super(senderId, UserRequestType.SEARCH);
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

}

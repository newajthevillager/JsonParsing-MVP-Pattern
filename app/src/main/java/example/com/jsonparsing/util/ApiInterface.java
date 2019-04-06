package example.com.jsonparsing.util;

import java.util.List;

import example.com.jsonparsing.model.User;
import example.com.jsonparsing.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("?results=50")
    Call<UserList> fetchUserList();

}

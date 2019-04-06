package example.com.jsonparsing.main_activity;

import java.util.ArrayList;
import java.util.List;

import example.com.jsonparsing.model.User;
import example.com.jsonparsing.model.UserList;
import example.com.jsonparsing.util.ApiClient;
import example.com.jsonparsing.util.ApiInterface;
import example.com.jsonparsing.util.MyAppUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityIntractorImpl implements MainActivityContract.IIntractor {

    MyAppUtil myAppUtil;
    ArrayList<User> users;

    public MainActivityIntractorImpl(MyAppUtil myAppUtil) {
        this.myAppUtil = myAppUtil;
    }

    @Override
    public void getNetworkState(OnNetworkListener onNetworkListener) {
        boolean result = myAppUtil.checkConnection();
        if (result) {
            onNetworkListener.onAvailable();
        } else {
            onNetworkListener.onNoNetwork();
        }
    }

    @Override
    public void getUsersList(final OnGetUsersListener onGetUsersListener) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserList> call = apiInterface.fetchUserList();

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                users = response.body().getUsers();
                onGetUsersListener.onSuccess(users);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                onGetUsersListener.onFailure(t.getMessage().toString());
            }
        });

    }
}

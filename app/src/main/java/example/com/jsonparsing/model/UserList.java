package example.com.jsonparsing.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {

    @SerializedName("results")
    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}

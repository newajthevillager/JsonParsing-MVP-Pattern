package example.com.jsonparsing.main_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import example.com.jsonparsing.R;
import example.com.jsonparsing.adapter.UserListAdapter;
import example.com.jsonparsing.details_activity.DetailsActivity;
import example.com.jsonparsing.model.User;
import example.com.jsonparsing.util.MyAppUtil;

public class MainActivity extends AppCompatActivity implements MainActivityContract.IView, MainActivityContract.IView.ItemClickListener {

    RecyclerView recyclerView;
    Button tryAgainBtn;
    ProgressBar progressBar;
    MyAppUtil myAppUtil;
    MainActivityContract.IPresenter presenter;
    UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        myAppUtil = new MyAppUtil(this);
        presenter = new MainActivityPresenterImpl(this, myAppUtil);
        presenter.onNetworkCheck();
    }

    public void initUI() {
        recyclerView = (RecyclerView) findViewById(R.id.rvUsers);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        tryAgainBtn = findViewById(R.id.btnTryAgain);
        progressBar = findViewById(R.id.pbUsers);
    }

    @Override
    public void showProgressbar() {
        if (!progressBar.isShown()) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressbar() {
        if (progressBar.isShown()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTryAgainButton() {
        if (!tryAgainBtn.isShown()) {
            tryAgainBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideTryAgainButton() {
        if (tryAgainBtn.isShown()) {
            tryAgainBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showUsers(List<User> users) {
        Toast.makeText(this, "USers showing", Toast.LENGTH_SHORT).show();
        userListAdapter = new UserListAdapter(users, this);
        recyclerView.setAdapter(userListAdapter);
        for (User user : users) {
            Log.d("HGH", user.getGender());
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Error : " + error, Toast.LENGTH_SHORT).show();
        Log.d("ERR", error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public void tryAgainBtnClick(View view) {
        presenter.onNetworkCheck();
    }

    @Override
    public void navigateToDetailsActivity(User user) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }
}

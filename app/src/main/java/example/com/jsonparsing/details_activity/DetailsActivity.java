package example.com.jsonparsing.details_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import example.com.jsonparsing.R;
import example.com.jsonparsing.model.User;

// no mvp in this activity
public class DetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView nameTv, genderTv, ageTv, emailTv;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent gIntent = getIntent();
        user = (User) gIntent.getSerializableExtra("user");

        initUI();
        displayDetails();
    }

    private void initUI() {
        imageView = findViewById(R.id.ivDetailsImage);
        nameTv = findViewById(R.id.tvdetaisName);
        genderTv = findViewById(R.id.tvdetaisGender);
        ageTv = findViewById(R.id.tvdetaisAge);
        emailTv = findViewById(R.id.tvdetaisEmail);
    }

    public void displayDetails() {
        String url = user.getPicture().getLarge();
        Picasso.get().load(url).into(imageView);
        String name = "Name : " + user.getName().getTitle()
                + " " + user.getName().getFirst()
                + " " + user.getName().getLast();
        nameTv.setText(name);
        genderTv.setText("Gender : " + user.getGender());
        ageTv.setText("Age : " + user.getDateOfBirth().getAge().toString());
        emailTv.setText("E-mail : " + user.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

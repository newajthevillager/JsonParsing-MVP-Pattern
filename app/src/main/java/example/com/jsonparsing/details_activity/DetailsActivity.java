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

import butterknife.BindView;
import butterknife.ButterKnife;
import example.com.jsonparsing.R;
import example.com.jsonparsing.model.User;

// no mvp in this activity
public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.ivDetailsImage)
    ImageView imageView;
    @BindView(R.id.tvdetaisName)
    TextView nameTv;
    @BindView(R.id.tvdetaisGender)
    TextView genderTv;
    @BindView(R.id.tvdetaisAge)
    TextView ageTv;
    @BindView(R.id.tvdetaisEmail)
    TextView emailTv;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent gIntent = getIntent();
        user = (User) gIntent.getSerializableExtra("user");

        displayDetails();
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

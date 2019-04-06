package example.com.jsonparsing.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.com.jsonparsing.R;
import example.com.jsonparsing.main_activity.MainActivityContract;
import example.com.jsonparsing.model.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    List<User> list;
    MainActivityContract.IView.ItemClickListener itemClickListener;

    public UserListAdapter(List<User> list, MainActivityContract.IView.ItemClickListener itemClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_row_user, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String name = list.get(i).getName().getFirst() + " " + list.get(i).getName().getLast();
        String email = list.get(i).getEmail();
        String thumbnailUrl = list.get(i).getPicture().getThumbnail();

        Picasso.get().load(thumbnailUrl).into(viewHolder.thumbnailIv);
        viewHolder.nameTv.setText(name);
        viewHolder.emailTv.setText(email);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.navigateToDetailsActivity(list.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnailIv;
        TextView nameTv;
        TextView emailTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnailIv = itemView.findViewById(R.id.ivUserThumbnail);
            nameTv = itemView.findViewById(R.id.tvUsername);
            emailTv = itemView.findViewById(R.id.tvUserEmail);
        }
    }

}

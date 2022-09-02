package com.example.footballcommunityapp.Community;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.footballcommunityapp.R;
import java.util.List;

public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private List<EachCircle> circlesList;
    private onItemClickListener monItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View circleView;
        TextView username;
        TextView circlecontent;
        TextView time;
        TextView location;
        TextView thumbTextView;
        ImageView imageView;
        ImageView imageViewClock;
        ImageView imagePost;
        ImageView thumbimage;
        public ViewHolder (View view){
            super(view);
            circleView = view;
            //circleImage = (ImageView) view.findViewById(R.id.team_image);
            username = (TextView) view.findViewById(R.id.username);
            circlecontent = (TextView) view.findViewById(R.id.circlecontent);
            time = (TextView) view.findViewById(R.id.time);
            location = (TextView) view.findViewById(R.id.location);
            thumbTextView = (TextView) view.findViewById(R.id.textView22);
            imageView = (ImageView) view.findViewById(R.id.imageView16);
            imageViewClock = (ImageView) view.findViewById(R.id.imageView18);
            imagePost = (ImageView) view.findViewById(R.id.imageView19);
            thumbimage= (ImageView) view.findViewById(R.id.imageView23);
        }
    }

    public CircleAdapter (List<EachCircle> circleList, onItemClickListener onItemClickListener){
        circlesList = circleList;
        this.monItemClickListener = onItemClickListener;
    }

    @Override
    public CircleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.circle_item,parent,false);
        final CircleAdapter.ViewHolder holder = new CircleAdapter.ViewHolder(view);
        holder.circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                holder.thumbimage.setOnClickListener(v -> {
                    monItemClickListener.OnClick(position);
                });
            }
        });
//        holder.circleImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                holder.circleImage.setOnClickListener(v -> {
//                    monItemClickListener.OnClick(position);
//                });
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(CircleAdapter.ViewHolder holder, int position) {
        EachCircle circle = circlesList.get(position);
        holder.location.setText(circle.getLocation());
        holder.time.setText(circle.getTime());
        holder.username.setText(circle.getUserName());
        holder.circlecontent.setText(circle.getCircleContent());
        holder.thumbTextView.setText(circle.getThumbNumber());
        holder.imageView.setImageResource(R.drawable.locationicon);
        holder.imageViewClock.setImageResource(R.drawable.clockicon);
        holder.imagePost.setImageResource(circle.getImagePostId());
        holder.thumbimage.setImageResource(R.drawable.thumb);
        holder.circlecontent.setSelected(true);
        holder.circlecontent.isClickable();
        holder.circlecontent.setVerticalScrollBarEnabled(true);
        holder.circlecontent.setHorizontallyScrolling(false);
        holder.circlecontent.setSingleLine(false);
        ((TextView)holder.circlecontent.findViewById(R.id.circlecontent)).setSelected(true);
    }

    @Override
    public int getItemCount() {
        return circlesList.size();
    }
    public interface onItemClickListener{
        void OnClick(int position);
    }
}
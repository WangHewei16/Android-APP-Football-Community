package com.example.footballcommunityapp.Team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.footballcommunityapp.R;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<EachTeam> mTeamList;
    private onItemClickListener monItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View teamView;
        ImageView teamImage;
        TextView teamName;

        public ViewHolder (View view){
            super(view);
            teamView = view;
            teamImage = (ImageView) view.findViewById(R.id.team_image);
            teamName = (TextView) view.findViewById(R.id.username);
        }
    }

    public TeamAdapter (List <EachTeam> fruitList, onItemClickListener onItemClickListener){
        mTeamList = fruitList;
        this.monItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.teamView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                holder.teamName.setOnClickListener(v -> {
                    monItemClickListener.OnClick(position);
                });
            }
        });
        holder.teamImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                holder.teamImage.setOnClickListener(v -> {
                    monItemClickListener.OnClick(position);
                });
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EachTeam team = mTeamList.get(position);
        holder.teamImage.setImageResource(team.getTeamId());
        holder.teamName.setText(team.getName());
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }
    public interface onItemClickListener{
        void OnClick(int position);
    }
}
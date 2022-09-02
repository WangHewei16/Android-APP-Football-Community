package com.example.footballcommunityapp.GroupChat;

import androidx.recyclerview.widget.RecyclerView;

import com.example.footballcommunityapp.R;
import com.example.footballcommunityapp.Team.EachTeam;
import com.example.footballcommunityapp.Team.TeamAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    private List<EachChat> mChatList;
    private TeamAdapter.onItemClickListener monItemClickListener;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View chatView;
        ImageView chatImage;
        TextView chatContent;
        ImageView chatTrangle;
        TextView chatUsername;
        TextView time;
        public ViewHolder (View view){
            super(view);
            chatView = view;
            chatImage = (ImageView) view.findViewById(R.id.chat_image);
            chatTrangle = (ImageView) view.findViewById(R.id.imageView20);
            chatContent = (TextView) view.findViewById(R.id.chatContent);
            chatUsername = (TextView) view.findViewById(R.id.textView20);
            time = (TextView) view.findViewById(R.id.textView21);
        }
    }

    public ChatAdapter (List <EachChat> fruitList, TeamAdapter.onItemClickListener onItemClickListener){
        mChatList = fruitList;
        this.monItemClickListener = onItemClickListener;
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent,false);
        final ChatAdapter.ViewHolder holder = new ChatAdapter.ViewHolder(view);
//        holder.chatView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                holder.chatName.setOnClickListener(v -> {
//                    monItemClickListener.OnClick(position);
//                });
//            }
//        });
//        holder.teamImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                holder.teamImage.setOnClickListener(v -> {
//                    monItemClickListener.OnClick(position);
//                });
//            }
//        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        EachChat chat = mChatList.get(position);
        holder.chatImage.setImageResource(chat.getChatId());
        holder.chatTrangle.setImageResource(R.drawable.chattrangle2);
        holder.chatContent.setText(chat.getChatContent());
        holder.chatUsername.setText(chat.getChatUsername());
        holder.time.setText(chat.getTime());
    }

    @Override
    public int getItemCount() {
        return mChatList.size();
    }
    public interface onItemClickListener{
        void OnClick(int position);
    }
}

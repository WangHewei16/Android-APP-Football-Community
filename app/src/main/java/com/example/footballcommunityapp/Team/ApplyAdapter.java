package com.example.footballcommunityapp.Team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.footballcommunityapp.R;

import java.util.List;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ViewHolder> implements View.OnClickListener{
    private List<Applier> mApplierList;
    private onItemClickListener monItemClickListener;


    class ViewHolder extends RecyclerView.ViewHolder{
        //TextView applierName
        View applyView;
        private ImageView applierImage;
        private TextView applierName;
        private Button agreeBtn;
        private Button refuseBtn;

        public ViewHolder (View view){
            super(view);
            applyView = view;
            applierName = (TextView) view.findViewById(R.id.username);
            agreeBtn = (Button) view.findViewById(R.id.button13);
            refuseBtn = (Button) view.findViewById(R.id.button14);
            applierImage = (ImageView) view.findViewById(R.id.applier_image);

            applierName.setOnClickListener((View.OnClickListener) ApplyAdapter.this);
            agreeBtn.setOnClickListener((View.OnClickListener) ApplyAdapter.this);
            refuseBtn.setOnClickListener((View.OnClickListener) ApplyAdapter.this);
        }
    }

    public ApplyAdapter (List <Applier> fruitList, onItemClickListener onItemClickListener){
        mApplierList = fruitList;
        this.monItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);

        holder.agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                holder.agreeBtn.setOnClickListener(v -> {
                    monItemClickListener.OnClickAgree(position);
                });
            }
        });

        holder.refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                holder.refuseBtn.setOnClickListener(v -> {
                    monItemClickListener.OnClickRefuse(position);
                });
            }
        });

//        holder.applyView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = holder.getAdapterPosition();
//                holder.applyView.setOnClickListener(v -> {
//                    monItemClickListener.OnClick(position);
//                });
//            }
//        });
        return holder;



    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Applier ap = mApplierList.get(position);
        holder.applierName.setText(ap.getUserName());
        holder.itemView.setTag(position);
        holder.agreeBtn.setTag(position);
        holder.refuseBtn.setTag(position);
        holder.applierImage.setImageResource(ap.getApplierId());
    }

    @Override
    public int getItemCount() {
        return mApplierList.size();
    }
    public interface onItemClickListener{
        void OnClickAgree(int position);

        void OnClickRefuse(int position);
    }

    @Override
    public void onClick(View v) {
//        int position = (int) v.getTag();      //getTag()获取数据
//        if (monItemClickListener != null) {
//            switch (v.getId()){
//                case R.id.rv_recyclerView:
//                    mOnItemClickListener.onItemClick(v, ViewName.PRACTISE, position);
//                    break;
//                default:
//                    mOnItemClickListener.onItemClick(v, ViewName.ITEM, position);
//                    break;
//            }
//        }
    }

}
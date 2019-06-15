package com.example.recyclerviewexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<RecyclerViewItem> mData = new ArrayList<>();

    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public RecyclerAdapter(ArrayList<RecyclerViewItem> list) {
        mData = list;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.item_recycler, parent, false) ;
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view) ;

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        RecyclerViewItem item = mData.get(position);

        viewHolder.image.setImageDrawable(item.getImage());
        viewHolder.title.setText(item.getTitle());
        viewHolder.content.setText(item.getContents());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // item_recycler.xml 에 정의된 내용
        ImageView image;
        TextView title;
        TextView content;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.img_image);
            title = itemView.findViewById(R.id.tv_title);
            content = itemView.findViewById(R.id.tv_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Log.d("RecyclerView", "ViewHolder clicked : " + pos);
                        if (mListener != null) {
                            Log.d("RecyclerView", "custom item click event");
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }
}

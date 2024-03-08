package com.example.appchat.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appchat.Model.Message;
import com.example.appchat.R;
import java.util.List;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ChatModelViewHolder> {
    private Context context;
    private List<Message> list;

    public ChatRecyclerAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChatModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rightmesssage, parent, false);
        return new ChatModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatModelViewHolder holder, int position) {
        Message message = list.get(position);
        holder.rightChatTextview.setText(message.getContent());
        Log.e("TAG", "onBindViewHolder: " + message.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ChatModelViewHolder extends RecyclerView.ViewHolder {
        TextView rightChatTextview;

        public ChatModelViewHolder(@NonNull View itemView) {
            super(itemView);
            rightChatTextview = itemView.findViewById(R.id.right_chat_textview);
        }
    }
}
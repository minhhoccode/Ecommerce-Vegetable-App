// ChatAdapter.java
package com.vungocminh.doanandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ChatMessage> chatMessages;

    public ChatAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == ChatMessage.USER_MESSAGE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_activity, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_item_activity, parent, false);
        }
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MessageViewHolder) holder).bind(chatMessages.get(position));
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessages.get(position).getSenderType();
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTextView;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.message_text_view);
        }

        void bind(ChatMessage chatMessage) {
            messageTextView.setText(chatMessage.getMessage());
        }
    }
}
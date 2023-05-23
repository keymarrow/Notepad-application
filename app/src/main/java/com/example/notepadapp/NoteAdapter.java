package com.example.notepadapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnClickListener onClickListener;

    public NoteAdapter(List<Note> noteList, OnClickListener onClickListener) {
        this.noteList = noteList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_note_item, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(itemView);

        // Set the onClickListener for the item view
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Note note = noteList.get(position);
                    onClickListener.onClick(position, note);
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.subtitleTextView.setText(note.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public interface OnClickListener {
        void onClick(int position, Note note);
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public TextView subtitleTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subtitleTextView = itemView.findViewById(R.id.subtitleTextView);
        }
    }
}



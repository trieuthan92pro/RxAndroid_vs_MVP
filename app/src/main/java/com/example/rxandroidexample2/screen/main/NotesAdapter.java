package com.example.rxandroidexample2.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rxandroidexample2.R;
import com.example.rxandroidexample2.data.model.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<Note> mNotes;

    public NotesAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mLayoutInflater.inflate(R.layout.item_note, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Note note = mNotes.get(i);
        viewHolder.bindData(note);
    }

    @Override
    public int getItemCount() {
        return mNotes == null ? 0 : mNotes.size();
    }

    public void setData(List<Note> notes){
        mNotes = notes;
        notifyDataSetChanged();
    }

    public void addData(Note note){
        mNotes.add(note);
        notifyItemChanged(mNotes.size()-1);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextNote= itemView.findViewById(R.id.text_note);
        }

        public void bindData(Note note){
            mTextNote.setText(note.getNode());
        }
    }
}

package com.example.rxandroidexample2.data;

import com.example.rxandroidexample2.data.model.Note;

import java.util.List;

public interface NoteDataSource {
    void loadNotes(NoteCallback callback);

    interface NoteCallback {
        void onSuccess(List<Note> notes);

        void onFailed(Exception e);
    }
}

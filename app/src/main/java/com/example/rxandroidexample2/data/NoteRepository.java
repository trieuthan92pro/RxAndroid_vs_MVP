package com.example.rxandroidexample2.data;

import com.example.rxandroidexample2.data.source.local.LocalNoteDataSource;

public class NoteRepository implements NoteDataSource {
    private static NoteRepository sNoteRepository;
    private NoteDataSource mLocalNoteDataSource;

    private NoteRepository() {
        mLocalNoteDataSource = LocalNoteDataSource.getInstance();
    }

    public static NoteRepository getInstance() {
        if (sNoteRepository == null) {
            sNoteRepository = new NoteRepository();
        }
        return sNoteRepository;
    }

    @Override
    public void loadNotes(NoteCallback callback) {
        mLocalNoteDataSource.loadNotes(callback);
    }
}

package com.example.rxandroidexample2.data.source.local;

import com.example.rxandroidexample2.data.NoteDataSource;
import com.example.rxandroidexample2.data.model.Note;

import java.util.ArrayList;
import java.util.List;

public class LocalNoteDataSource implements NoteDataSource {
    private static LocalNoteDataSource sLocalNoteDataSource;

    private LocalNoteDataSource() {

    }

    public static LocalNoteDataSource getInstance() {
        if (sLocalNoteDataSource == null) {
            sLocalNoteDataSource = new LocalNoteDataSource();
        }
        return sLocalNoteDataSource;
    }

    private List<Note> prepareNotes() {
        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1, "buy tooth paste!"));
        notes.add(new Note(2, "call brother!"));
        notes.add(new Note(3, "watch narcos tonight!"));
        notes.add(new Note(4, "pay power bill!"));
        notes.add(new Note(5, "beybe where are you now?"));
        notes.add(new Note(6, "can i have a disk of pork?"));
        notes.add(new Note(7, "buy me an ic cream, please!"));
        notes.add(new Note(8, "boom boom boom!"));
        notes.add(new Note(9, "take my hand!"));
        notes.add(new Note(10, "this is my beautiful pet!"));
        return notes;
    }

    @Override
    public void loadNotes(NoteCallback callback) {
        callback.onSuccess(prepareNotes());
    }
}

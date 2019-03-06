package com.example.rxandroidexample2.screen.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.rxandroidexample2.R;
import com.example.rxandroidexample2.data.model.Note;

import java.util.List;

public class MainFragment extends Fragment implements MainContract.View {
    private static final String MSG = "Emit finished!";
    private RecyclerView mRecyclerNotes;
    private NotesAdapter mNotesAdapter;
    private ProgressBar mProgressBar;
    private MainContract.Presenter mPresenter;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mRecyclerNotes = view.findViewById(R.id.recycler_notes);
        mNotesAdapter = new NotesAdapter(getContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerNotes.setLayoutManager(layoutManager);
        mRecyclerNotes.setAdapter(mNotesAdapter);
        mProgressBar = view.findViewById(R.id.progress_bar);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unsubscribe();
    }

    @Override
    public void showProgressBar(boolean isLoading) {
        if (isLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showResult(List<Note> notes) {
        mNotesAdapter.setData(notes);
    }

    @Override
    public void showNext(Note note) {
        mNotesAdapter.addData(note);
    }

    @Override
    public void showFinish() {
        Toast.makeText(getContext(), MSG, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}

package com.example.rxandroidexample2.screen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Preconditions;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.rxandroidexample2.R;
import com.example.rxandroidexample2.data.NoteRepository;
import com.example.rxandroidexample2.data.model.Note;
import com.example.rxandroidexample2.screen.note_detail.NoteDetailPresenter;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private MainPresenter mPresenter;
    private static String MSG = "All items are emitted!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.frame_fragment);//new MainFragment();
        if(mainFragment == null){
            mainFragment = new MainFragment();
            addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.frame_fragment);
        }
        mPresenter = new MainPresenter(mainFragment, NoteRepository.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
}

package com.example.rxandroidexample2.screen.main;

import android.support.annotation.NonNull;

import com.example.rxandroidexample2.data.NoteDataSource;
import com.example.rxandroidexample2.data.model.Note;

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

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;
    private NoteDataSource mNoteDataSource;
    @NonNull
    private CompositeDisposable mCompositeDisposable;

    public MainPresenter(MainContract.View v, NoteDataSource dataSource) {
        mView = v;
        mNoteDataSource = dataSource;
        mCompositeDisposable = new CompositeDisposable();
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mNoteDataSource.loadNotes(new NoteDataSource.NoteCallback() {
            @Override
            public void onSuccess(List<Note> notes) {
                Observable<Note> noteObservable = getNoteObservable(notes);
                DisposableObserver<Note> disposableObserver = getNoteObserverAllCap();
                mCompositeDisposable.add(
                        noteObservable
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(Schedulers.io())
                                .filter(new Predicate<Note>() {
                                    @Override
                                    public boolean test(Note s) throws Exception {
                                        return s.getNode().toLowerCase().startsWith("c");
                                    }
                                })
                                .map(new Function<Note, Note>() {
                                    @Override
                                    public Note apply(Note s) throws Exception {
                                        s.setNode(s.getNode().toUpperCase());
                                        return s;
                                    }
                                })
                                .subscribeWith(disposableObserver));
                mView.showProgressBar(false);
                mView.showResult(notes);
            }

            @Override
            public void onFailed(Exception e) {
                mView.showProgressBar(false);
                mView.showError(e);
            }
        });
    }

    @Override
    public void subscribe() {
        loadData();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void start() {
        mView.showProgressBar(true);
    }

    @Override
    public void stop() {

    }


    private DisposableObserver<Note> getNoteObserverAllCap() {
        return new DisposableObserver<Note>() {
            @Override
            public void onNext(Note s) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {

            }
        };
    }

    private Observable<Note> getNoteObservable(final List<Note> notes) {
        return Observable.create(new ObservableOnSubscribe<Note>() {
            @Override
            public void subscribe(ObservableEmitter<Note> emitter) throws Exception {
                for (Note note : notes) {
                    if (!emitter.isDisposed()) {
                        emitter.onNext(note);
                    }
                }

                if (!emitter.isDisposed()) {
                    emitter.onComplete();
                }
            }
        });
    }
}

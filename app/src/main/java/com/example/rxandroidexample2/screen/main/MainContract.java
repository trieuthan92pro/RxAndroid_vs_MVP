package com.example.rxandroidexample2.screen.main;

import com.example.rxandroidexample2.data.model.Note;
import com.example.rxandroidexample2.screen.BasePresenter;
import com.example.rxandroidexample2.screen.BaseView;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void showProgressBar(boolean isLoading);

        void showResult(List<Note> notes);

        void showError(Exception e);
    }

    interface Presenter extends BasePresenter {
        void loadData();

        void subscribe();

        void unsubscribe();
    }
}

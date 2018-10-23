package com.akhbulatov.githubsample.ui.global;

public interface BasePresenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}

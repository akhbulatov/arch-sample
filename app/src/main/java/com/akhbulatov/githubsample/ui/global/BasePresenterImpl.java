package com.akhbulatov.githubsample.ui.global;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

import retrofit2.Call;

public abstract class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    @Nullable private T view;
    @NonNull private Set<Call<?>> requestSet = new HashSet<>();

    @Override public void attachView(@NonNull T view) {
        this.view = view;
    }

    @Override public void detachView() {
        cancelRequests();
        view = null;
    }

    @Nullable protected T getView() {
        return view;
    }

    protected void addRequest(Call<?> request) {
        requestSet.add(request);
    }

    private void cancelRequests() {
        for (Call<?> request : requestSet) {
            request.cancel();
        }
    }
}

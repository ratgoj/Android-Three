package com.home_task.saprykin.hometask.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.RepoPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableJust;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoryVew {
    private static final String REPO_TAG = "Repository";

    private View repoView;
    private EditText serchRepo;
    private RecyclerView repoRecyclerView;
    private RecyclerView.LayoutManager repoLayoutManager;

    @InjectPresenter
    RepoPresenter presenter;

    public RepositoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repoView = inflater.inflate(R.layout.fragment_repositories, container, false);

        initView(repoView);

        return repoView;
    }

    private void initView(View repoView){
        repoLayoutManager = new LinearLayoutManager(getActivity());
        repoRecyclerView = repoView.findViewById(R.id.repo_list);
        repoRecyclerView.setLayoutManager(repoLayoutManager);
        repoRecyclerView.setAdapter(presenter.getRepoRecyclerAdapter());

        serchRepo = repoView.findViewById(R.id.repo_search);
        TextWatcher searchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

    }


    @Override
    public void onRepoItemClick(int position) {
        Log.d(REPO_TAG, "Click on Position: " + position);
    }
}

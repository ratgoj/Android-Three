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
import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.RepoPresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.RepositoryVew;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends MvpAppCompatFragment implements RepositoryVew {
    private static final String REPO_TAG = "Repository";

    private View repoView;
    private EditText searchRepo;
    private RecyclerView repoRecyclerView;
    private RecyclerView.LayoutManager repoLayoutManager;

    @InjectPresenter(type = PresenterType.GLOBAL) //Указываем type = PresenterType.GLOBAL, что бы презентер не пересозвался вместе с фрагментом
    RepoPresenter presenter;

    public RepositoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repoView = inflater.inflate(R.layout.fragment_repositories, container, false);

        initView(repoView);
        if(presenter.getSearchQuery()!=null)
            searchRepo.setText(presenter.getSearchQuery());

        return repoView;
    }

    private void initView(View repoView) {
        repoLayoutManager = new LinearLayoutManager(getActivity());
        repoRecyclerView = repoView.findViewById(R.id.repo_list);
        repoRecyclerView.setLayoutManager(repoLayoutManager);
        repoRecyclerView.setAdapter(presenter.getRepoRecyclerAdapter());

        searchRepo = repoView.findViewById(R.id.repo_search);
        TextWatcher searchWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchRepo(s.toString());
            }
        };
        searchRepo.addTextChangedListener(searchWatcher);
    }


    @Override
    public void onRepoItemClick(int position) {
        Log.d(REPO_TAG, "Click on Position: " + position);
    }

    @Override
    public MvpDelegate getMvpDelegate() {
        return super.getMvpDelegate();
    }
}

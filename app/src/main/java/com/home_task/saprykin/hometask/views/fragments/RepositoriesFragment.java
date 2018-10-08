package com.home_task.saprykin.hometask.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.home_task.saprykin.hometask.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RepositoriesFragment extends Fragment {
    View repoView;

    public RepositoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repoView = inflater.inflate(R.layout.fragment_repositories, container, false);
        return repoView;
    }


}

package com.home_task.saprykin.hometask.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.ProfilePresenter;
import com.home_task.saprykin.hometask.views.interfaces.ProfileView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends MvpAppCompatFragment implements ProfileView {
    private static final String PROFILE_TAG = "Profile Info";

    View profileView;
    ImageView profileImage;

    @InjectPresenter
    ProfilePresenter profilePresenter;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileView = inflater.inflate(R.layout.fragment_profile, container, false);
        profileImage = profileView.findViewById(R.id.profile_image);
        profilePresenter.setProfileInfo();
        profilePresenter.setProfileImage();
        return profileView;
    }

    @Override
    public void setText(int viewId, String currentText) {
        if (profileView.findViewById(viewId) instanceof TextView)
            ((TextView) profileView.findViewById(viewId)).setText(currentText);
    }

    @Override
    public void setImage() {
        Log.d(PROFILE_TAG, "Set image sub");
    }
}

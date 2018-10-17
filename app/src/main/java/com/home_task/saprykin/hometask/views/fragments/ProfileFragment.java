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
import com.bumptech.glide.Glide;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.presenters.ProfilePresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.ProfileView;
import com.home_task.saprykin.hometask.views.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileView {
    private static final String PROFILE_TAG = "Profile Info";

    ImageView profileImage;


    @InjectPresenter
    ProfilePresenter profilePresenter;

    public ProfileFragment() {
        super();
        layout = R.layout.fragment_profile;
    }


    @Override
    protected void initView() {
        profileImage = currentFragmentView.findViewById(R.id.profile_image);
        profilePresenter.setProfileInfo();
    }

    @Override
    public void setText(int viewId, String currentText) {
        if (currentFragmentView.findViewById(viewId) instanceof TextView)
            ((TextView) currentFragmentView.findViewById(viewId)).setText(currentText);
    }

    @Override
    public void setImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .into(profileImage);
    }

    @Override
    public void showLoading() {
        super.showLoading();
        currentFragmentView.findViewById(R.id.profile_info_layout).setVisibility(View.GONE);
        currentFragmentView.findViewById(R.id.progressView).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        currentFragmentView.findViewById(R.id.profile_info_layout).setVisibility(View.VISIBLE);
        currentFragmentView.findViewById(R.id.progressView).setVisibility(View.GONE);
    }
}

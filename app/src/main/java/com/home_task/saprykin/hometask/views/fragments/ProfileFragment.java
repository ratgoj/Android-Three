package com.home_task.saprykin.hometask.views.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.home_task.saprykin.hometask.AppContext;
import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.InternalSettings;
import com.home_task.saprykin.hometask.presenters.ProfilePresenter;
import com.home_task.saprykin.hometask.presenters.interfaces.ProfileView;
import com.home_task.saprykin.hometask.views.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileView {
    private static final String PROFILE_TAG = "Profile Info";

    ImageView profileImage;
    EditText searchProfileInput;
    ImageButton searchProfileButton;


    @InjectPresenter
    ProfilePresenter profilePresenter;

    public ProfileFragment() {
        layout = R.layout.fragment_profile;
    }


    @Override
    protected void initView() {
        profileImage = currentFragmentView.findViewById(R.id.profile_image);
        searchProfileInput = currentFragmentView.findViewById(R.id.profile_search_input);
        searchProfileButton = currentFragmentView.findViewById(R.id.profile_search_button);
        searchProfileButton.setOnClickListener(v -> {
            saveUserLogin(searchProfileInput.getText().toString());
            profilePresenter.findProfileInfo(searchProfileInput.getText().toString());
        });
        profilePresenter.setProfileInfo();
    }


    @Override
    public void setProfileFullName(String name) {
        setText(R.id.profile_full_name_text, name);
    }

    @Override
    public void setProfileNick(String nick) {
        setText(R.id.profile_nick_name_text, "@" + nick);
    }

    @Override
    public void setProfileDateCreation(String dateCreation) {
        setText(R.id.profile_date_text, dateCreation);
    }

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
        currentFragmentView.findViewById(R.id.profile_progress_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
        currentFragmentView.findViewById(R.id.profile_info_layout).setVisibility(View.VISIBLE);
        currentFragmentView.findViewById(R.id.profile_progress_view).setVisibility(View.GONE);
    }

    private void saveUserLogin(String userLogin) {
        InternalSettings.getInstance(getActivity()).saveUserLogin(userLogin);
    }
}

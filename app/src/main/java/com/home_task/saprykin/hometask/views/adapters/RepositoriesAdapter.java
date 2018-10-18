package com.home_task.saprykin.hometask.views.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.home_task.saprykin.hometask.R;
import com.home_task.saprykin.hometask.model.entities.models.RepoModel;

import java.util.List;

/**
 * Created by andrejsaprykin on 08/10/2018.
 */

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepoViewHolder> {
    private final static String ADAPTER_TAG = "Repo Adapter";

    public interface PositionClickListener {
        void onPositionClick(int position);
    }

    private List<RepoModel> repoList;
    private LayoutInflater repoInflater;
    private static PositionClickListener positionListener;

    public RepositoriesAdapter(List<RepoModel> repoList, PositionClickListener positionClickListener) {
        this.repoList = repoList;
        this.positionListener = positionClickListener;
    }

    public static class RepoViewHolder extends RecyclerView.ViewHolder {
        TextView repoName;
        TextView repoUpdateDate;

        public RepoViewHolder(View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_name);
            repoUpdateDate = itemView.findViewById(R.id.repo_update);
            itemView.setOnClickListener(v -> {
                if (positionListener != null)
                    positionListener.onPositionClick(getAdapterPosition());
            });
        }
    }

    @NonNull
    @Override
    public RepositoriesAdapter.RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.repoInflater = LayoutInflater.from(parent.getContext());
        View view = repoInflater.inflate(R.layout.repo_item_view, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoriesAdapter.RepoViewHolder holder, int position) {
        RepoModel curentItem = repoList.get(position);
        holder.repoName.setText(curentItem.getRepoName());
        holder.repoUpdateDate.setText(curentItem.getUrlPath());
    }

    @Override
    public int getItemCount() {
        if (repoList != null)
            return repoList.size();
        else
            return 0;
    }

    public void updateData(List<RepoModel> newRepoList) {
        this.repoList = newRepoList;
        notifyDataSetChanged();
    }
}

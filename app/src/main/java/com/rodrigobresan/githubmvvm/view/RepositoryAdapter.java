package com.rodrigobresan.githubmvvm.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.databinding.ItemRepositoryBinding;
import com.rodrigobresan.githubmvvm.model.entities.Repository;
import com.rodrigobresan.githubmvvm.viewmodel.ItemRepositoryViewModel;

import java.util.List;

/**
 * Created by rodrigobresan on 1/15/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Repository> repositoryList;

    public void setRepositoryList(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
        notifyDataSetChanged();
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepositoryBinding itemRepositoryBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_repository,
                        parent,
                        false);

        return new RepositoryViewHolder(itemRepositoryBinding);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bindRepository(repositoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        private ItemRepositoryBinding itemRepositoryBinding;

        public RepositoryViewHolder(ItemRepositoryBinding itemRepositoryBinding) {
            super(itemRepositoryBinding.layoutItemRepositoryContainer);

            this.itemRepositoryBinding = itemRepositoryBinding;
        }

        void bindRepository(Repository repository) {
            if (itemRepositoryBinding.getItemRepositoryViewModel() == null) {
                itemRepositoryBinding.setItemRepositoryViewModel(new ItemRepositoryViewModel(
                        repository, itemView.getContext()));
            } else {
                itemRepositoryBinding.getItemRepositoryViewModel().setRepository(repository);
            }
        }
    }
}

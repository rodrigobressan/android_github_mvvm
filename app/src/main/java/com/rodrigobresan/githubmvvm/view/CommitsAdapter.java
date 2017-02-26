package com.rodrigobresan.githubmvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rodrigobresan.githubmvvm.R;
import com.rodrigobresan.githubmvvm.databinding.ItemCommitBinding;
import com.rodrigobresan.githubmvvm.model.entities.Commit;
import com.rodrigobresan.githubmvvm.viewmodel.ItemCommitViewModel;

import java.util.List;


/**
 * Created by rodrigobresan on 1/27/17.
 * <p>
 * E-mail: rcbresan@gmail.com
 * Github: bresan
 */
public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.CommitViewHolder> {

    private List<Commit> commitList;

    public void setCommitList(List<Commit> commitList) {
        this.commitList = commitList;
        notifyDataSetChanged();
    }

    @Override
    public CommitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCommitBinding itemCommitBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_commit, parent, false);
        return new CommitViewHolder(itemCommitBinding);
    }

    @Override
    public void onBindViewHolder(CommitViewHolder holder, int position) {
        holder.bindCommit(commitList.get(position));
    }

    @Override
    public int getItemCount() {
        return commitList == null ? 0 : commitList.size();
    }

    public class CommitViewHolder extends RecyclerView.ViewHolder{

        private ItemCommitBinding itemCommitBinding;

        public CommitViewHolder(ItemCommitBinding itemCommitBinding) {
            super(itemCommitBinding.itemCommitContainer);

            this.itemCommitBinding = itemCommitBinding;
        }

        void bindCommit(Commit commit) {
            if (itemCommitBinding.getCommitViewModel() == null) {
                itemCommitBinding.setCommitViewModel(new ItemCommitViewModel(commit));
            } else {
                itemCommitBinding.getCommitViewModel().setCommit(commit);
            }
        }
    }
}

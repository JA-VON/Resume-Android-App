package com.profile.javondavis.sixsecond.ui.projects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.profile.javondavis.R;
import com.profile.javondavis.helpers.Constants;
import com.profile.javondavis.models.Project;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 29/05/16.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {

    private ArrayList<Project> mProjects;
    private Context mContext;

    public ProjectAdapter(Context context, ArrayList<Project> projects)
    {
        this.mContext = context;
        this.mProjects = projects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_projects, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Project project = mProjects.get(position);

        holder.projectTitleView.setText(project.getTitle());
        holder.projectDateView.setText(project.getDate());
        holder.projectDescriptionView.setText(project.getDescription());

        ArrayList<String> relevantTechnologies = project.getTags();

        // holder.relevantTechnologyViews is immutable since it is populated using ButterKnife
        // technologyViews is a mutable copy
        ArrayList<TextView> technologyViews = new ArrayList<>();
        technologyViews.addAll(holder.relevantTechnologyViews);

        // check how many relevant technologies and set them accordingly
        switch (relevantTechnologies.size())
        {
            case 0:
                ButterKnife.apply(holder.relevantTechnologyViews, Constants.VISIBILITY_GONE);
                break;
            case 1:
                holder.relevantTechnologyView1.setText(relevantTechnologies.get(0));
                technologyViews.remove(0);
                ButterKnife.apply(technologyViews,Constants.VISIBILITY_GONE);
                break;
            case 2:
                holder.relevantTechnologyView1.setText(relevantTechnologies.get(0));
                technologyViews.remove(0);
                holder.relevantTechnologyView2.setText(relevantTechnologies.get(1));
                technologyViews.remove(0);
                ButterKnife.apply(technologyViews,Constants.VISIBILITY_GONE);
                break;
            case 3:
                holder.relevantTechnologyView1.setText(relevantTechnologies.get(0));
                technologyViews.remove(0);
                holder.relevantTechnologyView2.setText(relevantTechnologies.get(1));
                technologyViews.remove(0);
                holder.relevantTechnologyView3.setText(relevantTechnologies.get(2));
                technologyViews.remove(0);
                break;
        }
        
    }

    @Override
    public int getItemCount() {
        return mProjects.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.projectTitleView) TextView projectTitleView;
        @Bind(R.id.projectDateView) TextView projectDateView;
        @Bind(R.id.projectDescriptionView) TextView projectDescriptionView;
        @Bind(R.id.relevantTechnologyView1) TextView relevantTechnologyView1;
        @Bind(R.id.relevantTechnologyView2) TextView relevantTechnologyView2;
        @Bind(R.id.relevantTechnologyView3) TextView relevantTechnologyView3;
        @Bind({R.id.relevantTechnologyView1, R.id.relevantTechnologyView2, R.id.relevantTechnologyView3})
        List<TextView> relevantTechnologyViews;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
package il.ac.shenkar.showshenkar.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.util.List;

import il.ac.shenkar.showshenkar.R;
import il.ac.shenkar.showshenkar.activities.ProjectActivity;
import il.ac.shenkar.showshenkar.backend.projectApi.ProjectApi;
import il.ac.shenkar.showshenkar.backend.projectApi.model.Project;
import il.ac.shenkar.showshenkar.utils.Constants;

public class DepProjectsRecyclerAdapter extends RecyclerView.Adapter<DepProjectsRecyclerAdapter.CustomViewHolder> {
    private List<Project> depProjectList;
    private Context mContext;
    private String mDepartment;

    public DepProjectsRecyclerAdapter(Context context, String department, List<Project> depProjectList) {
        this.depProjectList = depProjectList;
        this.mContext = context;
        this.mDepartment = department;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dep_project_row, null);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Project depProject = depProjectList.get(i);

        customViewHolder.projectId = depProject.getId();
        customViewHolder.txtProjectName.setText(depProject.getName());
        List<String> names = depProject.getStudentNames();
        if (names.size() > 0) {
            customViewHolder.txtProjectStudent.setText(names.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return (null != depProjectList ? depProjectList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected Long projectId;
        protected TextView txtProjectName;
        protected TextView txtProjectStudent;

        public CustomViewHolder(View view) {
            super(view);
            this.txtProjectName = (TextView) view.findViewById(R.id.project_name);
            this.txtProjectStudent = (TextView) view.findViewById(R.id.project_student);
            txtProjectName.setOnClickListener(this);
            txtProjectStudent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Create intent
            Intent intent = new Intent(mContext, ProjectActivity.class);
            intent.putExtra("project", txtProjectName.getText().toString());
            intent.putExtra("student", txtProjectStudent.getText().toString());
            intent.putExtra("id", projectId);

            //Start details activity
            mContext.startActivity(intent);
        }
    }

    public void refresh() {
        final ProjectApi projectApi = new ProjectApi.Builder(
                AndroidHttp.newCompatibleTransport(),
                new JacksonFactory(),
                new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) throws IOException {

                    }
                }).setRootUrl(Constants.ROOT_URL).build();

        new AsyncTask<Void, Void, List<Project>>() {
            @Override
            protected List<Project> doInBackground(Void... params) {
                List<Project> projects = null;
                try {
                    projects = projectApi.getProjectsByDepartment(mDepartment).execute().getItems();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return projects;
            }

            @Override
            protected void onPostExecute(List<Project> projects) {
                //show complition in UI
                //fill grid view with data
                if (projects != null) {
                    depProjectList.clear();
                    depProjectList.addAll(projects);
                    notifyDataSetChanged();
                }
            }
        }.execute();
    }
}
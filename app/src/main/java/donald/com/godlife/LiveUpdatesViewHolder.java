package donald.com.godlife;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


public class LiveUpdatesViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context context;

    public LiveUpdatesViewHolder(View itemView) {
        super(itemView);
        mView=itemView;


    }

    void setProjectbody(String project_names){
        TextView projectName= mView.findViewById(R.id.tv_post_body);
        projectName.setText(project_names);

    }
    void setPostDate(String dates){
        TextView date = mView.findViewById(R.id.tv_post_date);
        date.setText(dates);
    }


}

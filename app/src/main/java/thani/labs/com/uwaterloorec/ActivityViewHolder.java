package thani.labs.com.uwaterloorec;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by meyyappan on 2016-08-24.
 */
public class ActivityViewHolder extends RecyclerView.ViewHolder {
    TextView activityName, activityLocation, activityTime;
    ImageView activityIcon;

    ActivityViewHolder(View itemView) {
        super(itemView);
        activityName = (TextView) itemView.findViewById(R.id.activity_name);
        activityLocation = (TextView) itemView.findViewById(R.id.activity_location);
        activityTime = (TextView) itemView.findViewById(R.id.activity_time);
        activityIcon = (ImageView) itemView.findViewById(R.id.activity_icon);
    }
}

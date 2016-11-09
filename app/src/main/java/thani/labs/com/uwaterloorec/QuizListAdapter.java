package thani.labs.com.uwaterloorec;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thani.labs.com.uwaterloorec.model.ScheduleEntry;

/**
 * Created by meyyappan on 2016-08-24.
 */
public class QuizListAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    private List<ScheduleEntry> mScheduleEntries;

    QuizListAdapter(List<ScheduleEntry> scheduleEntries) {
        this.mScheduleEntries = scheduleEntries;
    }

    public void swap(List<ScheduleEntry> datas) {
        mScheduleEntries = datas;
        notifyDataSetChanged();
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.quiz_card, viewGroup, false);
        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder activityViewHolder, int i) {
        ScheduleEntry s = this.mScheduleEntries.get(i);
        activityViewHolder.activityName.setText(s.getSport());
        activityViewHolder.activityLocation.setText(s.getLocation());
        activityViewHolder.activityTime.setText(s.getStartTime() + " to " + s.getEndTime());
        int imageId = R.drawable.sporty;
        switch (s.getSport().toLowerCase()) {
            case "basketball":
                imageId = R.drawable.basketball;
                break;
            case "badminton":
                imageId = R.drawable.badminton;
                break;
            case "multi":
                imageId = R.drawable.sporty;
                break;
            case "soccer":
                imageId = R.drawable.soccer;
                break;
            case "swim":
                imageId = R.drawable.snorkel;
                break;
            case "studio":
                imageId = R.drawable.yoga;
                break;
        }
        activityViewHolder.activityIcon.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return mScheduleEntries.size();
    }
}
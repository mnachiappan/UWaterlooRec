package thani.labs.com.uwaterloorec;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import thani.labs.com.uwaterloorec.model.Quiz;

/**
 * Created by meyyappan on 2016-08-24.
 */
public class QuizListAdapter extends RecyclerView.Adapter<ActivityViewHolder> {
    private final List<Quiz> quizzes;

    QuizListAdapter(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public ActivityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.quiz_card, viewGroup, false);
        return new ActivityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActivityViewHolder activityViewHolder, int i) {
        activityViewHolder.activityName.setText("Volleyball");
        activityViewHolder.activityLocation.setText("CIF Gym 3");
        activityViewHolder.activityTime.setText("10:00 PM to 11:30 PM");
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }
}
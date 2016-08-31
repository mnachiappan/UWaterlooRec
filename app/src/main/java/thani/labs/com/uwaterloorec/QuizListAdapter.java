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
public class QuizListAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private final List<Quiz> quizzes;

    QuizListAdapter(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        final View v = layoutInflater.inflate(R.layout.quiz_card, viewGroup, false);
        return new QuizViewHolder(v);
    }

    @Override
    public void onBindViewHolder(QuizViewHolder quizViewHolder, int i) {
        quizViewHolder.quizQuestion.setText(quizzes.get(i).getQuestion());
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }
}
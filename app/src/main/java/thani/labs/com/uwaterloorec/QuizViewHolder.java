package thani.labs.com.uwaterloorec;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by meyyappan on 2016-08-24.
 */
public class QuizViewHolder extends RecyclerView.ViewHolder {
    TextView quizQuestion;

    QuizViewHolder(View itemView) {
        super(itemView);
        quizQuestion = (TextView) itemView.findViewById(R.id.quiz_question);
    }
}

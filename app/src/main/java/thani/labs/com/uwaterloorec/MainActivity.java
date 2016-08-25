package thani.labs.com.uwaterloorec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.quiz_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QuizListAdapter(new QuizProvider().readQuizzes()));
    }

    class QuizListAdapter extends RecyclerView.Adapter<QuizViewHolder> {
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

    class QuizViewHolder extends RecyclerView.ViewHolder {
        TextView quizQuestion;

        QuizViewHolder(View itemView) {
            super(itemView);
            quizQuestion = (TextView) itemView.findViewById(R.id.quiz_question);
        }
    }
}

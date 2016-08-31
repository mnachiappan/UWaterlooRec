package thani.labs.com.uwaterloorec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import thani.labs.com.uwaterloorec.provider.ScheduleProvider;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.quiz_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new QuizListAdapter(new ScheduleProvider().readQuizzes()));
    }
}

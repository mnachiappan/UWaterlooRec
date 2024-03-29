package thani.labs.com.uwaterloorec;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import thani.labs.com.uwaterloorec.model.ScheduleEntry;

public class MainActivity extends AppCompatActivity {

    private List<ScheduleEntry> data;
    private QuizListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AsyncFetch().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.android_filter_activities:
                ActivityFilterDialogFragment fm = new ActivityFilterDialogFragment();
                fm.setCallingActivityContext(this);
                fm.show(getSupportFragmentManager(), "FM");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void applyFilter() {
        SharedPreferences preferences =
                this.getSharedPreferences(ActivityFilterDialogFragment.PREFERENCES_FILTER_LIST,
                        Context.MODE_PRIVATE);
        List<ScheduleEntry> filteredData = new ArrayList<ScheduleEntry>();
        for (ScheduleEntry entry : data) {
            if (preferences.contains(entry.getSport())) {
                if (preferences.getBoolean(entry.getSport(), false)) {
                    filteredData.add(entry);
                }
            } else {
                filteredData.add(entry);
            }
        }
        listAdapter.swap(filteredData);
    }

    private class AsyncFetch extends AsyncTask<String, String, String> {

        URL url = null;
        HttpsURLConnection conn;

        @Override
        protected void onPreExecute() {

        }


        @Override
        protected String doInBackground(String... strings) {
            try {
                url = new URL("https://dog.mnachiappan.com/data.json");
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }
            try {
                conn = (HttpsURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            }

            try {
                if (conn.getResponseCode() == HttpsURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return (result.toString());
                } else {
                    return ("unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            data=new ArrayList<>();
            try {
                JSONArray jArray = new JSONArray(result);
                for(int i=0;i<jArray.length();i++) {
                    JSONObject jsonScheduleData = jArray.getJSONObject(i);
                    ScheduleEntry scheduleEntry = new ScheduleEntry(jsonScheduleData.getString("start_time"),
                            jsonScheduleData.getString("end_time"), jsonScheduleData.getString("name"),
                            jsonScheduleData.getString("location"));
                    data.add(scheduleEntry);
                }
                Collections.sort(data,new Comparator<ScheduleEntry>(){
                    public int compare(ScheduleEntry s1, ScheduleEntry s2){
                        return s1.getSport().compareTo(s2.getSport());
                    }});
                RecyclerView rv = (RecyclerView) findViewById(R.id.schedule_list);
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                listAdapter = new QuizListAdapter(data);
                rv.setAdapter(listAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

package thani.labs.com.uwaterloorec.model;

/**
 * Created by meyyappan on 2016-09-17.
 */
public class ScheduleEntry {
    private String mStartTime, mEndTime, mSport, mLocation;

    public ScheduleEntry(String startTime, String endTime, String sport, String location) {
        mStartTime = startTime;
        mEndTime = endTime;
        mSport = sport;
        mLocation = location;
    }

    public String getStartTime() {
        return mStartTime;
    }

    public String getEndTime() {
        return mEndTime;
    }

    public String getSport() {
        return mSport;
    }

    public String getLocation() {
        return mLocation;
    }
}

package thani.labs.com.uwaterloorec;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meyyappan on 2016-09-18.
 */
public class ActivityFilterDialogFragment extends DialogFragment {

    public static final String PREFERENCES_FILTER_LIST = "Activity.Filter.Preferences";

    private Context mContext;

    public void setCallingActivityContext(Context context) {
        this.mContext = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final List<Integer> mSelectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] activities = getResources().getStringArray(R.array.activities);
        SharedPreferences preferences =
                getActivity().getSharedPreferences(PREFERENCES_FILTER_LIST,
                        Context.MODE_PRIVATE);
        boolean[] checkedItems = new boolean[activities.length];
        for (int i = 0; i < activities.length; i++) {
            if (preferences.contains(activities[i])) {
                if (preferences.getBoolean(activities[i], false)) {
                    checkedItems[i] = true;
                    mSelectedItems.add(i);
                }
            } else {
                mSelectedItems.add(i);
                checkedItems[i] = true;
            }
        }
        // Set the dialog title
        builder.setTitle(R.string.select_activities)
                // Specify the list array, the items to be selected by default (null for none),
                // and the listener through which to receive callbacks when items are selected
                .setMultiChoiceItems(R.array.activities, checkedItems,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    mSelectedItems.add(which);
                                } else if (mSelectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    mSelectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK, so save the mSelectedItems results somewhere
                        // or return them to the component that opened the dialog
                        String[] activities = getResources().getStringArray(R.array.activities);
                        SharedPreferences preferences =
                                getActivity().getSharedPreferences(PREFERENCES_FILTER_LIST,
                                        Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        for (int i = 0; i < activities.length; i++) {
                            if (mSelectedItems.contains(i)) {
                                editor.putBoolean(activities[i], true);
                            } else {
                                editor.putBoolean(activities[i], false);
                            }
                        }
                        editor.commit();
                        ((MainActivity)mContext).applyFilter();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        return builder.create();
    }
}
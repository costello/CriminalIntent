package com.sviat.k.criminalintent.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import com.sviat.k.criminalintent.app.activity.CrimeActivity;
import com.sviat.k.criminalintent.app.data.Crime;
import com.sviat.k.criminalintent.app.data.CrimeLab;

import java.util.ArrayList;

/**
 * Created by Sviat on 03.11.14.
 */
public class CrimeListFragment extends ListFragment {
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crime_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Crime c = (Crime) getListAdapter().getItem(position);
        Intent intentCrimeActivity = new Intent(getActivity(), CrimeActivity.class);

        intentCrimeActivity.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(intentCrimeActivity);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // If we weren't given a view, inflate one
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Crime
            Crime c = (Crime) getListAdapter().getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.crime_list_item_crime_title);
            titleTextView.setText(c.getTitle());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.crime_list_item_crime_date);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox = (CheckBox) convertView.findViewById(R.id.crime_list_item_solved_check);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }
}
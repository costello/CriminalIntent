package com.sviat.k.criminalintent.app;

import android.support.v4.app.Fragment;

/**
 * Created by Sviat on 03.11.14.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}

package com.boost.entertainment.sqliteapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by waqas on 19/09/2016.
 */
public class StateSaveFragment extends Fragment {

    private Double aDouble;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Double getaDouble() {
        return aDouble;
    }

    public void setaDouble(Double aDouble) {
        this.aDouble = aDouble;
    }
}

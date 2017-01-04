package com.damavandi.androidsample.interfaces;

import android.view.View;

/**
 * Created by arch on 1/4/17.
 */

public interface OnItemClickListener {

    void onItemClick(View view, int position);

    void onLongItemClick(View view, int position);
}

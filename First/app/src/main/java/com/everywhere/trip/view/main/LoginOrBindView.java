package com.everywhere.trip.view.main;

import android.app.Activity;

import com.everywhere.trip.base.BaseMvpView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public interface LoginOrBindView extends BaseMvpView {
    String getPhone();

    Activity getAct();

    void go2MainActivity();
}

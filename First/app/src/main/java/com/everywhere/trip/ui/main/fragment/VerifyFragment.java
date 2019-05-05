package com.everywhere.trip.ui.main.fragment;

import com.everywhere.trip.R;
import com.everywhere.trip.base.BaseFragment;
import com.everywhere.trip.presenter.VerifyPresenter;
import com.everywhere.trip.view.main.VerifyView;

/**
 * @author xts
 *         Created by asus on 2019/5/4.
 */

public class VerifyFragment extends BaseFragment<VerifyView,VerifyPresenter> implements VerifyView{
    @Override
    protected VerifyPresenter initPresenter() {
        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @Override
    public void toastShort(String msg) {

    }
}

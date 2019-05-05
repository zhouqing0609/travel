package com.example.kaoshib.presenter;

import com.example.kaoshib.bean.TabBean;
import com.example.kaoshib.moudle.CallBack;
import com.example.kaoshib.moudle.Moudleimp;
import com.example.kaoshib.view.IView;

public class Presenterimp implements IPresenter {
    Moudleimp moudleimp;
    IView iView;

    public Presenterimp(IView iView) {
        this.iView = iView;
        moudleimp = new Moudleimp();
    }

    @Override
    public void getPresenterList() {
        moudleimp.getMoudleList(new CallBack() {
            @Override
            public void getList(TabBean tabBean) {
                iView.getViewList(tabBean);
            }

            @Override
            public void getFailed(String info) {
                iView.getViewFailed(info);
            }
        });
    }
}

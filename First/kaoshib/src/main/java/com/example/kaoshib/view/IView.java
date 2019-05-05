package com.example.kaoshib.view;

import com.example.kaoshib.bean.TabBean;

public interface IView {
    void getViewList(TabBean tabBean);
    void getViewFailed(String info);
}

package com.example.kaoshib.moudle;

import com.example.kaoshib.bean.TabBean;

public interface CallBack {
    void getList(TabBean tabBean);
    void getFailed(String info);

}

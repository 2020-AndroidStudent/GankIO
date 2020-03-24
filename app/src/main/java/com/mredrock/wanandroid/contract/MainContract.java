package com.mredrock.wanandroid.contract;

import com.mredrock.wanandroid.base.IModel;
import com.mredrock.wanandroid.base.IPresenter;
import com.mredrock.wanandroid.base.IView;

/**
 * @author 行云流水
 * @date 2020/3/24
 * @description
 */
public interface MainContract {
    interface Model extends IModel{
        // ...
    }

    interface View extends IView {
        // ...
    }
    interface Presenter extends IPresenter {
        // ...
    }
}

package com.diplom.app.fitnessproject.presenter;

/**
 * Created by Poltavets on 26.03.2016.
 */
public interface NavigationPresenter {
    boolean navigationDrawerClickListener(int menuitem);
    boolean optionMenuClickListener(int menuitem);
    void closeNavigationPresenter();
}

package com.diplom.app.fitnessproject.presenter.interfaces;

/**
 * Created by Poltavets on 26.03.2016.
 */
public interface NavigationPresenter  extends PresenterParent{
    boolean navigationDrawerClickListener(int menuitem);
    boolean optionMenuClickListener(int menuitem);
    void closeNavigationPresenter();
}

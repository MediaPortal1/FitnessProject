package com.diplom.app.fitnessproject.presenter.interfaces;

import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;

/**
 * Created by Poltavets on 13.05.2016.
 */
public interface UprazneniaAllListInterface extends PresenterParent,AdapterSetter {
    String getNameUpraznenie();
    void itemSelected(int position);
    void updateList();
}

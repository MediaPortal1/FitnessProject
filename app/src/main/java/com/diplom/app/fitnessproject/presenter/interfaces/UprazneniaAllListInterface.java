package com.diplom.app.fitnessproject.presenter.interfaces;

import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;


public interface UprazneniaAllListInterface extends AdapterSetter,ListChangedNotify{
    //UPRAZNENIA LIST
    String getNameUpraznenie();
    void itemSelected(int position);
}

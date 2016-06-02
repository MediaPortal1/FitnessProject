package com.diplom.app.fitnessproject.presenter.interfaces;

public interface ChangeColumn{
    //INTERFACE FOR CHANGE/DELETE ITEMS IN LIST
    void deleteColumn(String name);
    void renameColumn(String from, String to);
}

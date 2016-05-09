package com.diplom.app.fitnessproject.presenter.interfaces;

public interface ChangeColumn  extends PresenterParent{
    void deleteColumn(String name);
    void changeColumn(String change,String name);
}

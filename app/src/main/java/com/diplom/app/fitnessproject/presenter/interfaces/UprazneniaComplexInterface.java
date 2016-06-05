package com.diplom.app.fitnessproject.presenter.interfaces;


public interface UprazneniaComplexInterface extends ListChangedNotify{

    void deleteComplex(String name);
    void renameComplex(String from);
    void changeComplex(String from);
    void showInfoDialog(String name);
}

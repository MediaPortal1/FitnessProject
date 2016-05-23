package com.diplom.app.fitnessproject.presenter.interfaces;


public interface UprazneniaComplexInterface extends PresenterParent {
    void updateAdapter();
    void deleteComplex(String name);
    void renameComplex(String from);
    void showInfoDialog(String name);
}

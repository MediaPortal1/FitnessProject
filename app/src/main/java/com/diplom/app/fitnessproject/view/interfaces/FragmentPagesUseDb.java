package com.diplom.app.fitnessproject.view.interfaces;

import com.diplom.app.fitnessproject.model.DataBaseModel;

/**
 * Created by Poltavets on 11.05.2016.
 */
public interface FragmentPagesUseDb extends FragmentPages{
    void setDataBase(DataBaseModel db);
}

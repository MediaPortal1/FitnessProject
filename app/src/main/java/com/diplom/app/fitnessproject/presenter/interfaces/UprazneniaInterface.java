package com.diplom.app.fitnessproject.presenter.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Poltavets on 09.05.2016.
 */
public interface UprazneniaInterface extends PresenterParent{
    void addUpraznenie(Intent data);
    void addComplex(Intent data);
    void updateUpraznenie(Intent data);

}

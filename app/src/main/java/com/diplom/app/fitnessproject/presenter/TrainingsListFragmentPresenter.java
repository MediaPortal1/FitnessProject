package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseLoadTrainingsList;
import com.diplom.app.fitnessproject.model.DataBaseModelTrainings;
import com.diplom.app.fitnessproject.presenter.behavior.ShowTrainingInfo;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;
import com.diplom.app.fitnessproject.view.adapter.TrainingsListAdapter;
import com.diplom.app.fitnessproject.view.fragments.TrainingsRenameDialog;
import com.diplom.app.fitnessproject.view.interfaces.TrainingListView;

import java.util.ArrayList;
import java.util.Map;

public class TrainingsListFragmentPresenter implements TrainingListInterface,View.OnClickListener,OnDialogResult{

    private Context context;
    private FragmentManager fragmentManager;
    private ArrayList<Map<String,String>> trainingsList=new ArrayList<>();
    private TrainingListView view;
    private DataBaseModelTrainings dataBaseTrainings;
    private ShowInfoDialog trainingsInfoDialog;

    public static final int DIALOG_RENAME=1;

    public TrainingsListFragmentPresenter(Context context, FragmentManager fragmentManager, TrainingListView view) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.view = view;
        dataBaseTrainings=new DataBaseModelTrainings(context);
        trainingsInfoDialog=new ShowTrainingInfo();
        updateList();
    }


    @Override
    public void updateList() {
        DataBaseLoadTrainingsList connection=new DataBaseLoadTrainingsList(context,view,dataBaseTrainings,this);
        connection.execute();
    }

    //ON Image Button(imagebutton.lisitem.trainigs) click
    @Override
    public void onClick(View v) {
        final String name=(String)v.getTag();
        PopupMenu popupMenu=new PopupMenu(context,v);
        popupMenu.inflate(R.menu.popup_list_full);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_info:
                        showTrainingInfo(name);
                        break;
                    case R.id.menu_change:
                        //TODO:CHANGE TRAINING
                        break;
                    case R.id.menu_delete:
                        deleteTraining(name);
                        break;
                    case R.id.menu_rename:
                        renameTraining(name);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    //POPUP MENU ITEM CLICK

    private void deleteTraining(String name){
        dataBaseTrainings.deleteTrainingbyName(name);
        updateList();
    }

    private void renameTraining(String name){
        /***SHOW RENAME DIALOG***/
        TrainingsRenameDialog renameDialog=new TrainingsRenameDialog();
        renameDialog.setTitle(R.string.rename_training);
        renameDialog.setDialogResult(this);
        renameDialog.setString(name);
        renameDialog.setDialogCode(DIALOG_RENAME);
        renameDialog.show(fragmentManager,String.valueOf(DIALOG_RENAME));
    }

    private void showTrainingInfo(String name){
        trainingsInfoDialog.showDialog("INFO",fragmentManager,dataBaseTrainings.getTrainingbyName(name));
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE){
            case DIALOG_RENAME:
                Map<String,String> map= (Map<String, String>) obj;
                dataBaseTrainings.renameTraining(map.get("from"),map.get("to"));
                updateList();
                break;
        }
    }
}

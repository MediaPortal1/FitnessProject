package com.diplom.app.fitnessproject.presenter;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.presenter.behavior.ItemListFactory;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddCustomInterface;
import com.diplom.app.fitnessproject.view.activity.UprazneniaListTrainingsActivity;
import com.diplom.app.fitnessproject.view.fragments.AddTimeDialog;
import com.diplom.app.fitnessproject.view.fragments.TrainingsAddCustomChooseCat;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddComplexCommentDialog;
import com.diplom.app.fitnessproject.view.interfaces.TrainingsAddCustomView;

import java.util.ArrayList;
import java.util.Map;

public class TrainingsAddCustomPresenter implements TrainingsAddCustomInterface,AdapterView.OnItemClickListener,OnDialogResult{

    private Context context;
    private FragmentManager fragmentManager;
    private TrainingsAddCustomView view;

    private ArrayList<String> uprazneniaList=new ArrayList<>();
    private ArrayList<Map<String,Object>> listitem=new ArrayList<>();
    private SimpleAdapter adapter;
    private String name,desctiprion,categories;
    private int rest,type;

    public static final int DIALOG_DESCRIPTION=1;
    public static final int DIALOG_TYPE=2;
    public static final int DIALOG_REST=3;

    public TrainingsAddCustomPresenter(Context context, FragmentManager fragmentManager,TrainingsAddCustomView view) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.view=view;
        initList();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }
    private void initList(){

        //
        listitem.add(ItemListFactory.getListMap(R.mipmap.comment_outline,context.getString(R.string.description),context.getString(R.string.nodescription)));
        //
        listitem.add(ItemListFactory.getListMap(R.mipmap.category,context.getString(R.string.type),context.getString(R.string.notype)));
        //
        listitem.add(ItemListFactory.getListMap(R.mipmap.clock,context.getString(R.string.rest),context.getString(R.string.norest)));
        //
        listitem.add(ItemListFactory.getListMap(R.mipmap.gym,context.getString(R.string.title_upraznenia),context.getString(R.string.noupraznenia)));


        adapter=new SimpleAdapter(context,listitem,R.layout.listitem_add_custom,new String[]{"icon","text","subtext"},new int[]{R.id.imageview_listitem_add_custom,R.id.textView_listitem_add_custom,R.id.textView_subtext_listitem_add_custom});
        view.setAdapter(adapter);
    }

    //LIST ON CLICK
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0: // DESCRIPTION
                showDescription();
                break;
            case 1: // TYPE
                showType();
                break;
            case 2: // REST
                showRest();
                break;
            case 3://UPRAZNENIA
                context.startActivity(new Intent(context, UprazneniaListTrainingsActivity.class));
                break;
        }
    }
    private void showDescription(){
        UprazneniaAddComplexCommentDialog fragment=new UprazneniaAddComplexCommentDialog();
        ((DialogResultSetter)fragment).setDialogResult(this);
        fragment.setDialogCode(DIALOG_DESCRIPTION);
        fragment.show(fragmentManager,"comment");
    }
    private void showType(){
        TrainingsAddCustomChooseCat dialog=new TrainingsAddCustomChooseCat();
        dialog.setDialogResult(this);
        dialog.show(fragmentManager,"TYPE");
    }
    private void showRest(){
        AddTimeDialog dialog=new AddTimeDialog();
        dialog.setDialogCode(DIALOG_REST);
        dialog.setDialogResult(this);
        dialog.show(fragmentManager,"REST");
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE){
            case DIALOG_DESCRIPTION:
                String desc=(String)obj;
                if(desc!=null && !desc.equals("")){
                    desctiprion=desc;
                    listitem.get(0).put("subtext",desc);
                    updateList();
                }else Toast.makeText(context,context.getString(R.string.notempty_description),Toast.LENGTH_SHORT).show();
                break;
            case DIALOG_TYPE:
                onTypeResult((int)obj);
            break;
            case DIALOG_REST:
                int rest=(int)Integer.parseInt((String)obj);
                if(rest!=0){
                    this.rest=rest;
                    listitem.get(2).put("subtext",String.valueOf(rest));
                    updateList();
                }
                break;
        }
    }

    //ON RESULT DIALOG "TYPE"
    private void onTypeResult(int type){
        if(type!=0){
            switch (type){
                case DataBaseHelper.TRAININGS_TYPE_STEPBYSTEP:
                    this.type=type;
                    listitem.get(1).put("subtext",context.getString(R.string.type_trainings_basic));
                    updateList();
                    break;
                case DataBaseHelper.TRAININGS_TYPE_CICLE:
                    this.type=type;
                    listitem.get(1).put("subtext",context.getString(R.string.type_trainings_cicle));
                    updateList();
            }
        }
    }

    //RESULT INTENT WITH TRAINING INFO
    public Intent getResultIntent(){
        Intent intent=new Intent();
//        intent.putExtra("name",name);
//        intent.putExtra("type",type);
//        intent.putExtra("")
        return intent;
    }
}

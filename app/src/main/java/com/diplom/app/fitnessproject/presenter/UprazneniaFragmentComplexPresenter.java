package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.behavior.ShowComplexInfo;
import com.diplom.app.fitnessproject.presenter.behavior.ShowUpraznenieInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaComplexInterface;
import com.diplom.app.fitnessproject.view.UprazneniaAddActivity;
import com.diplom.app.fitnessproject.view.UprazneniaAddComplex;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaComplexExpandableListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaComplexRenameDialog;
import com.diplom.app.fitnessproject.view.interfaces.ComplexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UprazneniaFragmentComplexPresenter implements UprazneniaComplexInterface,OnDialogResult,View.OnClickListener{

    private Context context;
    private ComplexView view;
    private DataBaseModelUpraznenia db;
    private UprazneniaComplexExpandableListAdapter adapter;
    private FragmentManager fm;
    private ShowInfoDialog infoUprDialog,infoComplDialog;

    public UprazneniaFragmentComplexPresenter(Context context, ComplexView view, DataBaseModelUpraznenia db, FragmentManager fm) {
        this.context = context;
        this.view=view;
        this.db=db;
        this.fm=fm;
        infoUprDialog =new ShowUpraznenieInfoDialog();
        infoComplDialog=new ShowComplexInfo();
        updateList();
    }
    @Override
    public void updateList(){
        DataBaseConnectionAdapter connection=new DataBaseConnectionAdapter();
        connection.execute();
    }
    private class DataBaseConnectionAdapter extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<ArrayList<Map<String, String>>> childContent = new ArrayList<>(); // MAIN
            ArrayList<Map<String, String>> groupCatList = new ArrayList<>(); // GROUP
            ArrayList<Map<String,String>> childList; // CHILD ITEM

            String[] groupfrom = new String[]{"group"};
            int[] groupto = new int[]{R.id.textView_listitem_expandebl_complex};
            String childFrom[] = new String[] { "name" };
            int childTo[] = new int[] {R.id.textView_listitem_child_complex};


            Cursor cursorComplexList = db.getComplexList();
            if (cursorComplexList.moveToFirst()){
                do {
                    String name = cursorComplexList.getString(cursorComplexList.getColumnIndex("NAME"));
                    Map<String, String> map = new HashMap();
                    map.put("group", name);
                    groupCatList.add(map);
                    //
                    Cursor cursorupraznenia=db.getUprazneniaofComplex(name);
                    if(cursorupraznenia!=null && cursorupraznenia.moveToFirst()){
                        childList=new ArrayList<>();
                        do{
                            map=new HashMap<>();
                            map.put("name",cursorupraznenia.getString(cursorupraznenia.getColumnIndex("UPRAZNENIE")));
                            childList.add(map);
                        }while (cursorupraznenia.moveToNext());
                        childContent.add(childList);
                    }
                } while (cursorComplexList.moveToNext());
        }

            adapter=new UprazneniaComplexExpandableListAdapter(
                    context,groupCatList,
                    R.layout.listitem_group_expandeble_complex,groupfrom,
                    groupto,childContent,
                    R.layout.listitem_child_expandable_complex,
                    childFrom,childTo,(UprazneniaComplexInterface) UprazneniaFragmentComplexPresenter.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.setAdapter(adapter);
        }
    }

    @Override
    public void deleteComplex(String name) {
        db.deleteComplex(name);
        updateList();
    }

    @Override
    public void renameComplex(String from) {
        UprazneniaComplexRenameDialog fragment=new UprazneniaComplexRenameDialog();
        fragment.setDialogResult(this);
        ((StringSetter)fragment).setString(from);
        fragment.show(fm,"rename");
    }

    @Override
    public void changeComplex(String from) {
        int type;
        Intent intent=new Intent(context, UprazneniaAddComplex.class);
        Cursor cursor=db.getComplexbyName(from);
        cursor.moveToFirst();
        type=cursor.getInt(cursor.getColumnIndex("TYPE"));
        intent.putExtra("NAME",cursor.getString(cursor.getColumnIndex("NAME")));
        intent.putExtra("_ID",cursor.getLong(cursor.getColumnIndex("_id")));
        intent.putExtra("TYPE",type);

        String description=cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
        if(description!=null  && !description.equals(""))
            intent.putExtra("DESCRIPTION",description);

        String name=cursor.getString(cursor.getColumnIndex("NAME"));
        cursor=db.getUprazneniaofComplex(name);
        cursor.moveToFirst();
        intent.putExtra("FIRST",cursor.getString(cursor.getColumnIndex("UPRAZNENIE")));
        cursor.moveToNext();
        intent.putExtra("SECOND",cursor.getString(cursor.getColumnIndex("UPRAZNENIE")));
        if(type== DataBaseHelper.COMPLEX_TYPE_TRIPLE){
            cursor.moveToNext();
            intent.putExtra("THIRD",cursor.getString(cursor.getColumnIndex("UPRAZNENIE")));
        }

        view.startChangeActivity(intent);
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
       Map<String,String> map=(Map<String,String>)obj;
        if(map.get("to")!=null && !map.get("to").equals("")){
            db.renameComplex(map.get("from"),map.get("to"));
            Toast.makeText(context,context.getString(R.string.updatesuccess_complex),Toast.LENGTH_SHORT).show();
        }
            else Toast.makeText(context,context.getString(R.string.notempty_name),Toast.LENGTH_SHORT).show();
        updateList();
    }

    @Override
    public void onClick(View v) {
        TextView txtview=(TextView) v.findViewById(R.id.textView_listitem_child_complex);
        String name=txtview.getText().toString();
        Cursor cursor=db.getUprazneniabyName(name);
        infoUprDialog.showDialog("info",fm,cursor);
    }

    @Override
    public void showInfoDialog(String name) {
        Cursor cursor=db.getComplexbyName(name);
        infoComplDialog.showDialog("infocomplex",fm,cursor);
    }
}

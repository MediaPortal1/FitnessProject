package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.ContextSetter;
import com.diplom.app.fitnessproject.presenter.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.StringSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaChangeDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UprazneniaExpandableListAdapter extends SimpleExpandableListAdapter {
    private Context context;
    private DataBaseModelUpraznenia db;
    private ChangeColumn changeColumn;
    private OnDialogResult result;
    private FragmentManager fm;
    private ListChangedNotify listnotify;
    public UprazneniaExpandableListAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo, DataBaseModelUpraznenia db, ChangeColumn onpopup, OnDialogResult result, FragmentManager fm) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.context=context;
        this.db=db;
        this.changeColumn=onpopup;
        this.fm=fm;
        this.result=result;
        this.listnotify=(ListChangedNotify) result;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v=super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        ImageButton img=(ImageButton) v.findViewById(R.id.imageButton_listitem_upraznenia);
        final TextView txtview=(TextView) v.findViewById(R.id.textView_largeText_Uprazenia);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.inflate(R.menu.popup_list);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //TODO: Do Popup menu!
                        switch (item.getItemId()){
                            case R.id.menu_delete:
                                notifyDataSetChanged();
                                changeColumn.deleteColumn(txtview.getText().toString());
                                    notifyDataSetChanged();
                                listnotify.adapterUpdate();
                                return true;
                            case R.id.menu_change:
                                //TODO:
                                UprazneniaChangeDialog dialog=new UprazneniaChangeDialog();
                                ((ContextSetter)dialog).setContext(context);
                                ((StringSetter)dialog).setString(txtview.getText().toString());
                                dialog.setResult(result);
                                dialog.show(fm,"change");
                                notifyDataSetChanged();
                                listnotify.adapterUpdate();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

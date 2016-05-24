package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaListInterface;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaChangeDialog;

import java.util.List;
import java.util.Map;

public class UprazneniaExpandableListAdapter extends SimpleExpandableListAdapter {
    private Context context;
    private DataBaseModelUpraznenia db;
    private ChangeColumn changeColumn;
    private OnDialogResult result;
    private FragmentManager fm;
    private ListChangedNotify listnotify;
    private View.OnClickListener clickListener;
    public UprazneniaExpandableListAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo, DataBaseModelUpraznenia db, ChangeColumn onpopup, OnDialogResult result, FragmentManager fm, View.OnClickListener click) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.context=context;
        this.db=db;
        this.changeColumn=onpopup;
        this.fm=fm;
        this.result=result;
        this.listnotify=(ListChangedNotify) result;
        this.clickListener=click;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v=super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        final ImageButton img=(ImageButton) v.findViewById(R.id.imageButton_listitem_upraznenia);
        final TextView txtview=(TextView) v.findViewById(R.id.textView_listitem_child_complex);
        v.setTag(txtview.getText().toString());
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopumMenuImageListener(v,txtview);
            }
        });
        img.setTag(txtview.getText().toString());
        v.setOnClickListener(clickListener);
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopumMenuImageListener(img,txtview);
                return true;
            }
        });
        return v;
    }
    private void PopumMenuImageListener(View v, final TextView txt){
        final View view=v;
        PopupMenu popupMenu=new PopupMenu(context,v);
        popupMenu.inflate(R.menu.popup_list_full);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_delete:
                        notifyDataSetChanged();
                        changeColumn.deleteColumn(txt.getText().toString());
                        notifyDataSetChanged();
                        listnotify.adapterUpdate();
                        return true;
                    case R.id.menu_rename:
                        UprazneniaChangeDialog dialog=new UprazneniaChangeDialog();
                        ((ContextSetter)dialog).setContext(context);
                        ((StringSetter)dialog).setString(txt.getText().toString());
                        dialog.setResult(result);
                        dialog.show(fm,"change");
                        notifyDataSetChanged();
                        listnotify.adapterUpdate();
                        return true;
                    case R.id.menu_info:
                        ((UprazneniaListInterface)result).showInfoDialog(((String)view.getTag()));
                        break;
                    case R.id.menu_change:
                        ((UprazneniaListInterface)result).changeUpraznenie((String)view.getTag());
                        return true;
                }
                return false;
            }
        });
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

}

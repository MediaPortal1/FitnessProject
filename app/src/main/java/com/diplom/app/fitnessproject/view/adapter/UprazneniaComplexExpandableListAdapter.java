package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaComplexInterface;

import java.util.List;
import java.util.Map;


public class UprazneniaComplexExpandableListAdapter extends SimpleExpandableListAdapter {
    private UprazneniaComplexInterface presenter;
    private Context context;
    public UprazneniaComplexExpandableListAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo,UprazneniaComplexInterface presenter) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
        this.presenter=presenter;
        this.context=context;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
    return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view=super.getGroupView(groupPosition, isExpanded, convertView, parent);

        TextView txtview=(TextView)view.findViewById(R.id.textView_listitem_expandebl_complex);
        final String name=txtview.getText().toString();

        view.setClickable(false);
        view.findViewById(R.id.imageButton_listitem_group_complex).setFocusable(false);
        view.findViewById(R.id.imageButton_listitem_group_complex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.inflate(R.menu.popup_list);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_delete:
                                presenter.deleteComplex(name);
                                return true;
                            case R.id.menu_rename:
                                presenter.renameComplex(name);
                                return true;
                            case R.id.menu_info:
                                presenter.showInfoDialog(name);
                                break;
                            case R.id.menu_change:
                                //TODO: CHANGE COMPLEX
                                break;
                        }
                        return false;
                    }
                });
            }
        });
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view=super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
        view.setFocusable(false);
        view.findViewById(R.id.textView_listitem_child_complex).setFocusable(false);
        view.setOnClickListener((View.OnClickListener) presenter);
        return view;
    }
}

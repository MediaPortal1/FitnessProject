package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleExpandableListAdapter;

import com.diplom.app.fitnessproject.R;

import java.util.List;
import java.util.Map;


public class UprazneniaComplexExpandableListAdapter extends SimpleExpandableListAdapter {
    public UprazneniaComplexExpandableListAdapter(Context context, List<? extends Map<String, ?>> groupData, int groupLayout, String[] groupFrom, int[] groupTo, List<? extends List<? extends Map<String, ?>>> childData, int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo, childData, childLayout, childFrom, childTo);
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
        View v=super.getGroupView(groupPosition, isExpanded, convertView, parent);
        v.setClickable(false);
        v.findViewById(R.id.imageButton_listitem_group_complex).setFocusable(false);
        v.findViewById(R.id.imageButton_listitem_group_complex).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: POPUP
            }
        });
        return v;
    }

}

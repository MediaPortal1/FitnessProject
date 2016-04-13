package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;

public class UprazneniaListCatFragment extends Fragment implements FragmentPages{
    private String title;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.upraznenia_fragment_cat_lists,null);
        TextView txtView=(TextView)view.findViewById(R.id.upraznenia_fragment_cat_text);
        txtView.setText(title);
        ListView listView=(ListView)view.findViewById(R.id.upraznenia_cat_listview);
        listView.setAdapter(new SimpleCursorAdapter(getContext(),android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1},0));
        return view;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
    this.title=title;
    }

    public void setCursor(Cursor cursor) {
    this.cursor=cursor;

    }

}

package com.diplom.app.fitnessproject.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddCustomInterface;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddFragmentCustomPresenter;
import com.diplom.app.fitnessproject.view.activity.UprazneniaAddCustomChooseCategory;
import com.diplom.app.fitnessproject.view.interfaces.FragmentAddUpraznenieCustomView;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoGetter;

public class UprazneniaAddCustom extends Fragment implements FragmentPages,FragmentAddUpraznenieCustomView{
    private ListView listView;
    private TextView editText;
    private UprazneniaAddCustomInterface presenter;
    private Bundle changebundle;

    private String title;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_upraznenia_add_fragment_custom,null);
        presenter=new UprazneniaAddFragmentCustomPresenter(getContext(),this,getChildFragmentManager());
        listView=(ListView)view.findViewById(R.id.upraznenia_add_custom_listview);
        listView.setAdapter(presenter.getListAdapter());
        editText =(EditText)view.findViewById(R.id.upraznenia_add_custom_edittext);
        editText.addTextChangedListener((TextWatcher) presenter);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) presenter);

        //ON CHANGE
        if(changebundle!=null)presenter.initChange(changebundle);
        //

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.OnUpraznenieAdded(requestCode,resultCode,data);

    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void startActivityCat() {
        startActivityForResult(new Intent(getContext(), UprazneniaAddCustomChooseCategory.class), UprazneniaAddFragmentCustomPresenter.CATEGORY);
    }
    public UprazneniaInfoGetter getUpraznenieInfo(){
     return presenter.getUprazneniaInfo();
    }

    public void onChangeUpraznenie(Bundle bundle){
        this.changebundle=bundle;
    }

    @Override
    public void changeName(String name) {
        editText.setText(name);
    }

}

package com.diplom.app.fitnessproject.presenter.behavior;


import com.diplom.app.fitnessproject.R;

import java.util.HashMap;
import java.util.Map;

public class ItemListFactory {

    public static Map<String,Object> getListMap(int imageId,String text,String subtext){
        Map<String,Object> map=new HashMap<>();
        map.put("icon", imageId);
        map.put("text",text);
        map.put("subtext",subtext);
        return map;
    }
}

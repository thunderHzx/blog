package com.hzx.blog.util;

import com.hzx.blog.model.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * hzx
 * 给我一瓶朗姆酒
 * 2020/2/27
 */
public class StringToListUtil {

    public static List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();

        if(!"".equals(ids)&&ids != null){
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                list.add(new Long(split[i]));
            }
        }
        return list;

    }

}

package com.machine.utils.collection;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanshan.chen on 2018/4/20.
 */
public class ListUtilsTest {

    @Test
    public void testGetduplicate() {
        List<String> list = new ArrayList<>();
        list.add("6");
        list.add("4");
        list.add("6");
        list.add("6");
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("1");
        list.add("1");

        Assert.assertEquals(2, ListUtils.getduplicate(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        }).size());
    }
}
package com.machine.utils.collection;

import com.machine.utils.tuple.TwoTuple;
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



    @Test
    public void testGetDifferent(){
        List<String> listA = new ArrayList<>();
        listA.add("6");
        listA.add("4");
        listA.add("5");
        listA.add("3");
        listA.add("1");
        listA.add("2");

        List<String> listB = new ArrayList<>();
        listB.add("A");
        listB.add("4");
        listB.add("5");
        listB.add("B");
        listB.add("1");
        listB.add("2");

        TwoTuple twoTuple = ListUtils.getDifferent(listA,listB, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        Assert.assertEquals(2,((List)twoTuple.first).size());
        Assert.assertEquals(2,((List)twoTuple.second).size());
    }
}
package com.machine.utils.collection;

import com.machine.utils.tuple.TwoTuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListUtils {

    /**
     * 返回list里面重复的数据
     */
    public static <T> List<T> getduplicate(List<T> list, Comparator<T> compartor) {
        if (null == list || list.size() <= 1) {
            return null;
        }
        /*浅克隆参数,排序不影响参数的顺序*/
        List<T> tempList = new ArrayList<>(list);
        List<T> resultList = new ArrayList<>();
        Collections.sort(tempList, compartor);

        T preObject = tempList.get(0);
        for (int i = 1; i < tempList.size(); i++) {
            T curObject = tempList.get(i);
            if (compartor.compare(preObject, curObject) == 0) {
                if (resultList.size() == 0) {
                    resultList.add(curObject);
                } else {
                    if (compartor.compare(curObject, resultList.get(resultList.size() - 1)) != 0) {
                        resultList.add(curObject);
                    }
                }
            }
            preObject = curObject;
        }
        return resultList;
    }


    /**
     * 返回listA与listB里面不相同的数据【对应的集合不允许有重复的数据,比较器排序要按照升序排序】
     */
    public static <T> TwoTuple<List<T>, List<T>> getDifferent(List<T> listA,
                                                              List<T> listB,
                                                              Comparator<T> compartor) {
        return getDifferent(listA, listB, compartor, compartor);
    }

    /**
     * 返回listA与listB里面不相同的数据【对应的集合不允许有重复的数据,比较器排序要按照升序排序】
     */
    public static <T> TwoTuple<List<T>, List<T>> getDifferent(List<T> listA,
                                                              List<T> listB,
                                                              Comparator<T> compartoOrder,
                                                              Comparator<T> compartorEqual) {
        if ((null == listA || listA.size() == 0) && (null == listB || listB.size() == 0)) {
            return null;
        }
        if ((null == listA || listA.size() == 0)){
            return new TwoTuple(null,listB);
        }
        if (null == listB || listB.size() == 0) {
            return new TwoTuple(listA,null);
        }
        /*浅克隆参数,排序不影响参数的顺序*/
        List<T> listATemp = new ArrayList<>(listA);
        List<T> listBTemp = new ArrayList<>(listB);
        Collections.sort(listATemp, compartoOrder);
        Collections.sort(listBTemp, compartoOrder);

        int indexA = 0;
        int indexB = 0;
        List<T> resultA = new ArrayList<>();
        List<T> resultB = new ArrayList<>();
        while (true) {
            if (indexA >= listATemp.size() || indexB >= listBTemp.size()) {
                break;
            }
            T a = listATemp.get(indexA);
            T b = listBTemp.get(indexB);
            int compare = compartorEqual.compare(a, b);
            if (0 == compare) {
                indexA++;
                indexB++;
            } else if (compare < 0) {
                indexA++;
                resultA.add(a);
            } else {
                indexB++;
                resultB.add(b);
            }
        }
        if (indexA >= listATemp.size()) {
            for (; indexB < listBTemp.size(); indexB++) {
                resultB.add(listBTemp.get(indexB));
            }
        }
        if (indexB >= listBTemp.size()) {
            for (; indexA < listATemp.size(); indexA++) {
                resultA.add(listATemp.get(indexA));
            }
        }
        return new TwoTuple(resultA, resultB);
    }

}

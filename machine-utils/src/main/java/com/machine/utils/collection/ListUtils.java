package com.machine.utils.collection;

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
        /*复制一份参数,为了下面的排序影响参数的顺序*/
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
}

package com.machine.utils.envm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanshan.chen on 2018/05/07.
 */
public enum DateEnum {

    YESTER_DAY("昨天"),
    CURRENT_DAY("今天"),
    CURRENT_WEEK("本周"),
    CURRENT_MONTH("本月"),
    CURRENT_QUARTER("本季度"),
    CURRENT_YEAR("本年");


    public String getValue() {
        return value;
    }

    /**
     * @Description: 根据code获取enum
     */
    public static DateEnum getEnumByCode(String name) {
        if (kvMap.size() == 0) {
            synchronized (lock) {
                if (kvMap.size() == 0) {
                    DateEnum[] enums = DateEnum.values();
                    for (int i = 0; i < enums.length; i++) {

                        kvMap.put(enums[i].name(), enums[i]);
                    }
                }
            }
        }
        return kvMap.get(name);
    }

    private DateEnum(String value) {
        this.value = value;
    }

    private final String value;
    private static final Object lock = new Object();
    private static final Map<String, DateEnum> kvMap = new HashMap<String, DateEnum>();
}

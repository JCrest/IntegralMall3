package com.csii.integralmall.common;

import java.util.Map;
import java.util.WeakHashMap;

public class extendurl {

    /**
     * 根据不同的key 获取想要跳转到的类的名称（注：此处要保存全类名否则将无法找到对应的类）
     *
     * @param key 获取指定的类的钥匙
     * @return 返回要到达的类的全类名
     */
    public static String getValue(String key) {
        Map<String, String> map = new WeakHashMap<>();
        map.put("JFSC://EnjoyBig", "com.csii.integralmall.activity.enjoyBigBrand.BigBrandActivity");
        map.put("JFSC://ClassGoodsList", "com.csii.integralmall.activity.enjoyBigBrand.BigBrandActivity");
        return map.get(key);
    }

}

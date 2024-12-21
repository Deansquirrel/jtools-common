package com.github.deansquirrel.tools.common;

import java.util.Collection;
import java.util.Map;

public class ValidateTool {

    /**
     * 判断 对象是否相等，equals
     * @param obj 对象1
     * @param obj2 对象1
     * @return 是否
     */
    public static boolean areEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    /**
     * 判断字符串是否相等（忽略大小写），equalsIgnoreCase
     * @param obj 字符串1
     * @param obj2 字符串1
     * @return 是否
     */
    public static boolean areEqualIgnoreCase(String obj, String obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equalsIgnoreCase(obj2);
        }
    }

    /**
     * 判断 对象是否为空，字符串、集合、map、字符数组
     * @param value 对象
     * @return 是否
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            return ((String) value).isEmpty();
        } else if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map<?, ?>) value).isEmpty();
        } else if (value instanceof String[]) {
            return ((String[]) value).length == 0;
        } else {
            return false;
        }
    }

    /**
     * 判断 字符串是否为空
     * @param s 字符串
     * @return 是否
     */
    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    /**
     * 集合是否为空
     * @param c 集合
     * @return 是否
     */
    public static boolean isEmpty(Collection<?> c) {
        return c == null || c.isEmpty();
    }

    /**
     * 字符串是否不为空
     * @param s 字符串
     * @return 是否
     */
    public static boolean isNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    /**
     * 集合是否不为空
     * @param c 集合
     * @return 是否
     */
    public static boolean isNotEmpty(Collection<?> c) {
        return c != null && !c.isEmpty();
    }

    /**
     * 判断是否是字符串
     * @param obj 待判断 参数
     * @return 是否
     */
    public static boolean isString(Object obj) {
        return obj instanceof String;
    }
}

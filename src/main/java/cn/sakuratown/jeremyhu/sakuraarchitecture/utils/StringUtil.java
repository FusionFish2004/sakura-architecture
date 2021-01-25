package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

public class StringUtil {
    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
}

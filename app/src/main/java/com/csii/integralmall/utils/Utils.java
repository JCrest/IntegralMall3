package com.csii.integralmall.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Utils {

    public static int isInNonTappableRegion(int containerWidth, int pagerWidth, float oldX, float newX) {
        int tappableWidth = pagerWidth;
        int totalWidth = containerWidth;
        int nonTappableWidth = (totalWidth - tappableWidth) / 2;
        if (oldX < nonTappableWidth && newX < nonTappableWidth) {
            return -(int) Math.ceil((nonTappableWidth - newX) / (float) tappableWidth);
        }
        nonTappableWidth = (totalWidth + tappableWidth) / 2;
        if (oldX > nonTappableWidth && newX > nonTappableWidth) {
            return (int) Math.ceil((newX - nonTappableWidth) / (float) tappableWidth);
        }
        return 0;
    }

    public static float getFloat(float value, float minValue, float maxValue) {
        return Math.min(maxValue, Math.max(minValue, value));
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    // 判断大陆手机号码是否规则
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

}

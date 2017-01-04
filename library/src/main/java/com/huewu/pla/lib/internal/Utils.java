package com.huewu.pla.lib.internal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * Created by John on 12/5/16.
 */
public class Utils {
    public static int getDpFromPx(Context mContext, int px) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        return Math.round(px * dm.density);
    }

    public static Drawable getVersionDrawable(int SDK_INT, Context mContext, int drawable) {
        Drawable versionFitDrawable;

        if(SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            versionFitDrawable = mContext.getDrawable(drawable);
        } else {
            versionFitDrawable = mContext.getResources().getDrawable(drawable);
        }

        return versionFitDrawable;
    }
}

package com.ideologer.zamishoapp.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fonts {
    private AssetManager mngr;

    public Fonts(Context context) {
        mngr = context.getAssets();
    }
    private enum AssetTypefaces {
        CrimsonBold,
        CrimsonBoldItalic,
        CrimsonItalic,
        CrimsonRoman,
        CrimsonSemiBold,
        CrimsonSemiBoldItalic
    }

    private Typeface getTypeface(AssetTypefaces font) {
        Typeface tf = null;
        switch (font) {
            case CrimsonBold:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-Bold.ttf");
                break;
            case CrimsonBoldItalic:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-BoldItalic.ttf");
                break;
            case CrimsonItalic:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-Italic.ttf");
                break;
            case CrimsonRoman:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-Roman.ttf");
                break;
            case CrimsonSemiBold:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-Semibold.ttf");
                break;
            case CrimsonSemiBoldItalic:
                tf = Typeface.createFromAsset(mngr,"fonts/Crimson-SemiboldItalic.ttf");
                break;
            default:
                tf = Typeface.DEFAULT;
                break;
        }
        return tf;
    }
    public void setupLayoutTypefaces(View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    setupLayoutTypefaces(child);
                }
            } else if (v instanceof TextView) {
                if (v.getTag().toString().equals(AssetTypefaces.CrimsonBold.toString())){
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonBold));
                }else if (v.getTag().toString().equals(AssetTypefaces.CrimsonBoldItalic.toString())) {
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonBoldItalic));
                }else if (v.getTag().toString().equals(AssetTypefaces.CrimsonItalic.toString())) {
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonItalic));
                }else if (v.getTag().toString().equals(AssetTypefaces.CrimsonRoman.toString())) {
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonRoman));
                }else if (v.getTag().toString().equals(AssetTypefaces.CrimsonSemiBold.toString())) {
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonSemiBold));
                } else if (v.getTag().toString().equals(AssetTypefaces.CrimsonSemiBoldItalic.toString())) {
                    ((TextView)v).setTypeface(getTypeface(AssetTypefaces.CrimsonSemiBoldItalic));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // ignore
        }
    }
}

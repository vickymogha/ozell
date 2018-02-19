package com.ozellcooner.webserive;

import android.graphics.Color;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2/12/2018.
 */

public class ColorUtils {

    public static int parseRgb(String input) {
        try {
            Pattern c = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher m = c.matcher(input);

            if (m.matches())

            {

                return Color.rgb(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), Integer.valueOf(m.group(3)));
            }
        } catch (Exception e) {
            return Color.TRANSPARENT;
        }

        return Color.TRANSPARENT;
    }

}

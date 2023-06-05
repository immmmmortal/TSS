package edu.sumdu.tss.elephant.helper.utils;

import java.util.Random;
import java.util.UUID;

public final class StringUtils {

    private StringUtils() { }

    private static final int LETTER_A_CODE = 97;
    private static final int LETTER_Z_CODE = 122;

    public static String randomAlphaString(int targetStringLength) {
        int leftLimit = LETTER_A_CODE;
        int rightLimit = LETTER_Z_CODE;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }


    public static String replaceLast(String texto, String substituir, String substituto) {
        int pos = texto.lastIndexOf(substituir);
        if (pos > -1) {
            return texto.substring(0, pos)
                    + substituto
                    + texto.substring(pos + substituir.length());
        } else {
            return texto;
        }
    }
}

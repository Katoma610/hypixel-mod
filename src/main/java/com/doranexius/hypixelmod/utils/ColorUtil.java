package com.doranexius.hypixelmod.utils;

import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;

public class ColorUtil {

    //public static final int a = new Color(246, 7, 214, 255).getRGB();
    public static final int b = new Color(0, 255, 0, 255).getRGB();

    public static void setColor(int n2) {
        GlStateManager.color((float)((float)(n2 >> 16 & 0xFF) / 255.0f), (float)((float)(n2 >> 8 & 0xFF) / 255.0f), (float)((float)(n2 & 0xFF) / 255.0f), (float)((float)(n2 >> 24 & 0xFF) / 255.0f));
    }

    public static int a(int n2, int n3, float f2) {
        float f3 = 1;
        float f4;
        float f5 = 2900.0f;
        for (f4 = (float)(System.currentTimeMillis() % (long)((int)f5)) + (float)((n3 - n2) * 9); f4 > f5; f4 -= f5) {
        }
        f4 /= f5;
        if ((double)f3 > 0.5) {
            f4 = 0.5f - (f4 - 0.5f);
        }
        return Color.HSBtoRGB(f4 += 0.5f, f2, 1.0f);
    }
    public static float[] a(int n2) {
        float f2 = (float)(n2 >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(n2 >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(n2 >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(n2 & 0xFF) / 255.0f;
        return new float[]{f3, f4, f5, f2};
    }
    public static int a(float f2, float f3, float f4, float f5) {
        return new Color(f2, f3, f4, f5).getRGB();
    }

    public static int b(int n2, float f2) {
        float[] fArray = ColorUtil.a(n2);
        int n3 = ColorUtil.a(fArray[0], fArray[1], fArray[2], f2);
        return n3;
    }

}

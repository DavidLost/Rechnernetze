package edu.kit.informatik;

public class MathUtils {

    public static int nativePow(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; ++i) {
            result *= a;
        }
        return result;
    }

}

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Application {

    private static final String KEY = "yzbqklnj";

    public static void main(String [] args) {

        findKey(KEY);
    }

    private static void findKey(String key) {
        boolean run = true;
        int number = 0;
        StringBuilder sb;
        MessageDigest md;
        String original = KEY;
        boolean key5 = true, key6 = true;

        try {
            md = MessageDigest.getInstance("MD5");
            while (run) {
                md.update(original.concat(String.valueOf(number)).getBytes());
                byte[] digest = md.digest();
                sb = new StringBuilder();

                for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                if (key5) {
                    key5 = findKeyWith5Zeros(original, sb, number);
                }
                if (key6) {
                    key6 = findKeyWith6Zeros(original, sb, number);
                }
                if (!key5 && !key6) {
                    run = false;
                }
                number ++;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static boolean findKeyWith5Zeros(String original, StringBuilder sb, int number) {
        if (sb.toString().startsWith("00000")) {

            System.out.println(original);
            System.out.println(sb.toString());
            System.out.println(number);
            return false;
        }
        return true;
    }

    private static boolean findKeyWith6Zeros(String original, StringBuilder sb, int number) {
        if (sb.toString().startsWith("000000")) {

            System.out.println(original);
            System.out.println(sb.toString());
            System.out.println(number);
            return false;
        }
        return true;
    }
}

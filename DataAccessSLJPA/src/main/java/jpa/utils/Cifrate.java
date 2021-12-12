package jpa.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Cifrate {

    public static String SHA256(String cadena) {
        MessageDigest md;
        byte[] hash = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            hash = md.digest(cadena.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Error codificando a SHA256 " + e.getMessage());
        }
        return byteToHex(hash);
    }

    private static String byteToHex(byte[] raw) {
        String string = "";
        for (int i = 0; i < raw.length; i++) {
            string += Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1);
        }
        return string;
    }

}

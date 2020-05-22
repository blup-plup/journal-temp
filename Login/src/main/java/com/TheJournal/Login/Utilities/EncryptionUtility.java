package com.TheJournal.Login.Utilities;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class EncryptionUtility {

    public static String stringEncryption(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] otpSalt = (key).getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        otpSalt = sha.digest(otpSalt);

        otpSalt = Arrays.copyOf(otpSalt,16);

        Key aesKey = null;

        aesKey = new SecretKeySpec(otpSalt,"AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1,aesKey);

        byte[] encrypted = cipher.doFinal(key.getBytes());
        String ret = String.valueOf(Hex.encodeHex(encrypted));
        return ret;
    }
}

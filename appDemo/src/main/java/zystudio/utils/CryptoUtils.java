package zystudio.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * Created by wzy on 15-9-8.
 */
public class CryptoUtils {

    private static final String[] KEY_IV = {
        "vc8v7vghw7v278vn2v8239vh29vh890s", "aqkeezgxijvlm2or"
    };

    public static byte[] encrypt(byte[] plainTextBytes) {
        if (ByteUtil.isEmpty(plainTextBytes)) {
            return ByteUtil.EMPTY_BYTES;
        }
        final String KEY = KEY_IV[0];
        final String IV = KEY_IV[1];
        byte[] cipherResult = ByteUtil.EMPTY_BYTES;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = null;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            cipherResult = cipher.doFinal(plainTextBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherResult;
    }

    public static byte[] decrypt(byte[] cipherBytes) {
        if (ByteUtil.isEmpty(cipherBytes)) {
            return ByteUtil.EMPTY_BYTES;
        }
        final String KEY = KEY_IV[0];
        final String IV = KEY_IV[1];
        byte[] plaintext = ByteUtil.EMPTY_BYTES;

        try {
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            plaintext = cipher.doFinal(cipherBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plaintext;
    }

}

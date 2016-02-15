package util;

import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * create by cpp at 2015/12/17
 */
public class AesCrypter {

    private static String ivStr = "0123456789abcdef";

    public AesCrypter() {

    }

    /**
     * Encodes a String in AES-128 with a given key
     *
     * @param password
     * @param text
     * @return String Base64 and AES encoded String
     */
    public String encode(String password, String text) throws EnCryptoException {
        if (password == null || password.length() == 0) {
            throw new EnCryptoException("Please give Password");
        }

        if (password.length() < 16) {
            throw new EnCryptoException("Please a 16 length Password");
        }

        if (text.length() == 0 || text == null) {
            throw new EnCryptoException("Sorry you must  give a text");
        }

        try {
            SecretKeySpec skeySpec = getKey(password);
            byte[] clearText = text.getBytes("UTF8");
            final byte[] iv = ivStr.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
            String encrypedValue = Base64.encodeToString(cipher.doFinal(clearText), Base64.NO_WRAP);
            return encrypedValue;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        throw new EnCryptoException("加密失败");
    }

    /**
     * Decodes a String using AES-128 and Base64
     *
     * @param password
     * @param text
     * @return desoded String
     */
    public String decode(String password, String text)
            throws DeCryptoException {

        if (password == null || password.length() == 0) {
            throw new DeCryptoException("Please give Password");
        }

        if (password.length() < 16) {
            throw new DeCryptoException("Please a 16 length Password");
        }

        if (text == null || text.length() == 0) {
            throw new DeCryptoException("Sorry you must  give a text");
        }

        try {
            SecretKey key = getKey(password);
            final byte[] iv = ivStr.getBytes();
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            byte[] encrypedPwdBytes = Base64.decode(text, Base64.NO_WRAP);
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
            byte[] decrypedValueBytes = (cipher.doFinal(encrypedPwdBytes));
            String decrypedValue = new String(decrypedValueBytes);
            return decrypedValue;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        throw new DeCryptoException("解密失败");
    }

    /**
     * Generates a SecretKeySpec for given password
     *
     * @param password
     * @return SecretKeySpec
     * @throws UnsupportedEncodingException
     */
    public SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
        SecretKeySpec key = new SecretKeySpec(password.getBytes("UTF8"), "AES");
        return key;
    }


    public class CryptoException extends Exception {
        public CryptoException(String message) {
            super(message);
        }
    }

    public class EnCryptoException extends CryptoException {
        public EnCryptoException(String message) {
            super(message);
        }
    }

    public class DeCryptoException extends CryptoException {
        public DeCryptoException(String message) {
            super(message);
        }
    }

}

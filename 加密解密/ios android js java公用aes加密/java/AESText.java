package util;

/**
 * Created by Administrator on 2015/12/17.
 */
public class AESText {

    public static void main(String[]args){
        AesCrypter aesCrypter = new AesCrypter();
        try {
            String encode = aesCrypter.encode("0123456789abcdef", "nihao");
            System.out.println(encode);
            String decode = aesCrypter.decode("0123456789abcdef", "R5iPW0CaN8kh++IS88++yg==");
            System.out.println(decode);
        } catch (AesCrypter.EnCryptoException e) {
            e.printStackTrace();
        } catch (AesCrypter.DeCryptoException e) {
            e.printStackTrace();
        }
    }
}

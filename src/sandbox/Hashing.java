package sandbox;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

/**
 * Hashingは、SHA-256アルゴリズムを用いて、キーをハッシュ化するクラスです。
 * @param password 編集・削除キー入力値
 * */

public class Hashing {
    public String hash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); //SHA-256のアルゴリズムを用いて、ハッシュ化を行う
        byte[] b = password.getBytes(); //ハッシュ化する文字列をバイト配列に変換する
        String hashedPassword = DatatypeConverter.printHexBinary(messageDigest.digest(b)); //Stringに再変換してreturnする
        return hashedPassword;
    }
}

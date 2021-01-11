package org.shaneking.ling.zero.crypto;

import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.security.Key0;
import org.shaneking.ling.zero.util.Hex0;
import org.shaneking.ling.zero.util.LruMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Base64;
import java.util.UUID;

/**
 * https://github.com/ShaneKing/sk-js/blob/master/src/Crypto0.js
 */
public class Crypto0 {
  //ILoveYou
  public static final String DEFAULT_SALT = "494c6f7665596f75";
  private static final LruMap<String, Cipher> SALT_DECRYPT_MAP = new LruMap<>(13);
  private static final LruMap<String, Cipher> SALT_ENCRYPT_MAP = new LruMap<>(13);

  public static String aesDecrypt(String encrypted) throws Exception {
    return aesDecrypt(encrypted, DEFAULT_SALT);
  }

  public static String aesDecrypt(String encrypted, String salt) throws Exception {
    return aesDecrypt(encrypted, salt, StandardCharsets.UTF_8);
  }

  public static String aesDecrypt(String encrypted, String salt, Charset charset) throws Exception {
    return new String(SALT_DECRYPT_MAP.get(salt, () -> {
//      KeyGenerator.getInstance(Key0.AES).init(128);
      Cipher cipher = Cipher.getInstance(Cipher0.AES_ECB_PKCS5Padding);
      cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(salt.getBytes(charset), Key0.AES));
      return cipher;
    }).doFinal(Base64.getDecoder().decode(encrypted)));
  }

  public static String aesEncrypt(String content) throws Exception {
    return aesEncrypt(content, DEFAULT_SALT);
  }

  public static String aesEncrypt(String content, String salt) throws Exception {
    return aesEncrypt(content, salt, StandardCharsets.UTF_8);
  }

  public static String aesEncrypt(String content, String salt, Charset charset) throws Exception {
    return Base64.getEncoder().encodeToString(SALT_ENCRYPT_MAP.get(salt, () -> {
//      KeyGenerator.getInstance(Key0.AES).init(128);
      Cipher cipher = Cipher.getInstance(Cipher0.AES_ECB_PKCS5Padding);
      cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(salt.getBytes(), Key0.AES));
      return cipher;
    }).doFinal(content.getBytes(charset)));
  }

  public static String genKey() {
    return genKey(UUID.randomUUID().toString().split(String0.MINUS)[0]);
  }

  public static String genKey(String eightLengthString) {
    if (String0.isNull2Empty(eightLengthString) || eightLengthString.length() != 8) {
      throw new ZeroException(MessageFormat.format("Must 8 length string : {0}", String.valueOf(eightLengthString)));
    }
    return Hex0.encodeHexString(eightLengthString.getBytes());
  }
}

package org.shaneking.ling.zero.crypto;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.shaneking.ling.zero.lang.String0;
import org.shaneking.ling.zero.lang.ZeroException;
import org.shaneking.ling.zero.security.Key0;
import org.shaneking.ling.zero.util.Hex0;
import org.shaneking.ling.zero.util.LruMap;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

/**
 * https://github.com/ShaneKingFriendly/sk-js/blob/master/src/SKC1.js
 */
@Slf4j
public class SKC1 {
  public static final String ALGORITHM_NAME = "SKC1";
  //ILoveYou
  public static final String DEFAULT_SALT = "494c6f7665596f75";
  public static final String ENCRYPTED_PREFIX = String0.wrapBracket(ALGORITHM_NAME);

  private static final LruMap<String, Cipher> SALT_DECRYPT_MAP = new LruMap<>(13);
  private static final LruMap<String, Cipher> SALT_ENCRYPT_MAP = new LruMap<>(13);

  public static String decrypt(String encrypted) {
    return decrypt(encrypted, true);
  }

  public static String decrypt(String encrypted, boolean quietly) {
    return decrypt(encrypted, DEFAULT_SALT, quietly);
  }

  public static String decrypt(String encrypted, String salt) {
    return decrypt(encrypted, salt, true);
  }

  public static String decrypt(String encrypted, String salt, boolean quietly) {
    return decrypt(encrypted, salt, StandardCharsets.UTF_8, quietly);
  }

  public static String decrypt(String encrypted, String salt, Charset charset, boolean quietly) {
    try {
      return new String(SALT_DECRYPT_MAP.get(salt, () -> {
        //      KeyGenerator.getInstance(Key0.AES).init(128);
        Cipher cipher = Cipher.getInstance(Cipher0.AES_ECB_PKCS5Padding);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(salt.getBytes(charset), Key0.AES));
        return cipher;
      }).doFinal(Base64.getDecoder().decode(encrypted.getBytes(charset))), charset);
    } catch (Exception e) {
      log.error(String.join(String0.COMMA, encrypted, salt, charset.toString()), e);
      if (quietly) {
        return encrypted;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static String encrypt(String content) {
    return encrypt(content, true);
  }

  public static String encrypt(String content, boolean quietly) {
    return encrypt(content, DEFAULT_SALT, quietly);
  }

  public static String encrypt(String content, String salt) {
    return encrypt(content, salt, true);
  }

  public static String encrypt(String content, String salt, boolean quietly) {
    return encrypt(content, salt, StandardCharsets.UTF_8, quietly);
  }

  public static String encrypt(@NonNull String content, String salt, Charset charset, boolean quietly) {
    try {
      return Base64.getEncoder().encodeToString(SALT_ENCRYPT_MAP.get(salt, () -> {
        //      KeyGenerator.getInstance(Key0.AES).init(128);
        Cipher cipher = Cipher.getInstance(Cipher0.AES_ECB_PKCS5Padding);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(salt.getBytes(), Key0.AES));
        return cipher;
      }).doFinal(content.getBytes(charset)));
    } catch (Exception e) {
      log.error(String.join(String0.COMMA, content, salt, charset.toString()), e);
      if (quietly) {
        return content;
      } else {
        throw new ZeroException(e);
      }
    }
  }

  public static String salt() {
    return salt(UUID.randomUUID().toString().split(String0.MINUS)[0]);
  }

  public static String salt(String eightLengthString) {
    if (String0.isNull2Empty(eightLengthString) || eightLengthString.length() != 8) {
      throw new IllegalArgumentException("Must 8 length string : " + eightLengthString);
    }
    return Hex0.encodeHexString(eightLengthString.getBytes());
  }
}

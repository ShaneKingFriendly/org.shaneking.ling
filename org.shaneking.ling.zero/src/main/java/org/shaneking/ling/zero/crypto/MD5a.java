package org.shaneking.ling.zero.crypto;

import org.shaneking.ling.zero.util.Hex0;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5a {
  public static final String ALGORITHM_NAME = "MD5";

  public static String encrypt(String content) throws NoSuchAlgorithmException {
    return content == null ? null : new String(Hex0.encodeHex(MessageDigest.getInstance(ALGORITHM_NAME).digest(content.getBytes(StandardCharsets.UTF_8))));
  }
}

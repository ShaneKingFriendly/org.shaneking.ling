package org.shaneking.ling.zero.text;

import lombok.NonNull;
import org.shaneking.ling.zero.lang.String0;

import java.text.MessageFormat;

public class MF0 {
  public static String fmt(@NonNull String pattern, Object... arguments) {
    return MessageFormat.format(pattern.replace(String0.SINGLE_QUOTATION, String0.SINGLE_QUOTATION + String0.SINGLE_QUOTATION), arguments);
  }
}

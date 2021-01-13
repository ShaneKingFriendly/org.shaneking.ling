package org.shaneking.ling.zero.io;

import org.shaneking.ling.zero.lang.String0;

import java.io.File;

public class File0 {
  public static final int DEFAULT_BUFFER_SIZE = 256 * 1024;
  public static final String ILLEGAL_FILENAME_REGEX = "[{/\\\\:*?\"<>|}]";
  //Text
  public static final String TYPE_ASP = "asp";
  public static final String TYPE_CLASSPATH = "classpath";
  public static final String TYPE_CONF = "conf";
  public static final String TYPE_CSV = "csv";
  public static final String TYPE_GITIGNORE = "gitignore";
  public static final String TYPE_INI = "ini";
  public static final String TYPE_JAVA = "java";
  public static final String TYPE_JSON = "json";
  public static final String TYPE_JSP = "jsp";
  public static final String TYPE_LOG = "log";
  public static final String TYPE_PHP = "php";
  public static final String TYPE_PROJECT = "project";
  public static final String TYPE_SQL = "sql";
  public static final String TYPE_SVN = "svn";
  public static final String TYPE_SYS = "sys";
  public static final String TYPE_TXT = "txt";
  public static final String TYPE_XML = "xml";
  //Document
  public static final String TYPE_DOC = "doc";
  public static final String TYPE_DOCX = "docx";
  public static final String TYPE_PDF = "pdf";
  public static final String TYPE_PPT = "ppt";
  public static final String TYPE_PPTX = "pptx";
  public static final String TYPE_XLS = "xls";
  public static final String TYPE_XLSX = "xlsx";
  //Executable
  public static final String TYPE_BAT = "bat";
  public static final String TYPE_BIN = "bin";
  public static final String TYPE_DEB = "deb";
  public static final String TYPE_DMG = "dmg";
  public static final String TYPE_EXE = "exe";
  public static final String TYPE_MSI = "msi";
  public static final String TYPE_PKG = "pkg";
  public static final String TYPE_RPM = "rpm";
  public static final String TYPE_SH = "sh";
  //Compiled
  public static final String TYPE_A = "a";
  public static final String TYPE_CLASS = "class";
  public static final String TYPE_DLL = "dll";
  public static final String TYPE_KO = "ko";
  public static final String TYPE_SO = "so";
  //Compressed
  public static final String TYPE_ARJ = "arj";
  public static final String TYPE_GZ = "gz";
  public static final String TYPE_RAR = "rar";
  public static final String TYPE_TAR = "tar";
  public static final String TYPE_ZIP = "zip";
  //Web
  public static final String TYPE_CSS = "css";
  public static final String TYPE_HTM = "htm";
  public static final String TYPE_HTML = "html";
  public static final String TYPE_JS = "js";
  //Mirror
  public static final String TYPE_MDF = "mdf";
  public static final String TYPE_ISO = "iso";
  //Image
  public static final String TYPE_BMP = "bmp";
  public static final String TYPE_GIF = "gif";
  public static final String TYPE_JPG = "jpg";
  public static final String TYPE_PNG = "png";
  public static final String TYPE_TIF = "tif";
  public static final String TYPE_SWF = "swf";
  //Audio
  public static final String TYPE_MID = "mid";
  public static final String TYPE_MP3 = "mp3";
  public static final String TYPE_WAV = "wav";
  public static final String TYPE_WMA = "wma";
  //Video
  public static final String TYPE_AVI = "avi";
  public static final String TYPE_MOV = "mov";
  public static final String TYPE_MPEG = "mpeg";
  public static final String TYPE_MPG = "mpg";
  public static final String TYPE_RM = "rm";
  public static final String TYPE_RMVB = "rmvb";
  public static final String TYPE_WMV = "wmv";
  //Others
  public static final String TYPE_ASF = "asf";
  public static final String TYPE_TMP = "tmp";

  public static File join(File parent, String... strings) {
    return join(File.separator, parent, strings);
  }

  public static File join(String separator, String... strings) {
    return new File(String.join(separator, strings));
  }

  public static File join(String separator, File parent, String... strings) {
    return new File(parent, String.join(separator, strings));
  }

  public static String suffix(String fileTypeName) {
    return String0.DOT + fileTypeName;
  }
}

package org.nypl.simplified.app.reader;

import java.util.concurrent.ConcurrentHashMap;

import com.io7m.jnull.NullCheck;

/**
 * The default implementation of the {@link ReaderHTTPMimeMapType} type.
 */

public final class ReaderHTTPMimeMap implements ReaderHTTPMimeMapType
{
  public static ReaderHTTPMimeMapType newMap(
    final String in_default_type)
  {
    return new ReaderHTTPMimeMap(in_default_type);
  }

  private final String                            default_type;
  private final ConcurrentHashMap<String, String> types;

  private ReaderHTTPMimeMap(
    final String in_default_type)
  {
    this.default_type = NullCheck.notNull(in_default_type);
    this.types = new ConcurrentHashMap<String, String>();
    this.types.put("asc", "text/plain");
    this.types.put("class", "application/octet-stream");
    this.types.put("css", "text/css");
    this.types.put("doc", "application/msword");
    this.types.put("exe", "application/octet-stream");
    this.types.put("flv", "video/x-flv");
    this.types.put("gif", "image/gif");
    this.types.put("htm", "text/html");
    this.types.put("html", "text/html");
    this.types.put("java", "text/x-java-source, text/java");
    this.types.put("jpeg", "image/jpeg");
    this.types.put("jpg", "image/jpeg");
    this.types.put("js", "application/javascript");
    this.types.put("m3u", "audio/mpeg-url");
    this.types.put("mov", "video/quicktime");
    this.types.put("mp3", "audio/mpeg");
    this.types.put("mp4", "video/mp4");
    this.types.put("ogg", "application/x-ogg");
    this.types.put("ogv", "video/ogg");
    this.types.put("pdf", "application/pdf");
    this.types.put("png", "image/png");
    this.types.put("swf", "application/x-shockwave-flash");
    this.types.put("txt", "text/plain");
    this.types.put("webm", "video/webm");
    this.types.put("xhtml", "application/xhtml+xml");
    this.types.put("xml", "application/xml");
    this.types.put("zip", "application/octet-stream");
  }

  @Override public String getDefaultMimeType()
  {
    return this.default_type;
  }

  @Override public String getMimeTypeForSuffix(
    final String suffix)
  {
    NullCheck.notNull(suffix);
    if (this.types.containsKey(suffix)) {
      return NullCheck.notNull(this.types.get(suffix));
    }
    return this.getDefaultMimeType();
  }
}
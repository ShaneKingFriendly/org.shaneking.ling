package org.shaneking.ling.jackson.ctx;

/*
ThreadLocal is not perfect in spring mvc env.
 */
@Deprecated
public class JacksonCtx {
  public static final ThreadLocal<String> scenario = new ThreadLocal<>();
}

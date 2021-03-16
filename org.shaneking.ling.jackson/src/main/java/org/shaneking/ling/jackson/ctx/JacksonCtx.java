package org.shaneking.ling.jackson.ctx;

@Deprecated
public class JacksonCtx {
  public static final InheritableThreadLocal<String> scenario = new InheritableThreadLocal<>();///ThreadLocal is not perfect in sub-thread scenario.
}

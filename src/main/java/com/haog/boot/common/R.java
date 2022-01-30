package com.haog.boot.common;

// 统一返回数据结果

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
  private String resultCode;
  private String resultMsg;
  private Object data = new HashMap<> ( );

  public R() {
  }

  // 成功静态方法
  public static R success() {
    R r = new R ( );
    r.setResultCode (ResultCode.SUCCESS);
    r.setResultMsg ("成功");
    return r;
  }

  // 失败静态方法
  public static R error() {
    R r = new R ( );
    r.setResultCode (ResultCode.ERROR);
    r.setResultMsg ("失败");
    return r;
  }

  public R code(String code) {
    this.setResultCode (code);
    return this;
  }

  public R message(String message) {
    this.setResultMsg (message);
    return this;
  }

  public R data(Object value){
    this.setData (value);
    return this;
  }

  public R data(Map<String,Object>map){
    this.setData (map);
    return this;
  }
}

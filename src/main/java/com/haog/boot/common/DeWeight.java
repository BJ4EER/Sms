package com.haog.boot.common;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class DeWeight {
  /**
   * 去除批量导入时重复的教职工Id
   * @param list
   * @return
   */
  public List<String> deWeight(List<String> list) {
    LinkedHashSet<String> hashSet = new LinkedHashSet<> (list);
    List<String> newList = new ArrayList<> (hashSet);
    return newList;
  }
}

package com.haog.boot.sms.vo;

import lombok.Data;

@Data
public class StaffInvigilateVo {
  private Integer page;
  private String staffId;
  private String staffName;
  private String examName;
  private String examType;
  private String session;
  private String term;
}

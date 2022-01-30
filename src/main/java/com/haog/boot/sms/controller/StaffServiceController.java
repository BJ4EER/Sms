package com.haog.boot.sms.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.haog.boot.common.R;
import com.haog.boot.sms.entity.StaffService;
import com.haog.boot.sms.service.impl.StaffServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HaoG
 * @since 2022-01-29
 */
@RestController
@RequestMapping("/sms/staffService")
public class StaffServiceController {

  @Autowired
  StaffServiceServiceImpl staffServiceService;

  /**
   * 修改教职工服务量表备注
   * @param staffServiceInfo
   * @return
   */
  @PostMapping("updateNote")
  public R updateNote(@RequestBody Map<String,String> staffServiceInfo) {
    // 根据staffId获取对应教职工服务量表信息
    StaffService staffService = staffServiceService.getById (staffServiceInfo.get ("staffId"));
    // 设置备注
    staffService.setNote (staffServiceInfo.get ("note"));
    // 修改备注
    Boolean result = staffServiceService.updateById (staffService);
    // 返回成功信息
    return R.success ( ).data (result);
  }

}


package com.haog.boot.sms.service;

import com.haog.boot.sms.entity.StaffService;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HaoG
 * @since 2022-01-29
 */
public interface StaffServiceService extends IService<StaffService> {
  void updateService(String staffId);
}

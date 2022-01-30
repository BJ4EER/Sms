package com.haog.boot.sms.service.impl;

import com.haog.boot.sms.entity.StaffService;
import com.haog.boot.sms.mapper.StaffServiceMapper;
import com.haog.boot.sms.service.StaffServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author HaoG
 * @since 2022-01-29
 */
@Service
public class StaffServiceServiceImpl extends ServiceImpl<StaffServiceMapper, StaffService> implements StaffServiceService {

  @Override
  public void updateService(String staffId) {
    System.out.println (staffId);
    StaffService staffService1 = getById (staffId);
    if (staffService1 == null) {
      StaffService staffService2 = new StaffService ( );
      staffService2.setStaffId (staffId);
      staffService2.setServiceVolume (1);
      save (staffService2);
    } else {
      Integer serviceVolume = staffService1.getServiceVolume ( ) + 1;
      staffService1.setStaffId (staffId);
      staffService1.setServiceVolume (serviceVolume);
      updateById (staffService1);
    }
  }
}

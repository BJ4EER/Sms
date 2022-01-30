package com.haog.boot.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haog.boot.sms.entity.StaffInvigilate;
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

  @Autowired
  StaffInvigilateServiceImpl staffInvigilateService;

  /**
   * 单条添加教职工监考信息时更新教职工服务量
   *
   * @param staffId
   */
  @Override
  public void updateAddService(String staffId) {
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

  /**
   * 批量添加教职工监考信息时更新教职工服务量
   *
   * @param staffId
   */
  @Override
  public void updateAddBatchService(String staffId) {
    System.out.println (staffId);
    QueryWrapper<StaffInvigilate> wrapper = new QueryWrapper<> ( );
    wrapper.eq ("staff_id", staffId);
    Integer invigilateNums = staffInvigilateService.count (wrapper);
    StaffService staffService1 = getById (staffId);
    if (staffService1 == null) {
      StaffService staffService2 = new StaffService ( );
      staffService2.setStaffId (staffId);
      staffService2.setServiceVolume (invigilateNums);
      save (staffService2);
    } else {
      Integer serviceVolume = staffService1.getServiceVolume ( ) + invigilateNums;
      staffService1.setStaffId (staffId);
      staffService1.setServiceVolume (serviceVolume);
      updateById (staffService1);
    }
  }

  /**
   * 单条删除教职工监考信息时更新教职工服务量
   *
   * @param staffId
   */
  @Override
  public void updateDeleteService(String staffId) {
    System.out.println (staffId);
    StaffService staffService = getById (staffId);
    Integer serviceVolume = staffService.getServiceVolume ( ) - 1;
    staffService.setServiceVolume (serviceVolume);
    updateById (staffService);
  }

  /**
   * 批量删除教职工监考信息时更新教职工服务量
   * @param staffId
   */
  @Override
  public void updateDeleteBatchService(String staffId) {
    System.out.println (staffId);
    StaffService staffService = getById (staffId);
    QueryWrapper<StaffInvigilate> wrapper = new QueryWrapper<> ( );
    wrapper.eq ("staff_id", staffId);
    Integer invigilateNums = staffInvigilateService.count (wrapper);
    staffService.setServiceVolume (invigilateNums);
    updateById (staffService);
  }
}

package com.haog.boot.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haog.boot.sms.entity.StaffInvigilate;
import com.haog.boot.sms.mapper.StaffInvigilateMapper;
import com.haog.boot.sms.service.StaffInvigilateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haog.boot.sms.vo.StaffInvigilateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author HaoG
 * @since 2022-01-28
 */
@Service
public class StaffInvigilateServiceImpl extends ServiceImpl<StaffInvigilateMapper, StaffInvigilate> implements StaffInvigilateService {
  @Autowired
  StaffInvigilateMapper staffInvigilateMapper;

  @Override
  public List<StaffInvigilateVo> selectStaInvigilate(Page<StaffInvigilateVo> page, QueryWrapper wrapper) {
    return staffInvigilateMapper.selectStaInvigilate (page,wrapper);
  }
}

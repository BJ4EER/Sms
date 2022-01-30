package com.haog.boot.sms.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haog.boot.sms.entity.StaffInvigilate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.haog.boot.sms.vo.StaffInvigilateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author HaoG
 * @since 2022-01-28
 */
public interface StaffInvigilateService extends IService<StaffInvigilate> {
  public List<StaffInvigilateVo> selectStaInvigilate(Page<StaffInvigilateVo> page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}

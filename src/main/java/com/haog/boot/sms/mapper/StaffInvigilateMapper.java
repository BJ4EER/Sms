package com.haog.boot.sms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haog.boot.sms.entity.StaffInvigilate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haog.boot.sms.vo.StaffInvigilateVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author HaoG
 * @since 2022-01-28
 */
@Repository
public interface StaffInvigilateMapper extends BaseMapper<StaffInvigilate> {
  List<StaffInvigilateVo> selectStaInvigilate(Page<StaffInvigilateVo> page, @Param(Constants.WRAPPER) QueryWrapper wrapper);
}

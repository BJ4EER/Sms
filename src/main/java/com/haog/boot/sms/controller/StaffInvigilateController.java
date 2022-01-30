package com.haog.boot.sms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haog.boot.common.DeWeight;
import com.haog.boot.common.Paging;
import com.haog.boot.common.R;
import com.haog.boot.common.ReadExcel;
import com.haog.boot.sms.entity.StaffInvigilate;
import com.haog.boot.sms.entity.StaffService;
import com.haog.boot.sms.service.impl.StaffInvigilateServiceImpl;
import com.haog.boot.sms.service.impl.StaffServiceServiceImpl;
import com.haog.boot.sms.vo.StaffInvigilateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author HaoG
 * @since 2022-01-28
 */
@RestController
@RequestMapping("/sms/staffInvigilate")
public class StaffInvigilateController {

  @Autowired
  StaffInvigilateServiceImpl staffInvigilateService;

  @Autowired
  StaffServiceServiceImpl staffServiceService;

  /**
   * 单条添加教职工监考经历信息
   *
   * @param staffInvigilate
   * @return
   */
  @PostMapping("add")
  public R addStaffInvigilate(@RequestBody StaffInvigilate staffInvigilate) {
    System.out.println (staffInvigilate);
    // 修改监考经历中考试方式的值与exam_type表格id对应
    staffInvigilate.updateExamType ( );
    // 单条添加教职工经历到数据库中
    Boolean result = staffInvigilateService.save (staffInvigilate);
    // 更新教职工服务量
    staffServiceService.updateAddService (staffInvigilate.getStaffId ( ));
    // 返回成功信息
    return R.success ( ).data (result);
  }

  /**
   * 批量添加教职工监考经历信息
   *
   * @param file
   * @return
   * @throws IOException
   */
  @PostMapping("addBatch")
  public R addBatchStaffInvigilate(@RequestParam("file") MultipartFile file) throws IOException {
    // 创建教职工监考经历对象(StaffInvigilate)集合Collection
    Collection<StaffInvigilate> objectList = new ArrayList<> ( );
    // 实例化读取Excel文件类
    ReadExcel readExcel = new ReadExcel ( );
    // 赋值所读取到的Excel文件数据到集合listStaffInvigilate中
    List<List> listStaffInvigilate = readExcel.readExcel (file);
    // 赋值所读取到的Excel文件教职工Id到listStaffId中
    List<String> listStaffId = new ArrayList<> ( );
    // 根据集合长度依次新建对象(StaffInvigilate)，然后添加到该对象集合Collection
    for (int i = 0; i < listStaffInvigilate.size ( ); i++) {
      StaffInvigilate staffInvigilate = new StaffInvigilate ( );
      staffInvigilate.setTerm ((String) listStaffInvigilate.get (i).get (0));
      staffInvigilate.setStaffId ((String) listStaffInvigilate.get (i).get (1));
      staffInvigilate.setExamType ((String) listStaffInvigilate.get (i).get (3));
      staffInvigilate.updateExamType ( );
      staffInvigilate.setExamName ((String) listStaffInvigilate.get (i).get (4));
      staffInvigilate.setSession (Integer.parseInt (listStaffInvigilate.get (i).get (5).toString ( )));
      objectList.add (staffInvigilate);
      listStaffId.add ((String) listStaffInvigilate.get (i).get (1));
    }
    // 批量添加教职工监考经历到数据库中
    Boolean result = staffInvigilateService.saveBatch (objectList);
    // 去除listStaffId中重复的教职工Id
    DeWeight deWeight = new DeWeight ( );
    List<String> newListStaffId = deWeight.deWeight (listStaffId);
    // 更新教职工服务量
    newListStaffId.forEach ((e) -> {
      staffServiceService.updateAddBatchService (e);
    });
    // 返回成功信息
    return R.success ( );
  }

  /**
   * 查询教师监考经历
   *
   * @param staffInvigilateVo
   * @return
   */
  @PostMapping("queryList")
  public R queryListStaffInvigilate(@RequestBody StaffInvigilateVo staffInvigilateVo) {
    System.out.println (staffInvigilateVo);
    // 设置分页查询
    Page<StaffInvigilateVo> page = new Page<> (staffInvigilateVo.getPage ( ), Paging.LIMIT);
    // 设置查询条件
    QueryWrapper<StaffInvigilateVo> wrapper = new QueryWrapper<> ( );
    Map<String, String> map = new HashMap<> ( );
    map.put ("si.staff_id", staffInvigilateVo.getStaffId ( ));
    map.put ("sp.staff_name", staffInvigilateVo.getStaffName ( ));
    map.put ("exam_name", staffInvigilateVo.getExamName ( ));
    map.put ("exam_type", staffInvigilateVo.getExamType ( ));
    map.put ("session", staffInvigilateVo.getSession ( ));
    map.put ("term", staffInvigilateVo.getTerm ( ));
    wrapper.allEq (map, false);
    // 按条件查询教职工监考信息
    List<StaffInvigilateVo> voList = staffInvigilateService.selectStaInvigilate (page, wrapper);
    // 返回成功信息
    return R.success ( ).data (voList);
  }

  /**
   * 修改监考经历
   *
   * @param staffInvigilate
   * @return
   */
  @PostMapping("update")
  public R updateStaffInvigilate(@RequestBody StaffInvigilate staffInvigilate) {
    System.out.println (staffInvigilate);
    // 根据id查询教职工监考经历获取乐观锁版本号
    StaffInvigilate staffInvigilateServiceById = staffInvigilateService.getById (staffInvigilate.getId ( ));
    staffInvigilate.setVersion (staffInvigilateServiceById.getVersion ( ));
    // 修改监考经历中考试方式的值与exam_type表格id对应
    staffInvigilate.updateExamType ( );
    // 根据id修改教职工监考经历
    Boolean result = staffInvigilateService.updateById (staffInvigilate);
    // 返回成功信息
    return R.success ( ).data (result);
  }

  /**
   * 单条删除教职工监考信息
   *
   * @param staffInvigilate
   * @return
   */
  @PostMapping("delete")
  public R deleteStaffInvigilate(@RequestBody StaffInvigilate staffInvigilate) {
    // 根据id删除教职工监考信息
    Boolean result = staffInvigilateService.removeById (staffInvigilate.getId ( ));
    // 更新教职工服务量
    staffServiceService.updateDeleteService (staffInvigilate.getStaffId ( ));
    // 返回成功信息
    return R.success ( ).data (result);
  }

  /**
   * 批量删除教职工监考信息
   * @param objectCollection
   * @return
   */
  @PostMapping("deleteBatch")
  public R deleteBatchStaffInvigilate(@RequestBody Collection<StaffInvigilate> objectCollection) {
    // 分别取出对象集合中的id和staffId分别放入listId和listStaffId中
    List<Long> listId = new ArrayList<> ( );
    List<String> listStaffId = new ArrayList<> ( );
    objectCollection.forEach ((e) -> {
      listId.add (e.getId ( ));
      listStaffId.add (e.getStaffId ( ));
    });
    // 批量删除教职工监考信息
    staffInvigilateService.removeByIds (listId);
    // 去除listStaffId中重复的staffId
    DeWeight deWeight = new DeWeight ( );
    List<String> newListStaffId = deWeight.deWeight (listStaffId);
    // 更新教职工服务量
    newListStaffId.forEach ((e) -> {
      staffServiceService.updateDeleteBatchService (e);
    });
    // 返回成功信息
    return R.success ( );
  }
}


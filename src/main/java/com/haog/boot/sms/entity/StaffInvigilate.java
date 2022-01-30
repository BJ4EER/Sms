package com.haog.boot.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author HaoG
 * @since 2022-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="StaffInvigilate对象", description="")
public class StaffInvigilate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "学期")
    private String term;

    @ApiModelProperty(value = "考试名称")
    private String examName;

    @ApiModelProperty(value = "考试类别：1校内考试，2社会考试")
    private String examType;

    @ApiModelProperty(value = "教职工id")
    private String staffId;

    @ApiModelProperty(value = "场次")
    private Integer session;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    public void updateExamType(){
        if (getExamType ().equals ("社会考试")){
            setExamType ("1");
        }else {
            setExamType ("2");
        }
    }
}

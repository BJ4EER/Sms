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
 * @since 2022-01-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="StaffService对象", description="")
public class StaffService implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "教职工id")
    @TableId(value = "staff_id", type = IdType.ASSIGN_ID)
    private String staffId;

    @ApiModelProperty(value = "公告服务量")
    private Integer serviceVolume;

    private String note;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Integer version;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}

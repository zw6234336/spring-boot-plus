package io.geekidea.boot.material.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@TableName("material_unit")
@Schema(description = "物料单位")
public class MaterialUnit {

    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键")
    private Long id;
    
    @Schema(description = "单位编码")
    private String code;
    
    @Schema(description = "单位名称") 
    private String name;
    
    @Schema(description = "备注")
    private String remark;
    
    @Schema(description = "状态 1:启用 0:禁用")
    private Boolean status;
    
    @Schema(description = "创建时间")
    private Date createTime;
    
    @Schema(description = "修改时间")
    private Date updateTime;
}

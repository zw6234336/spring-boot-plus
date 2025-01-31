package io.geekidea.boot.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 物料单位视图对象
 */
@Data
@Schema(description = "物料单位返回结果")
public class MaterialUnitVo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Schema(description = "主键ID")
    private Long id;
    
    @Schema(description = "单位编码")
    private String unitCode;
    
    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "删除标识: 0-未删除 1-已删除")
    private Boolean deleted;
    
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
    
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;
}

package io.geekidea.boot.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 物料单位传输对象
 */
@Data
@Schema(description = "物料单位请求参数")
public class MaterialUnitDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;
    
    @NotBlank(message = "单位编码不能为空")
    @Size(max = 32, message = "单位编码长度不能超过32个字符")
    @Schema(description = "单位编码")
    private String unitCode;
    
    @NotBlank(message = "单位名称不能为空")
    @Size(max = 64, message = "单位名称长度不能超过64个字符")
    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "删除标识：0-未删除 1-已删除")
    private Boolean deleted = false;
}

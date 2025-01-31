package io.geekidea.boot.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Schema(description = "物料单位DTO")
public class MaterialUnitDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @NotBlank(message = "单位编码不能为空")
    @Size(max = 32, message = "单位编码长度不能超过32个字符")
    @Schema(description = "单位编码", required = true)
    private String unitCode;

    @NotBlank(message = "单位名称不能为空")
    @Size(max = 64, message = "单位名称长度不能超过64个字符")
    @Schema(description = "单位名称", required = true)
    private String unitName;

    @Size(max = 200, message = "备注长度不能超过200个字符")
    @Schema(description = "备注")
    private String remark;

    @Schema(description = "状态：0-禁用 1-启用")
    private Boolean status;
}

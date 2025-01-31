package io.geekidea.boot.material.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "物料单位查询参数")
public class MaterialUnitQuery extends BasePageQuery {
    
    @Schema(description = "单位编码")
    private String unitCode;
    
    @Schema(description = "单位名称")
    private String unitName;
}

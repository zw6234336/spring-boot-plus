package io.geekidea.boot.material.query;

import io.geekidea.boot.framework.page.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 物料单位查询参数对象
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "物料单位查询参数")
public class MaterialUnitQuery extends BasePageQuery {
    
    @Schema(description = "单位编码")
    private String unitCode;

    @Schema(description = "单位名称")
    private String unitName;

    @Schema(description = "删除标识 0-未删除 1-已删除")
    private Boolean deleted;

    @Schema(description = "关键字搜索")
    private String keyword;

    @Schema(description = "排序字段")
    private String sortField;

    @Schema(description = "排序方向 asc/desc")
    private String sortOrder;
}

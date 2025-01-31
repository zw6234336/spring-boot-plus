package io.geekidea.boot.material.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.hutool.db.PageResult;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import io.geekidea.boot.user.vo.UserVo;

import java.util.List;

public interface MaterialUnitService extends IService<MaterialUnit> {
    
    /**
     * 添加物料单位
     */
    void add(MaterialUnitDto dto);

    /**
     * 批量添加物料单位
     */
    void batchAdd(List<MaterialUnitDto> dtoList);

    /**
     * 更新物料单位
     */
    void update(MaterialUnitDto dto);

    /**
     * 删除物料单位
     */
    void delete(Long id);

    /**
     * 获取物料单位详情
     */
    MaterialUnitVo info(Long id);

    /**
     * 分页查询物料单位
     */
    Paging<MaterialUnitVo> getPage(MaterialUnitQuery query);
}

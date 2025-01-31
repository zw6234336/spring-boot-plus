package io.geekidea.boot.material.service;

import com.baomidou.mybatisplus.extension.service.IService;

import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.vo.MaterialUnitVo;

public interface MaterialUnitService extends IService<MaterialUnit> {

    void add(MaterialUnitDto dto);

    void update(MaterialUnitDto dto); 

    void delete(Long id);

    MaterialUnitVo info(Long id);

    Paging<MaterialUnitVo> getPage(MaterialUnitQuery query);

}

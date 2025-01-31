package io.geekidea.boot.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import io.geekidea.boot.material.entity.MaterialUnit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialUnitMapper extends BaseMapper<MaterialUnit> {
    MaterialUnitVo info(Long id);
}

package io.geekidea.boot.material.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaterialUnitMapper extends BaseMapper<MaterialUnit> {
    
    /**
     * 根据ID查询详情
     */
    MaterialUnitVo info(Long id);

    /**
     * 分页查询
     */
    List<MaterialUnitVo> getPage(MaterialUnitQuery query);

    /**
     * 批量插入
     */
    int batchInsert(@Param("list") List<MaterialUnit> list);

    /**
     * 统计指定编码数量（排除指定ID）
     */
    int countByCode(@Param("code") String code, @Param("excludeId") Long excludeId);
}

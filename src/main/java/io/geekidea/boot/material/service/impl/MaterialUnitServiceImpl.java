package io.geekidea.boot.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.geekidea.boot.material.mapper.MaterialUnitMapper;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.service.MaterialUnitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialUnitServiceImpl extends ServiceImpl<MaterialUnitMapper, MaterialUnit> implements MaterialUnitService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MaterialUnitDto dto) {
        // 实现添加逻辑
    }

    @Override 
    @Transactional(rollbackFor = Exception.class)
    public void update(MaterialUnitDto dto) {
        // 实现修改逻辑
    }

    @Override
    @Transactional(rollbackFor = Exception.class) 
    public void delete(Long id) {
        // 实现删除逻辑
    }

    @Override
    public MaterialUnitVo info(Long id) {
        // 实现详情查询逻辑
        return null;
    }

    @Override
    public PageResult<MaterialUnitVo> getPage(MaterialUnitQuery query) {
        // 实现分页查询逻辑
        return null;
    }
}

package io.geekidea.boot.material.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.db.PageResult;
import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.material.mapper.MaterialUnitMapper;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.service.MaterialUnitService;
import io.geekidea.boot.material.vo.MaterialUnitVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialUnitServiceImpl extends ServiceImpl<MaterialUnitMapper, MaterialUnit> implements MaterialUnitService {

    @Autowired
    private MaterialUnitMapper materialUnitMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(MaterialUnitDto dto) {
        MaterialUnit materialUnit = new MaterialUnit();
        BeanUtils.copyProperties(dto, materialUnit);
        materialUnit.setCreateTime(new Date());
        materialUnit.setUpdateTime(new Date());
        save(materialUnit);
    }

    @Override 
    @Transactional(rollbackFor = Exception.class)
    public void update(MaterialUnitDto dto) {
        MaterialUnit materialUnit = getById(dto.getId());
        if (materialUnit == null) {
            throw new BusinessException("物料单位不存在");
        }
        BeanUtils.copyProperties(dto, materialUnit);
        materialUnit.setUpdateTime(new Date());
        updateById(materialUnit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) 
    public void delete(Long id) {
        MaterialUnit materialUnit = getById(id);
        if (materialUnit == null) {
            throw new BusinessException("物料单位不存在");
        }
        removeById(id);
    }

    @Override
    public MaterialUnitVo info(Long id) {
        return materialUnitMapper.info(id);
    }

    @Override
    public PageResult<MaterialUnitVo> getPage(MaterialUnitQuery query) {
        Page<MaterialUnit> page = new Page<>(query.getPageIndex(), query.getPageSize());
        QueryWrapper<MaterialUnit> wrapper = new QueryWrapper<>();
        
        // 添加查询条件
        if (StringUtils.isNotBlank(query.getKeyword())) {
            wrapper.like("name", query.getKeyword())
                  .or()
                  .like("code", query.getKeyword());
        }
        wrapper.orderByDesc("create_time");
        
        Page<MaterialUnit> resultPage = page(page, wrapper);
        
        // Convert entities to VOs
        List<MaterialUnitVo> voList = resultPage.getRecords().stream()
            .map(unit -> {
                MaterialUnitVo vo = new MaterialUnitVo();
                BeanUtils.copyProperties(unit, vo);
                return vo;
            })
            .collect(Collectors.toList());
        
        PageResult<MaterialUnitVo> pageResult = new PageResult<>();
        pageResult.addAll(voList);
        pageResult.setPage((int)query.getPageIndex());
        pageResult.setPageSize((int)query.getPageSize());
        pageResult.setTotal((int)resultPage.getTotal());
        return pageResult;
    }
}

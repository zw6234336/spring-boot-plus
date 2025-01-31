package io.geekidea.boot.material.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;

import cn.hutool.db.PageResult;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.material.mapper.MaterialUnitMapper;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.service.MaterialUnitService;
import io.geekidea.boot.material.validator.MaterialUnitValidator;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import io.geekidea.boot.user.vo.UserRoleVo;
import io.geekidea.boot.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = "material_unit", cacheManager = "caffeineCacheManager")
public class MaterialUnitServiceImpl extends ServiceImpl<MaterialUnitMapper, MaterialUnit> implements MaterialUnitService {

    @Autowired
    private MaterialUnitMapper materialUnitMapper;

    @Autowired
    private MaterialUnitValidator validator;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void add(MaterialUnitDto dto) {
        // 验证单位编码
        validator.validateUnitCode(dto.getUnitCode());
        validator.validateUnitCodeUnique(dto.getUnitCode(), null);

        MaterialUnit materialUnit = new MaterialUnit();
        BeanUtils.copyProperties(dto, materialUnit);
        materialUnit.setCreatedAt(LocalDateTime.now());
        materialUnit.setUpdatedAt(LocalDateTime.now());
        save(materialUnit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void batchAdd(List<MaterialUnitDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return;
        }

        List<MaterialUnit> units = new ArrayList<>(dtoList.size());
        LocalDateTime now = LocalDateTime.now();

        for (MaterialUnitDto dto : dtoList) {
            validator.validateUnitCode(dto.getUnitCode());
            validator.validateUnitCodeUnique(dto.getUnitCode(), null);

            MaterialUnit unit = new MaterialUnit();
            BeanUtils.copyProperties(dto, unit);
            unit.setCreatedAt(now);
            unit.setUpdatedAt(now);
            units.add(unit);
        }

        materialUnitMapper.batchInsert(units);
    }

    @Override 
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void update(MaterialUnitDto dto) {
        MaterialUnit existingUnit = validator.validateExists(dto.getId());

        // 如果编码有变更，验证新编码
        if (!existingUnit.getUnitCode().equals(dto.getUnitCode())) {
            validator.validateUnitCode(dto.getUnitCode());
            validator.validateUnitCodeUnique(dto.getUnitCode(), dto.getId());
        }

        BeanUtils.copyProperties(dto, existingUnit);
        existingUnit.setUpdatedAt(LocalDateTime.now());
        updateById(existingUnit);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void delete(Long id) {
        validator.validateExists(id);
        removeById(id);
    }

    @Override
    @Cacheable(key = "#id", unless = "#result == null")
    public MaterialUnitVo info(Long id) {
        return materialUnitMapper.info(id);
    }

    @Override
    @Cacheable(key = "'page:' + #query.toString()", unless = "#result == null")
    public Paging<MaterialUnitVo> getPage(MaterialUnitQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        List<MaterialUnitVo> list = materialUnitMapper.getPage(query);
        Paging<MaterialUnitVo> paging = new Paging<>(list);
        return paging;
    }
}

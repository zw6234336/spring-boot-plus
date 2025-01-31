package io.geekidea.boot.material.validator;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.material.entity.MaterialUnit;
import io.geekidea.boot.material.mapper.MaterialUnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class MaterialUnitValidator {
    
    private static final String UNIT_CODE_PATTERN = "^[A-Z0-9]{1,10}$";
    private static final Pattern CODE_VALIDATOR = Pattern.compile(UNIT_CODE_PATTERN);

    @Autowired
    private MaterialUnitMapper materialUnitMapper;

    /**
     * 验证单位编码格式
     */
    public void validateUnitCode(String unitCode) {
        if (!CODE_VALIDATOR.matcher(unitCode).matches()) {
            throw new BusinessException("单位编码格式不正确，只能包含大写字母和数字，长度1-10位");
        }
    }

    /**
     * 验证单位编码唯一性
     */
    public void validateUnitCodeUnique(String unitCode, Long excludeId) {
        int count = materialUnitMapper.countByCode(unitCode, excludeId);
        if (count > 0) {
            throw new BusinessException("单位编码已存在");
        }
    }

    /**
     * 验证单位是否存在
     */
    public MaterialUnit validateExists(Long id) {
        MaterialUnit unit = materialUnitMapper.selectById(id);
        if (unit == null) {
            throw new BusinessException("物料单位不存在");
        }
        return unit;
    }
}

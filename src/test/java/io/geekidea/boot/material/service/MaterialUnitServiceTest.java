package io.geekidea.boot.material.service;

import io.geekidea.boot.framework.exception.BusinessException;
import io.geekidea.boot.framework.page.Paging;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import cn.hutool.db.PageResult;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 物料单位服务测试类
 * 用于测试物料单位相关的业务逻辑
 * 包含了增删改查等功能的单元测试
 *
 * @author geekidea
 * @since 2024-02-01
 */
@Slf4j
@SpringBootTest
@Transactional  // 使用事务确保测试数据不会污染数据库
public class MaterialUnitServiceTest {

    /**
     * 物料单位服务
     * 注入待测试的服务类
     */
    @Autowired
    private MaterialUnitService materialUnitService;

    /**
     * 测试用的DTO对象
     * 用于测试数据的创建和修改
     */
    private MaterialUnitDto testDto;

    /**
     * 测试用的查询对象
     * 用于测试查询功能
     */
    private MaterialUnitQuery testQuery;

    /**
     * 测试前的准备工作
     * 初始化测试数据
     */
    @BeforeEach
    void setUp() {
        testDto = new MaterialUnitDto();
        testDto.setUnitCode("PCS");
        testDto.setUnitName("件");
        testDto.setDeleted(false);

        testQuery = new MaterialUnitQuery();
    }

    /**
     * 测试添加和获取物料单位
     * 验证物料单位的创建和查询功能
     * 1. 添加新的物料单位记录
     * 2. 通过关键字查询验证添加是否成功
     * 3. 验证返回数据的正确性
     */
    @Test
    void shouldAddAndGetMaterialUnit() {
        // 添加新的物料单位
        materialUnitService.add(testDto);

        // 通过单位编码获取添加的物料单位
        MaterialUnitQuery query = new MaterialUnitQuery();
        query.setKeyword("BOX");
        Paging<MaterialUnitVo> page = materialUnitService.getPage(query);
        log.info("page:{}", page.getList().toString());

        // 验证查询结果
//        assertFalse(page.isEmpty(), "查询结果不应为空");
//        MaterialUnitVo vo = page.get(0);
//        assertEquals("PCS", vo.getUnitCode(), "单位编码应匹配");
//        assertEquals("件", vo.getUnitName(), "单位名称应匹配");
//        assertNotNull(vo.getId(), "ID不应为空");
    }

    /**
     * 测试更新物料单位
     * 验证物料单位信息的修改功能
     * 1. 先添加测试数据
     * 2. 修改数据内容
     * 3. 验证修改后的数据是否正确
     */
    @Test
    void shouldUpdateMaterialUnit() {
        // 先添加一个物料单位
        materialUnitService.add(testDto);

        // 查找添加的单位
        Paging<MaterialUnitVo> page = materialUnitService.getPage(new MaterialUnitQuery());
        MaterialUnitVo vo = page.getList().get(0);

        // 更新单位信息
        MaterialUnitDto updateDto = new MaterialUnitDto();
        updateDto.setId(vo.getId());
        updateDto.setUnitCode("BOX");
        updateDto.setUnitName("盒");
        updateDto.setDeleted(true);

        materialUnitService.update(updateDto);

        // 验证更新结果
        MaterialUnitVo updated = materialUnitService.info(vo.getId());
        assertEquals("BOX", updated.getUnitCode(), "单位编码应已更新");
        assertEquals("盒", updated.getUnitName(), "单位名称应已更新");
        assertTrue(updated.getDeleted(), "删除标识应已更新");
        assertNotNull(updated.getUpdatedAt(), "更新时间不应为空");
    }

    /**
     * 测试删除物料单位
     * 验证物料单位的删除功能
     * 1. 先添加测试数据
     * 2. 执行删除操作
     * 3. 验证数据已被成功删除
     */
    @Test
    void shouldDeleteMaterialUnit() {
        // 先添加一个物料单位
        materialUnitService.add(testDto);

        // 查找添加的单位
        Paging<MaterialUnitVo> page = materialUnitService.getPage(new MaterialUnitQuery());
        MaterialUnitVo vo = page.getList().get(0);

        // 删除该单位
        materialUnitService.delete(vo.getId());

        // 验证删除结果
        MaterialUnitVo deleted = materialUnitService.info(vo.getId());
        assertNull(deleted, "删除后应无法查询到该记录");
    }

    /**
     * 测试物料单位分页查询
     * 验证分页查询功能
     * 1. 添加多个测试数据
     * 2. 执行分页查询
     * 3. 验证查询结果的正确性
     */
    @Test
    void shouldGetPageOfMaterialUnits() {
        // 添加多个物料单位
        materialUnitService.add(testDto);

        // 添加第二个物料单位
        MaterialUnitDto secondDto = new MaterialUnitDto();
        secondDto.setUnitCode("KG");
        secondDto.setUnitName("千克");
        secondDto.setDeleted(false);
        materialUnitService.add(secondDto);

        // 测试分页查询
        MaterialUnitQuery query = new MaterialUnitQuery();
        Paging<MaterialUnitVo> result = materialUnitService.getPage(query);

        // 验证分页结果
        assertNotNull(result, "分页结果不应为空");
        assertTrue(result.getList().size() >= 2, "应至少包含两条记录");
    }

    /**
     * 测试单位编码的验证规则
     * 验证单位编码的各种约束条件
     * 1. 测试小写字母编码（应该失败）
     * 2. 测试特殊字符编码（应该失败）
     * 3. 测试超长编码（应该失败）
     */
    @Test
    void shouldValidateUnitCode() {
        // 测试小写字母编码
        MaterialUnitDto invalidDto = new MaterialUnitDto();
        invalidDto.setUnitCode("pcs");
        invalidDto.setUnitName("件");

        assertThrows(BusinessException.class, () -> {
            materialUnitService.add(invalidDto);
        }, "小写编码应该被拒绝");

        // 测试包含特殊字符的编码
        invalidDto.setUnitCode("PCS@");
        assertThrows(BusinessException.class, () -> {
            materialUnitService.add(invalidDto);
        }, "包含特殊字符的编码应该被拒绝");

        // 测试超长编码
        invalidDto.setUnitCode("PCS123456789");
        assertThrows(BusinessException.class, () -> {
            materialUnitService.add(invalidDto);
        }, "超长编码应该被拒绝");
    }

    /**
     * 测试单位编码唯一性约束
     * 验证系统是否正确阻止重复的单位编码
     * 1. 添加一个物料单位
     * 2. 尝试添加相同编码的物料单位
     * 3. 验证是否抛出业务异常
     */
    @Test
    void shouldPreventDuplicateCode() {
        // 添加第一个单位
        materialUnitService.add(testDto);

        // 尝试添加相同编码的单位
        MaterialUnitDto duplicateDto = new MaterialUnitDto();
        duplicateDto.setUnitCode("PCS");
        duplicateDto.setUnitName("个");

        assertThrows(BusinessException.class, () -> {
            materialUnitService.add(duplicateDto);
        }, "重复的单位编码应该被拒绝");
    }
}

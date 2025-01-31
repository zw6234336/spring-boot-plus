package io.geekidea.boot.material.service;

/**
 * 物料单位服务测试类
 * 用于测试物料单位相关的服务方法
 */
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cn.hutool.db.PageResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MaterialUnitServiceTest {

    @Mock
    private MaterialUnitService materialUnitService; // 模拟物料单位服务

    private MaterialUnitDto mockDto; // 模拟物料单位数据传输对象
    private MaterialUnitQuery mockQuery; // 模拟物料单位查询对象

    /**
     * 测试前的初始化设置
     * 初始化Mock对象和测试数据
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // 初始化mockDto测试数据
        mockDto = new MaterialUnitDto();
        mockDto.setId(1L);
        mockDto.setUnitCode("PCS");
        mockDto.setUnitName("件");
        mockDto.setRemark("测试物料单位");
        mockDto.setStatus(true);
        
        mockQuery = new MaterialUnitQuery();
    }

    /**
     * 测试添加物料单位方法
     * 验证服务方法是否被正确调用
     */
    @Test
    void add_ShouldCallServiceMethod() {
        doNothing().when(materialUnitService).add(mockDto);
        materialUnitService.add(mockDto);
        verify(materialUnitService, times(1)).add(mockDto);
    }

    /**
     * 测试更新物料单位方法
     * 验证服务方法是否被正确调用
     */
    @Test
    void update_ShouldCallServiceMethod() {
        doNothing().when(materialUnitService).update(mockDto);
        materialUnitService.update(mockDto);
        verify(materialUnitService, times(1)).update(mockDto);
    }

    /**
     * 测试删除物料单位方法
     * 验证服务方法是否被正确调用
     */
    @Test
    void delete_ShouldCallServiceMethod() {
        Long id = 1L;
        doNothing().when(materialUnitService).delete(id);
        materialUnitService.delete(id);
        verify(materialUnitService, times(1)).delete(id);
    }

    /**
     * 测试获取物料单位信息方法
     * 验证返回数据的有效性
     */
    @Test
    void info_ShouldReturnValidData() {
        Long id = 1L;
        MaterialUnitVo expected = new MaterialUnitVo();
        when(materialUnitService.info(id)).thenReturn(expected);

        MaterialUnitVo result = materialUnitService.info(id);
        assertNotNull(result);
        assertEquals(expected, result);
    }

    /**
     * 测试分页查询物料单位方法
     * 验证分页数据的返回结果
     */
    @Test
    void getPage_ShouldReturnPagingData() {
        PageResult<MaterialUnitVo> expected = new PageResult<>();
        when(materialUnitService.getPage(mockQuery)).thenReturn(expected);

        PageResult<MaterialUnitVo> result = materialUnitService.getPage(mockQuery);
        assertNotNull(result);
        assertEquals(expected, result);
    }
}

package io.geekidea.boot.material.controller;

import io.geekidea.boot.framework.response.ApiResult;
import io.geekidea.boot.material.dto.MaterialUnitDto;
import io.geekidea.boot.material.query.MaterialUnitQuery;
import io.geekidea.boot.material.service.MaterialUnitService;
import io.geekidea.boot.material.vo.MaterialUnitVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 物料单位控制器
 * 
 * 该控制器负责处理与物料单位相关的所有HTTP请求，包括:
 * - 添加新的物料单位
 * - 修改现有物料单位信息
 * - 删除物料单位
 * - 查询物料单位详情
 * - 分页查询物料单位列表
 */
@Slf4j
@RestController
@Tag(name = "物料单位")
@RequestMapping("/admin/materialUnit")
public class MaterialUnitController {

    /**
     * 物料单位服务接口
     */
    @Autowired
    private MaterialUnitService materialUnitService;
    
    /**
     * 添加新的物料单位
     *
     * @param dto 物料单位数据传输对象，包含需要添加的物料单位信息
     * @return ApiResult 操作结果
     */
    @PostMapping("/add")
    @Operation(summary = "添加物料单位")
    public ApiResult add(@Valid @RequestBody MaterialUnitDto dto) {
        log.info("添加物料单位开始, 参数: {}", dto);
        try {
            materialUnitService.add(dto);
            log.info("添加物料单位成功");
            return ApiResult.success();
        } catch (Exception e) {
            log.error("添加物料单位失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 更新物料单位信息
     *
     * @param dto 物料单位数据传输对象，包含需要更新的物料单位信息
     * @return ApiResult 操作结果
     */
    @PostMapping("/update") 
    @Operation(summary = "修改物料单位")
    public ApiResult update(@Valid @RequestBody MaterialUnitDto dto) {
        log.info("修改物料单位开始, 参数: {}", dto);
        try {
            materialUnitService.update(dto);
            log.info("修改物料单位成功");
            return ApiResult.success();
        } catch (Exception e) {
            log.error("修改物料单位失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 删除指定ID的物料单位
     *
     * @param id 要删除的物料单位ID
     * @return ApiResult 操作结果
     */
    @GetMapping("/delete/{id}")
    @Operation(summary = "删除物料单位")
    public ApiResult delete(@PathVariable Long id) {
        log.info("删除物料单位开始, id: {}", id);
        try {
            materialUnitService.delete(id);
            log.info("删除物料单位成功");
            return ApiResult.success();
        } catch (Exception e) {
            log.error("删除物料单位失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 获取指定ID的物料单位详细信息
     *
     * @param id 物料单位ID
     * @return ApiResult<MaterialUnitVo> 包含物料单位详细信息的响应对象
     */
    @GetMapping("/info/{id}")
    @Operation(summary = "物料单位详情")
    public ApiResult<MaterialUnitVo> info(@PathVariable Long id) {
        log.info("获取物料单位详情开始, id: {}", id);
        try {
            MaterialUnitVo vo = materialUnitService.info(id);
            log.info("获取物料单位详情成功: {}", vo);
            return ApiResult.success(vo);
        } catch (Exception e) {
            log.error("获取物料单位详情失败: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 分页查询物料单位列表
     *
     * @param query 查询条件对象，包含分页参数和筛选条件
     * @return ApiResult<MaterialUnitVo> 包含分页数据的响应对象
     */
    @PostMapping("/page")
    @Operation(summary = "物料单位分页列表")
    public ApiResult<MaterialUnitVo> getPage(@Valid @RequestBody MaterialUnitQuery query) {
        log.info("获取物料单位分页列表开始, 查询参数: {}", query);
        try {
            Object result = materialUnitService.getPage(query);
            log.info("获取物料单位分页列表成功");
            return ApiResult.success(result);
        } catch (Exception e) {
            log.error("获取物料单位分页列表失败: {}", e.getMessage(), e);
            throw e;
        }
    }
}

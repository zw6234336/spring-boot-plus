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

@Slf4j
@RestController
@Tag(name = "物料单位")
@RequestMapping("/admin/materialUnit")
public class MaterialUnitController {

    @Autowired
    private MaterialUnitService materialUnitService;
    
    @PostMapping("/add")
    @Operation(summary = "添加物料单位")
    public ApiResult add(@Valid @RequestBody MaterialUnitDto dto) {
        materialUnitService.add(dto);
        return ApiResult.success();
    }

    @PostMapping("/update") 
    @Operation(summary = "修改物料单位")
    public ApiResult update(@Valid @RequestBody MaterialUnitDto dto) {
        materialUnitService.update(dto);
        return ApiResult.success();
    }

    @GetMapping("/delete/{id}")
    @Operation(summary = "删除物料单位")
    public ApiResult delete(@PathVariable Long id) {
        materialUnitService.delete(id);
        return ApiResult.success();
    }

    @GetMapping("/info/{id}")
    @Operation(summary = "物料单位详情")
    public ApiResult<MaterialUnitVo> info(@PathVariable Long id) {
        return ApiResult.success(materialUnitService.info(id));
    }

    @PostMapping("/page")
    @Operation(summary = "物料单位分页列表")
    public ApiResult<MaterialUnitVo> getPage(@Valid @RequestBody MaterialUnitQuery query) {
        return ApiResult.success(materialUnitService.getPage(query));
    }
}

package com.ruoyi.active.controller;

import com.ruoyi.active.domain.ActiveEvaluation;
import com.ruoyi.active.service.IActiveEvaluationService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作品评价Controller
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
@Api(tags = "04-作品评分", value = "活动模块中的作品评分相关API")
@RestController
@RequestMapping("/active/evaluation")
public class ActiveEvaluationController extends BaseController
{
    @Autowired
    private IActiveEvaluationService activeEvaluationService;

    /**
     * 查询作品评价列表
     */
    @ApiOperation(value = "分页查询", notes = "分页查询作品评分信息")
    @PreAuthorize("@ss.hasPermi('active:evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveEvaluation activeEvaluation)
    {
        startPage();
        List<ActiveEvaluation> list = activeEvaluationService.selectActiveEvaluationList(activeEvaluation);
        return getDataTable(list);
    }


    /**
     * 导出作品评价列表
     */
    @ApiOperation(value = "导出评分", notes = "导出作品评分列表信息")
    @PreAuthorize("@ss.hasPermi('active:evaluation:export')")
    @Log(title = "作品评分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveEvaluation activeEvaluation)
    {
        List<ActiveEvaluation> list = activeEvaluationService.selectActiveEvaluationList(activeEvaluation);
        ExcelUtil<ActiveEvaluation> util = new ExcelUtil<ActiveEvaluation>(ActiveEvaluation.class);
        util.exportExcel(response, list, "作品评价数据");
    }

    /**
     * 获取作品评价详细信息
     */
    @ApiOperation(value = "查询评分", notes = "通过id查询作品评分信息")
    @ApiImplicitParam(name = "evaId", value = "评分ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:evaluation:query')")
    @GetMapping(value = "/{evaId}")
    public AjaxResult getInfo(@PathVariable("evaId") Long evaId)
    {
        return success(activeEvaluationService.selectActiveEvaluationByEvaId(evaId));
    }

    /**
     * 新增作品评价
     */
    @ApiOperation(value = "添加评分", notes = "添加作品评分信息")
    @PreAuthorize("@ss.hasPermi('active:evaluation:add')")
    @Log(title = "作品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiveEvaluation activeEvaluation)
    {
        return toAjax(activeEvaluationService.insertActiveEvaluation(activeEvaluation));
    }

    /**
     * 修改作品评价
     */
    @ApiOperation(value = "修改评分", notes = "修改作品评分信息")
    @PreAuthorize("@ss.hasPermi('active:evaluation:edit')")
    @Log(title = "作品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveEvaluation activeEvaluation)
    {
        return toAjax(activeEvaluationService.updateActiveEvaluation(activeEvaluation));
    }

    /**
     * 删除作品评价
     */
    @ApiOperation(value = "删除评分", notes = "删除作品评分信息")
    @ApiImplicitParam(name = "evaIds", value = "评分ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:evaluation:remove')")
    @Log(title = "作品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{evaIds}")
    public AjaxResult remove(@PathVariable Long[] evaIds)
    {
        return toAjax(activeEvaluationService.deleteActiveEvaluationByEvaIds(evaIds));
    }


}

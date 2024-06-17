package com.ruoyi.active.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.active.domain.ActiveWorks;
import com.ruoyi.active.service.IActiveWorksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作品Controller
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
@Api(tags = "作品信息")
@RestController
@RequestMapping("/active/works")
public class ActiveWorksController extends BaseController
{
    @Autowired
    private IActiveWorksService activeWorksService;

    /**
     * 查询作品列表
     */
    @ApiOperation(value = "作品列表")
    @PreAuthorize("@ss.hasPermi('active:works:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveWorks activeWorks)
    {
        startPage();
        List<ActiveWorks> list = activeWorksService.selectActiveWorksList(activeWorks);
        return getDataTable(list);
    }

    /**
     * 导出作品列表
     */
    @ApiOperation(value = "导出作品列表")
    @PreAuthorize("@ss.hasPermi('active:works:export')")
    @Log(title = "作品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveWorks activeWorks)
    {
        List<ActiveWorks> list = activeWorksService.selectActiveWorksList(activeWorks);
        ExcelUtil<ActiveWorks> util = new ExcelUtil<ActiveWorks>(ActiveWorks.class);
        util.exportExcel(response, list, "作品数据");
    }

    /**
     * 获取作品信息
     */
    @ApiOperation(value = "获取作品信息")
    @PreAuthorize("@ss.hasPermi('active:works:query')")
    @GetMapping(value = "/{workId}")
    public AjaxResult getInfo(@PathVariable("workId") Long workId)
    {
        return success(activeWorksService.selectActiveWorksByWorkId(workId));
    }

    /**
     * 新增作品
     */
    @ApiOperation(value = "新增作品")
    @PreAuthorize("@ss.hasPermi('active:works:add')")
    @Log(title = "作品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiveWorks activeWorks)
    {
        return toAjax(activeWorksService.insertActiveWorks(activeWorks));
    }

    /**
     * 修改作品
     */
    @ApiOperation(value = "修改作品")
    @PreAuthorize("@ss.hasPermi('active:works:edit')")
    @Log(title = "作品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveWorks activeWorks)
    {
        return toAjax(activeWorksService.updateActiveWorks(activeWorks));
    }

    /**
     * 删除作品
     */
    @ApiOperation(value = "删除作品")
    @PreAuthorize("@ss.hasPermi('active:works:remove')")
    @Log(title = "作品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{workIds}")
    public AjaxResult remove(@PathVariable Long[] workIds)
    {
        return toAjax(activeWorksService.deleteActiveWorksByWorkIds(workIds));
    }
}

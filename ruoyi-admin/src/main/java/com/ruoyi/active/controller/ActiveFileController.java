package com.ruoyi.active.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

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
import com.ruoyi.active.domain.ActiveFile;
import com.ruoyi.active.service.IActiveFileService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作品相关文件Controller
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
@RestController
@RequestMapping("/active/file")
public class ActiveFileController extends BaseController
{
    @Autowired
    private IActiveFileService activeFileService;

    /**
     * 查询作品相关文件列表
     */
    @PreAuthorize("@ss.hasPermi('active:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveFile activeFile)
    {
        startPage();
        List<ActiveFile> list = activeFileService.selectActiveFileList(activeFile);
        return getDataTable(list);
    }

    /**
     * 导出作品相关文件列表
     */
    @PreAuthorize("@ss.hasPermi('active:file:export')")
    @Log(title = "作品相关文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveFile activeFile)
    {
        List<ActiveFile> list = activeFileService.selectActiveFileList(activeFile);
        ExcelUtil<ActiveFile> util = new ExcelUtil<ActiveFile>(ActiveFile.class);
        util.exportExcel(response, list, "作品相关文件数据");
    }

    /**
     * 获取作品相关文件详细信息
     */
    @PreAuthorize("@ss.hasPermi('active:file:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(activeFileService.selectActiveFileByFileId(fileId));
    }

    /**
     * 新增作品相关文件
     */
    @PreAuthorize("@ss.hasPermi('active:file:add')")
    @Log(title = "作品相关文件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiveFile activeFile)
    {
        return toAjax(activeFileService.insertActiveFile(activeFile));
    }

    /**
     * 修改作品相关文件
     */
    @PreAuthorize("@ss.hasPermi('active:file:edit')")
    @Log(title = "作品相关文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveFile activeFile)
    {
        return toAjax(activeFileService.updateActiveFile(activeFile));
    }

    /**
     * 删除作品相关文件
     */
    @PreAuthorize("@ss.hasPermi('active:file:remove')")
    @Log(title = "作品相关文件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(activeFileService.deleteActiveFileByFileIds(fileIds));
    }
}

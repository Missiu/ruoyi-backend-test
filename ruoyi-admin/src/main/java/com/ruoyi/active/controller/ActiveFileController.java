package com.ruoyi.active.controller;

import com.ruoyi.active.domain.ActiveFile;
import com.ruoyi.active.service.IActiveFileService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private IActiveFileService activeFileService;
    /**
     * 实现文件下载功能
     */
    @PreAuthorize("@ss.hasPermi('active:file:download')")
    @GetMapping("/download/{fileId}")
    public AjaxResult download(@PathVariable Long fileId, HttpServletResponse response) {
        try
        {
            ActiveFile activeFile = activeFileService.selectActiveFileByFileId(fileId);
            // 数据库资源地址
            String downloadPath = activeFile.getFileStoragePath();
            // 下载名称
            String downloadName = activeFile.getFileName();

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
        return success();
    }
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

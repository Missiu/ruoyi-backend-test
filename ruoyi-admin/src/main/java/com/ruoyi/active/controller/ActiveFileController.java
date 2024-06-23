package com.ruoyi.active.controller;

import com.ruoyi.active.domain.entity.ActiveFile;
import com.ruoyi.active.domain.entity.ActiveWorks;
import com.ruoyi.active.service.IActiveFileService;
import com.ruoyi.active.service.IActiveWorksService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.web.controller.common.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 作品相关文件Controller
 *
 * @author huzhihao
 * @date 2024-06-16
 */
@Api(tags = "03-作品附件", value = "活动模块中的作品相关文件API")
@RestController
@RequestMapping("/active/file")
public class ActiveFileController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IActiveFileService activeFileService;

    @Autowired
    private IActiveWorksService activeWorksService;

    /**
     * 实现文件下载功能
     */
    @ApiOperation(value = "附件下载", notes = "通过id下载作品附件")
    @ApiImplicitParam(name = "fileId", value = "文件ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:file:download')")
    @GetMapping("/download/{fileId}")
    public void download(@PathVariable("fileId") Long fileId, HttpServletResponse response) {
        try {
            ActiveFile activeFile = activeFileService.selectActiveFileByFileId(fileId);
            String fileName = activeFile.getFileName();
            String filePath = activeFile.getFileStoragePath();

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 查询作品相关文件列表
     */
    @ApiOperation(value = "分页查询", notes = "分页查询作品相关文件信息,查询结果也包含作品信息")
    @PreAuthorize("@ss.hasPermi('active:file:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveFile activeFile) {
        startPage();
        List<ActiveFile> list = activeFileService.selectActiveFileList(activeFile);
        return getDataTable(list);
    }

    /**
     * 导出作品相关文件列表
     */
    @ApiOperation(value = "导出附件", notes = "导出作品相关文件列表信息")
    @PreAuthorize("@ss.hasPermi('active:file:export')")
    @Log(title = "作品相关文件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveFile activeFile) {
        List<ActiveFile> list = activeFileService.selectActiveFileList(activeFile);
        ExcelUtil<ActiveFile> util = new ExcelUtil<ActiveFile>(ActiveFile.class);
        util.exportExcel(response, list, "作品相关文件数据");
    }

    /**
     * 获取作品相关文件详细信息
     */
    @ApiOperation(value = "查询附件", notes = "通过id查询作品相关文件信息")
    @ApiImplicitParam(name = "fileId", value = "文件ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:file:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId) {
        return success(activeFileService.selectActiveFileByFileId(fileId));
    }

    /**
     * 新增作品相关文件
     */
    @ApiOperation(value = "上传附件", notes = "上传作品相关文件信息")
    @PreAuthorize("@ss.hasPermi('active:file:add')")
    @Log(title = "作品相关文件", businessType = BusinessType.INSERT)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", required = true,
                    dataType = "MultipartFile", dataTypeClass = MultipartFile.class)
    })
    @PostMapping("/add")
    public AjaxResult add(@RequestPart MultipartFile file, ActiveFile activeFile) {
        try {
            ActiveWorks activeWork = activeWorksService.selectActiveWorksByWorkId(activeFile.getWorkId());
            // 上传文件路径
            String filePath = RuoYiConfig.getProfile() + "/active"
                    + "/name_" + activeWork.getWorkName() + "id_" + activeFile.getWorkId()
                    + "/category_" + activeFile.getFileCategory();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            fileName = fileName.replace("/profile", "");
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            activeFile.setFileName(FileUtils.getName(fileName));
            activeFile.setFileStoragePath(RuoYiConfig.getProfile() + fileName);
            activeFileService.insertActiveFile(activeFile);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改作品相关文件
     */
    @ApiOperation(value = "修改附件", notes = "修改作品相关文件信息")
    @PreAuthorize("@ss.hasPermi('active:file:edit')")
    @Log(title = "作品相关文件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveFile activeFile) {
        return toAjax(activeFileService.updateActiveFile(activeFile));
    }

    /**
     * 删除作品相关文件
     */
    @ApiOperation(value = "删除附件", notes = "删除作品相关文件信息")
    @ApiImplicitParam(name = "fileIds", value = "文件ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:file:remove')")
    @Log(title = "作品相关文件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds) {
        return toAjax(activeFileService.deleteActiveFileByFileIds(fileIds));
    }
}

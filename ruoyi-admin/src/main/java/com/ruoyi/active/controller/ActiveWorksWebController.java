package com.ruoyi.active.controller;

import com.ruoyi.active.domain.entity.ActiveEvaluation;
import com.ruoyi.active.domain.entity.ActiveFile;
import com.ruoyi.active.domain.entity.ActiveWorks;
import com.ruoyi.active.service.IActiveEvaluationService;
import com.ruoyi.active.service.IActiveFileService;
import com.ruoyi.active.service.IActiveWorksService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.file.FileSizeLimitExceededException;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.config.ServerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "06-作品管理-客户端", value = "活动模块中的的客户端作品管理API")
@RestController
@RequestMapping("/active/work/web")
public class ActiveWorksWebController extends BaseController {

    @Autowired
    private IActiveWorksService activeWorksService;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private IActiveFileService activeFileService;

    @Autowired
    private IActiveEvaluationService activeEvaluationService;
    /**
     * 新增作品
     */
    @ApiOperation(value = "添加作品", notes = "添加作品信息")
    @PostMapping("/addWorks")
    public AjaxResult addWorks(@RequestBody ActiveWorks activeWorks)
    {
        return toAjax(activeWorksService.insertActiveWorks(activeWorks));
    }

    /**
     * 修改作品
     */
    @ApiOperation(value = "修改作品", notes = "修改作品信息")
    @PutMapping("/editWorks")
    public AjaxResult editWorks(@RequestBody ActiveWorks activeWorks)
    {
        return toAjax(activeWorksService.updateActiveWorks(activeWorks));
    }

    /**
     * 新增作品相关文件
     */
    @ApiOperation(value = "上传附件", notes = "上传作品相关文件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", required = true,
                    dataType = "MultipartFile", dataTypeClass = MultipartFile.class)
    })
    @PostMapping("/file/add")
    public AjaxResult addFile(@RequestPart MultipartFile file, ActiveFile activeFile) {
        try {
            String[] allowedExtension = {"mp4", "pdf"};
            // 获取文件后缀
            String extension = FileUploadUtils.getExtension(file);
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            // 文件大小校验
            if (extension.equals("mp4")){
                // 500m
                if (size > 500 * 1024 * 1024)
                {
                    throw new FileSizeLimitExceededException(500);
                }
            }else if (extension.equals("pdf")){
                // 500m
                if (size > 100 * 1024 * 1024)
                {
                    throw new FileSizeLimitExceededException(500);
                }
            }else {
                throw new InvalidExtensionException.InvalidFlashExtensionException(allowedExtension, extension,
                        originalFilename);
            }
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
     * 查询作品评价列表
     */
    @ApiOperation(value = "分页查询", notes = "分页查询作品评分信息")
    @GetMapping("/list")
    public TableDataInfo list(ActiveEvaluation activeEvaluation)
    {
        startPage();
        List<ActiveEvaluation> list = activeEvaluationService.selectActiveEvaluationList(activeEvaluation);
        return getDataTable(list);
    }


    /**
     * 修改作品评价
     */
    @ApiOperation(value = "修改评分", notes = "修改作品评分信息")
    @PutMapping("/editEvaluation")
    public AjaxResult editEvaluation(@RequestBody ActiveEvaluation activeEvaluation)
    {
        return toAjax(activeEvaluationService.updateActiveEvaluation(activeEvaluation));
    }
}

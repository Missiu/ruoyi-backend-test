package com.ruoyi.active.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.ActiveUser;
import com.ruoyi.active.service.IActiveUserService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginActiveUserBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.ruoyi.common.utils.ActiveSecurityUtils.getLoginActiveUser;

/**
 * 账号Controller
 *
 * @author huzhihao
 * @date 2024-06-15
 */
@Api(tags = "01-账号管理", value = "活动模块中的的账号管理API")
@RestController
@RequestMapping("/active/user")
public class ActiveUserController extends BaseController {
    @Autowired
    private IActiveUserService activeUserService;

    /**
     * 新增账号
     */
    @ApiOperation(value = "新增账号", notes = "管理员新增账号信息")
    @PreAuthorize("@ss.hasPermi('active:user:add')")
    @Log(title = "账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiveUser activeUser) {
        return toAjax(activeUserService.insertActiveUser(activeUser));
    }

    /**
     * 删除账号
     */
    @ApiOperation(value = "删除账号", notes = "通过id删除账号信息")
    @ApiImplicitParam(name = "userIds", value = "账号ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:user:remove')")
    @Log(title = "账号", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        return toAjax(activeUserService.deleteActiveUserByUserIds(userIds));
    }

    /**
     * 修改账号
     */
    @ApiOperation(value = "修改账号", notes = "管理员修改账号信息")
    @PreAuthorize("@ss.hasPermi('active:user:edit')")
    @Log(title = "账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveUser activeUser) {
        return toAjax(activeUserService.updateActiveUser(activeUser));
    }

    /**
     * 获取账号详细信息
     */
    @ApiOperation(value = "查询账号", notes = "通过id查询账号信息")
    @ApiImplicitParam(name = "userId", value = "账号ID", required = true, type = "Long")
    @PreAuthorize("@ss.hasPermi('active:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        return success(activeUserService.selectActiveUserByUserId(userId));
    }

    /**
     * 查询账号列表
     */
    @ApiOperation(value = "分页查询", notes = "分页查询账号信息")
    @PreAuthorize("@ss.hasPermi('active:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveUser activeUser) {
        startPage();
        List<ActiveUser> list = activeUserService.selectActiveUserList(activeUser);
        return getDataTable(list);
    }

    /**
     * 导入账号列表
     */
    @ApiOperation(value = "导入账号", notes = "导入账号列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "需要导入的用户信息列表文件", required = true, dataType = "MultipartFile", paramType = "form", dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "updateSupport", value = "是否更新已经存在的账号数据", required = true, dataType = "boolean", paramType = "form")
    })
    @Log(title = "活动账号列表管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('active:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<ActiveUser> util = new ExcelUtil<>(ActiveUser.class);
        List<ActiveUser> accountList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = activeUserService.importUser(accountList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @ApiOperation(value = "下载模板", notes = "下载账号列表模板")
    @GetMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<ActiveUser> util = new ExcelUtil<>(ActiveUser.class);
        util.importTemplateExcel(response, "账号数据");
    }

    /**
     * 导出账号列表
     */
    @ApiOperation(value = "导出账号", notes = "导出账号列表信息")
    @PreAuthorize("@ss.hasPermi('active:user:export')")
    @ApiImplicitParam(name = "activeUser", value = "需要导出的账号信息", required = true, dataTypeClass = ActiveUser.class)
    @Log(title = "账号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveUser activeUser) {
        List<ActiveUser> list = activeUserService.selectActiveUserList(activeUser);
        ExcelUtil<ActiveUser> util = new ExcelUtil<ActiveUser>(ActiveUser.class);
        util.exportExcel(response, list, "账号数据");
    }


}

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
import com.ruoyi.active.domain.ActiveUser;
import com.ruoyi.active.service.IActiveUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 账号Controller
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
@RestController
@RequestMapping("/active/user")
public class ActiveUserController extends BaseController
{
    @Autowired
    private IActiveUserService activeUserService;

    /**
     * 查询账号列表
     */
    @PreAuthorize("@ss.hasPermi('active:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(ActiveUser activeUser)
    {
        startPage();
        List<ActiveUser> list = activeUserService.selectActiveUserList(activeUser);
        return getDataTable(list);
    }

    /**
     * 导入账号列表
     */
    @Log(title = "活动账号列表管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('active:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ActiveUser> util = new ExcelUtil<>(ActiveUser.class);
        List<ActiveUser> accountList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = activeUserService.importUser(accountList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<ActiveUser> util = new ExcelUtil<>(ActiveUser.class);
        util.importTemplateExcel(response, "账号数据");
    }
    /**
     * 导出账号列表
     */
    @Log(title = "账号", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ActiveUser activeUser)
    {
        List<ActiveUser> list = activeUserService.selectActiveUserList(activeUser);
        ExcelUtil<ActiveUser> util = new ExcelUtil<ActiveUser>(ActiveUser.class);
        util.exportExcel(response, list, "账号数据");
    }

    /**
     * 获取账号详细信息
     */
    @PreAuthorize("@ss.hasPermi('active:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(activeUserService.selectActiveUserByUserId(userId));
    }

    /**
     * 新增账号
     */
    @PreAuthorize("@ss.hasPermi('active:user:add')")
    @Log(title = "账号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ActiveUser activeUser)
    {
        return toAjax(activeUserService.insertActiveUser(activeUser));
    }

    /**
     * 修改账号
     */
    @PreAuthorize("@ss.hasPermi('active:user:edit')")
    @Log(title = "账号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ActiveUser activeUser)
    {
        return toAjax(activeUserService.updateActiveUser(activeUser));
    }

    /**
     * 删除账号
     */
    @PreAuthorize("@ss.hasPermi('active:user:remove')")
    @Log(title = "账号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(activeUserService.deleteActiveUserByUserIds(userIds));
    }
}

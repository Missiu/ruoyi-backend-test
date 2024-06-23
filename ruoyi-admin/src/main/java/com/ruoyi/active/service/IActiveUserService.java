package com.ruoyi.active.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.ActiveUser;

/**
 * 账号Service接口
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
public interface IActiveUserService 
{
    /**
     * 查询账号
     * 
     * @param userId 账号主键
     * @return 账号
     */
    public ActiveUser selectActiveUserByUserId(Long userId);

    /**
     * 查询账号列表
     * 
     * @param activeUser 账号
     * @return 账号集合
     */
    public List<ActiveUser> selectActiveUserList(ActiveUser activeUser);

    /**
     * 新增账号
     * 
     * @param activeUser 账号
     * @return 结果
     */
    public int insertActiveUser(ActiveUser activeUser);

    /**
     * 修改账号
     * 
     * @param activeUser 账号
     * @return 结果
     */
    public int updateActiveUser(ActiveUser activeUser);

    /**
     * 批量删除账号
     * 
     * @param userIds 需要删除的账号主键集合
     * @return 结果
     */
    public int deleteActiveUserByUserIds(Long[] userIds);

    /**
     * 删除账号信息
     * 
     * @param userId 账号主键
     * @return 结果
     */
    public int deleteActiveUserByUserId(Long userId);

    /**
     * 导入账号列表
     *
     * @param accountList 账号数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    String importUser(List<ActiveUser> accountList, boolean isUpdateSupport, String operName);

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    public String login(String username, String password);
}

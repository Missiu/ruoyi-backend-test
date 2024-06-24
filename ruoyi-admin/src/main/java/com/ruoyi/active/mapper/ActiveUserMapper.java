package com.ruoyi.active.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.ActiveUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 账号Mapper接口
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
@Mapper
public interface ActiveUserMapper 
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
     * 修改密码
     * @param activeUser 账号
     * @return 结果
     */
    public int updateActiveUserPassword(ActiveUser activeUser);

    /**
     * 删除账号
     * 
     * @param userId 账号主键
     * @return 结果
     */
    public int deleteActiveUserByUserId(Long userId);

    /**
     * 批量删除账号
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActiveUserByUserIds(Long[] userIds);

    /**
     * 根据用户名称查询账号
     *
     */
    public ActiveUser selectActiveUserByUsername(String username);
}

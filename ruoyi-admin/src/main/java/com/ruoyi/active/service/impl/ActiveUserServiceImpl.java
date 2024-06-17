package com.ruoyi.active.service.impl;

import com.ruoyi.active.domain.ActiveUser;
import com.ruoyi.active.mapper.ActiveUserMapper;
import com.ruoyi.active.service.IActiveUserService;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

/**
 * 账号Service业务层处理
 *
 * @author huzhihao
 * @date 2024-06-15
 */
@Service
public class ActiveUserServiceImpl implements IActiveUserService {
    @Autowired
    private ActiveUserMapper activeUserMapper;

    @Autowired
    private Validator validator;

    private static final Logger log = LoggerFactory.getLogger(ActiveUserServiceImpl.class);

    /**
     * 查询账号
     *
     * @param userId 账号主键
     * @return 账号
     */
    @Override
    public ActiveUser selectActiveUserByUserId(Long userId) {
        return activeUserMapper.selectActiveUserByUserId(userId);
    }

    /**
     * 查询账号列表
     *
     * @param activeUser 账号
     * @return 账号
     */
    @Override
    public List<ActiveUser> selectActiveUserList(ActiveUser activeUser) {
        return activeUserMapper.selectActiveUserList(activeUser);
    }

    /**
     * 新增账号
     *
     * @param activeUser 账号
     * @return 结果
     */
    @Override
    public int insertActiveUser(ActiveUser activeUser) {
        activeUser.setCreateTime(DateUtils.getNowDate());
        if (StringUtils.isEmpty(activeUser.getPassword())){
            activeUser.setPassword("123456");
        }
        return activeUserMapper.insertActiveUser(activeUser);
    }

    /**
     * 修改账号
     *
     * @param activeUser 账号
     * @return 结果
     */
    @Override
    public int updateActiveUser(ActiveUser activeUser) {
        activeUser.setUpdateTime(DateUtils.getNowDate());
        return activeUserMapper.updateActiveUser(activeUser);
    }

    /**
     * 批量删除账号
     *
     * @param userIds 需要删除的账号主键
     * @return 结果
     */
    @Override
    public int deleteActiveUserByUserIds(Long[] userIds) {
        return activeUserMapper.deleteActiveUserByUserIds(userIds);
    }

    /**
     * 删除账号信息
     *
     * @param userId 账号主键
     * @return 结果
     */
    @Override
    public int deleteActiveUserByUserId(Long userId) {
        return activeUserMapper.deleteActiveUserByUserId(userId);
    }

    /**
     * 导入账号
     *
     * @param accountList   账号数据列表
     * @param updateSupport 是否更新已经存在的账号数据
     * @param operName      操作人员
     * @return 结果
     */
    @Override
    public String importUser(List<ActiveUser> accountList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(accountList) || accountList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (ActiveUser user : accountList) {
            try {
                // 验证是否存在这个用户
                ActiveUser u = activeUserMapper.selectActiveUserByUserId(user.getUserId());
                if (StringUtils.isNull(u)) {
                    BeanValidators.validateWithException(validator, user);
                    user.setCreateBy(operName);
                    this.insertActiveUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号编号 " + user.getUserId() + " 导入成功");
                } else if (updateSupport) {
                    BeanValidators.validateWithException(validator, user);
                    user.setUpdateBy(operName);
                    this.insertActiveUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号编号 " + user.getUserId() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + successNum + "、账号编号 " + user.getUserId() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + successNum + "、账号编号 " + user.getUserId() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}

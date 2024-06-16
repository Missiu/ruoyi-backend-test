package com.ruoyi.active.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.active.mapper.ActiveWorksMapper;
import com.ruoyi.active.domain.ActiveWorks;
import com.ruoyi.active.service.IActiveWorksService;

/**
 * 作品Service业务层处理
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
@Service
public class ActiveWorksServiceImpl implements IActiveWorksService 
{
    @Autowired
    private ActiveWorksMapper activeWorksMapper;

    /**
     * 查询作品
     * 
     * @param workId 作品主键
     * @return 作品
     */
    @Override
    public ActiveWorks selectActiveWorksByWorkId(Long workId)
    {
        return activeWorksMapper.selectActiveWorksByWorkId(workId);
    }

    /**
     * 查询作品列表
     * 
     * @param activeWorks 作品
     * @return 作品
     */
    @Override
    public List<ActiveWorks> selectActiveWorksList(ActiveWorks activeWorks)
    {
        return activeWorksMapper.selectActiveWorksList(activeWorks);
    }

    /**
     * 新增作品
     * 
     * @param activeWorks 作品
     * @return 结果
     */
    @Override
    public int insertActiveWorks(ActiveWorks activeWorks)
    {
        activeWorks.setCreateTime(DateUtils.getNowDate());
        return activeWorksMapper.insertActiveWorks(activeWorks);
    }

    /**
     * 修改作品
     * 
     * @param activeWorks 作品
     * @return 结果
     */
    @Override
    public int updateActiveWorks(ActiveWorks activeWorks)
    {
        return activeWorksMapper.updateActiveWorks(activeWorks);
    }

    /**
     * 批量删除作品
     * 
     * @param workIds 需要删除的作品主键
     * @return 结果
     */
    @Override
    public int deleteActiveWorksByWorkIds(Long[] workIds)
    {
        return activeWorksMapper.deleteActiveWorksByWorkIds(workIds);
    }

    /**
     * 删除作品信息
     * 
     * @param workId 作品主键
     * @return 结果
     */
    @Override
    public int deleteActiveWorksByWorkId(Long workId)
    {
        return activeWorksMapper.deleteActiveWorksByWorkId(workId);
    }
}

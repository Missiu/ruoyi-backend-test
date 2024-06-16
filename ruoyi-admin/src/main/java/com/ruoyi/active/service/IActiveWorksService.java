package com.ruoyi.active.service;

import java.util.List;
import com.ruoyi.active.domain.ActiveWorks;

/**
 * 作品Service接口
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
public interface IActiveWorksService 
{
    /**
     * 查询作品
     * 
     * @param workId 作品主键
     * @return 作品
     */
    public ActiveWorks selectActiveWorksByWorkId(Long workId);

    /**
     * 查询作品列表
     * 
     * @param activeWorks 作品
     * @return 作品集合
     */
    public List<ActiveWorks> selectActiveWorksList(ActiveWorks activeWorks);

    /**
     * 新增作品
     * 
     * @param activeWorks 作品
     * @return 结果
     */
    public int insertActiveWorks(ActiveWorks activeWorks);

    /**
     * 修改作品
     * 
     * @param activeWorks 作品
     * @return 结果
     */
    public int updateActiveWorks(ActiveWorks activeWorks);

    /**
     * 批量删除作品
     * 
     * @param workIds 需要删除的作品主键集合
     * @return 结果
     */
    public int deleteActiveWorksByWorkIds(Long[] workIds);

    /**
     * 删除作品信息
     * 
     * @param workId 作品主键
     * @return 结果
     */
    public int deleteActiveWorksByWorkId(Long workId);

}

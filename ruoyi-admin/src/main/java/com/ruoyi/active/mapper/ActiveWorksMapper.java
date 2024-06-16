package com.ruoyi.active.mapper;

import java.util.List;
import com.ruoyi.active.domain.ActiveWorks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 作品Mapper接口
 * 
 * @author huzhihao
 * @date 2024-06-15
 */
@Component
public interface ActiveWorksMapper 
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
     * 删除作品
     * 
     * @param workId 作品主键
     * @return 结果
     */
    public int deleteActiveWorksByWorkId(Long workId);

    /**
     * 批量删除作品
     * 
     * @param workIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteActiveWorksByWorkIds(Long[] workIds);
}

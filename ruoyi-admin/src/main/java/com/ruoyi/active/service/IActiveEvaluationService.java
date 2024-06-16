package com.ruoyi.active.service;

import java.util.List;
import com.ruoyi.active.domain.ActiveEvaluation;

/**
 * 作品评价Service接口
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
public interface IActiveEvaluationService 
{
    /**
     * 查询作品评价
     * 
     * @param evaId 作品评价主键
     * @return 作品评价
     */
    public ActiveEvaluation selectActiveEvaluationByEvaId(Long evaId);

    /**
     * 查询作品评价列表
     * 
     * @param activeEvaluation 作品评价
     * @return 作品评价集合
     */
    public List<ActiveEvaluation> selectActiveEvaluationList(ActiveEvaluation activeEvaluation);

    /**
     * 新增作品评价
     * 
     * @param activeEvaluation 作品评价
     * @return 结果
     */
    public int insertActiveEvaluation(ActiveEvaluation activeEvaluation);

    /**
     * 修改作品评价
     * 
     * @param activeEvaluation 作品评价
     * @return 结果
     */
    public int updateActiveEvaluation(ActiveEvaluation activeEvaluation);

    /**
     * 批量删除作品评价
     * 
     * @param evaIds 需要删除的作品评价主键集合
     * @return 结果
     */
    public int deleteActiveEvaluationByEvaIds(Long[] evaIds);

    /**
     * 删除作品评价信息
     * 
     * @param evaId 作品评价主键
     * @return 结果
     */
    public int deleteActiveEvaluationByEvaId(Long evaId);
}

package com.ruoyi.active.service;

import com.ruoyi.active.domain.ActiveFile;

import java.util.List;

/**
 * 作品相关文件Service接口
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
public interface IActiveFileService 
{
    /**
     * 查询作品相关文件
     * 
     * @param fileId 作品相关文件主键
     * @return 作品相关文件
     */
    public ActiveFile selectActiveFileByFileId(Long fileId);

    /**
     * 查询作品相关文件列表
     * 
     * @param activeFile 作品相关文件
     * @return 作品相关文件集合
     */
    public List<ActiveFile> selectActiveFileList(ActiveFile activeFile);

    /**
     * 新增作品相关文件
     * 
     * @param activeFile 作品相关文件
     * @return 结果
     */
    public int insertActiveFile(ActiveFile activeFile);

    /**
     * 修改作品相关文件
     * 
     * @param activeFile 作品相关文件
     * @return 结果
     */
    public int updateActiveFile(ActiveFile activeFile);

    /**
     * 批量删除作品相关文件
     * 
     * @param fileIds 需要删除的作品相关文件主键集合
     * @return 结果
     */
    public int deleteActiveFileByFileIds(Long[] fileIds);

    /**
     * 删除作品相关文件信息
     * 
     * @param fileId 作品相关文件主键
     * @return 结果
     */
    public int deleteActiveFileByFileId(Long fileId);
}

package com.ruoyi.active.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 作品相关文件对象 active_file
 * 
 * @author huzhihao
 * @date 2024-06-16
 */
public class ActiveFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文件ID */
    private Long fileId;

    /** 作品ID */
    @Excel(name = "作品ID")
    private Long workId;

    /** 文件类别 */
    @Excel(name = "文件类别")
    private String fileCategory;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件存储路径 */
    @Excel(name = "文件存储路径")
    private String fileStoragePath;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadedAt;

    public void setFileId(Long fileId) 
    {
        this.fileId = fileId;
    }

    public Long getFileId() 
    {
        return fileId;
    }
    public void setWorkId(Long workId) 
    {
        this.workId = workId;
    }

    public Long getWorkId() 
    {
        return workId;
    }
    public void setFileCategory(String fileCategory) 
    {
        this.fileCategory = fileCategory;
    }

    public String getFileCategory() 
    {
        return fileCategory;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileStoragePath(String fileStoragePath) 
    {
        this.fileStoragePath = fileStoragePath;
    }

    public String getFileStoragePath() 
    {
        return fileStoragePath;
    }
    public void setUploadedAt(Date uploadedAt) 
    {
        this.uploadedAt = uploadedAt;
    }

    public Date getUploadedAt() 
    {
        return uploadedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileId", getFileId())
            .append("workId", getWorkId())
            .append("fileCategory", getFileCategory())
            .append("fileName", getFileName())
            .append("fileStoragePath", getFileStoragePath())
            .append("uploadedAt", getUploadedAt())
            .toString();
    }
}

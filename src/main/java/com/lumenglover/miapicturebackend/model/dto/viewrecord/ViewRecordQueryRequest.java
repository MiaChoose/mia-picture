package com.lumenglover.miapicturebackend.model.dto.viewrecord;

import com.lumenglover.miapicturebackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 浏览记录查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ViewRecordQueryRequest extends PageRequest {

    /**
     * 目标类型（1-图片，2-帖子等）
     */
    private Integer targetType;
}

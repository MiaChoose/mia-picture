package com.lumenglover.miapicturebackend.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReminderVO implements Serializable {
    private Long id;
    private String content;
    private String time;
    private Boolean completed;

    /**
     * 是否收藏
     */
    private Boolean starred;

    /**
     * 是否重要
     */
    private Boolean important;

    private static final long serialVersionUID = 1L;
}

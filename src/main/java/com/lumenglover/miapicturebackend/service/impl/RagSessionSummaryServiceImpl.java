package com.lumenglover.miapicturebackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lumenglover.miapicturebackend.mapper.RagSessionSummaryMapper;
import com.lumenglover.miapicturebackend.model.entity.RagSessionSummary;
import com.lumenglover.miapicturebackend.service.RagSessionSummaryService;
import org.springframework.stereotype.Service;

/**
 * 智能客服会话摘要 Service 实现
 */
@Service
public class RagSessionSummaryServiceImpl extends ServiceImpl<RagSessionSummaryMapper, RagSessionSummary> implements RagSessionSummaryService {
}

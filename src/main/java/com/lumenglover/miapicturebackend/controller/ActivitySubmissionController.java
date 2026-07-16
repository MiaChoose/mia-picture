package com.lumenglover.miapicturebackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumenglover.miapicturebackend.annotation.AuthCheck;
import com.lumenglover.miapicturebackend.common.BaseResponse;

import com.lumenglover.miapicturebackend.common.ResultUtils;
import com.lumenglover.miapicturebackend.constant.UserConstant;
import com.lumenglover.miapicturebackend.exception.ErrorCode;
import com.lumenglover.miapicturebackend.exception.ThrowUtils;
import com.lumenglover.miapicturebackend.model.dto.activity.ActivitySubmissionAddRequest;
import com.lumenglover.miapicturebackend.model.dto.activity.ActivitySubmissionQueryRequest;
import com.lumenglover.miapicturebackend.model.dto.activity.ActivitySubmissionReviewRequest;
import com.lumenglover.miapicturebackend.model.entity.ActivitySubmission;
import com.lumenglover.miapicturebackend.model.vo.ActivitySubmissionVO;
import com.lumenglover.miapicturebackend.service.ActivitySubmissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 活动提交接口
 */
@RestController
@RequestMapping("/activity/submission")
@Slf4j
public class ActivitySubmissionController {

    @Resource
    private ActivitySubmissionService activitySubmissionService;

    /**
     * 提交图片到活动
     */
    @PostMapping("/submit")
    public BaseResponse<Long> submitToActivity(@RequestBody ActivitySubmissionAddRequest request,
                                                HttpServletRequest httpRequest) {
        Long submissionId = activitySubmissionService.submitToActivity(request, httpRequest);
        return ResultUtils.success(submissionId);
    }

    /**
     * 审核提交（仅管理员）
     */
    @PostMapping("/review")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> reviewSubmission(@RequestBody ActivitySubmissionReviewRequest request,
                                                   HttpServletRequest httpRequest) {
        Boolean result = activitySubmissionService.reviewSubmission(request, httpRequest);
        return ResultUtils.success(result);
    }

    /**
     * 分页获取提交列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<ActivitySubmissionVO>> listSubmissionByPage(
            @RequestBody ActivitySubmissionQueryRequest request,
            HttpServletRequest httpRequest) {
        long current = request.getCurrent();
        long size = request.getPageSize();

        // 查询数据库
        Page<ActivitySubmission> submissionPage = activitySubmissionService.page(
                new Page<>(current, size),
                activitySubmissionService.getQueryWrapper(request));

        // 获取封装类
        return ResultUtils.success(activitySubmissionService.getSubmissionVOPage(submissionPage, httpRequest));
    }

    /**
     * 获取我的提交列表
     */
    @PostMapping("/my/list/page")
    public BaseResponse<Page<ActivitySubmissionVO>> listMySubmissionByPage(
            @RequestBody ActivitySubmissionQueryRequest request,
            HttpServletRequest httpRequest) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR);

        // 补充查询条件，只查询当前用户的提交
        long current = request.getCurrent();
        long size = request.getPageSize();

        // 查询数据库
        Page<ActivitySubmission> submissionPage = activitySubmissionService.page(
                new Page<>(current, size),
                activitySubmissionService.getQueryWrapper(request));

        // 获取封装类
        return ResultUtils.success(activitySubmissionService.getSubmissionVOPage(submissionPage, httpRequest));
    }

    /**
     * 根据ID获取提交详情
     */
    @GetMapping("/get")
    public BaseResponse<ActivitySubmissionVO> getSubmissionById(Long id, HttpServletRequest httpRequest) {
        ThrowUtils.throwIf(id == null || id <= 0, ErrorCode.PARAMS_ERROR);

        ActivitySubmission submission = activitySubmissionService.getById(id);
        ThrowUtils.throwIf(submission == null, ErrorCode.NOT_FOUND_ERROR);

        ActivitySubmissionVO submissionVO = activitySubmissionService.getSubmissionVO(submission, httpRequest);
        return ResultUtils.success(submissionVO);
    }
}

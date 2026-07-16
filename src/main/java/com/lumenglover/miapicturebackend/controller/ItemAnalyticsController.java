package com.lumenglover.miapicturebackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumenglover.miapicturebackend.annotation.AuthCheck;
import com.lumenglover.miapicturebackend.common.BaseResponse;
import com.lumenglover.miapicturebackend.common.ResultUtils;
import com.lumenglover.miapicturebackend.constant.UserConstant;
import com.lumenglover.miapicturebackend.exception.BusinessException;
import com.lumenglover.miapicturebackend.exception.ErrorCode;
import com.lumenglover.miapicturebackend.exception.ThrowUtils;
import com.lumenglover.miapicturebackend.model.entity.Picture;
import com.lumenglover.miapicturebackend.model.entity.Post;
import com.lumenglover.miapicturebackend.model.entity.User;
import com.lumenglover.miapicturebackend.model.vo.InteractionUserVO;
import com.lumenglover.miapicturebackend.model.vo.ItemAnalyticsVO;
import com.lumenglover.miapicturebackend.service.ItemAnalyticsService;
import com.lumenglover.miapicturebackend.service.PictureService;
import com.lumenglover.miapicturebackend.service.PostService;
import com.lumenglover.miapicturebackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 内容分析接口
 */
@RestController
@RequestMapping("/item/analytics")
@Slf4j
public class ItemAnalyticsController {

    @Resource
    private ItemAnalyticsService itemAnalyticsService;

    @Resource
    private UserService userService;

    @Resource
    private PictureService pictureService;

    @Resource
    private PostService postService;

    /**
     * 获取图片分析数据
     */
    @GetMapping("/picture/{id}")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<ItemAnalyticsVO> getPictureAnalytics(@PathVariable String id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null || "undefined".equals(id), ErrorCode.PARAMS_ERROR);
        Long pictureId;
        try {
            pictureId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效的ID格式");
        }
        ThrowUtils.throwIf(pictureId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        // 权限检查：作者
        Picture picture = pictureService.getById(pictureId);
        ThrowUtils.throwIf(picture == null, ErrorCode.NOT_FOUND_ERROR);
        if (!java.util.Objects.equals(picture.getUserId(), loginUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无权查看该图片的统计数据");
        }

        ItemAnalyticsVO analytics = itemAnalyticsService.getPictureAnalytics(pictureId);
        return ResultUtils.success(analytics);
    }

    /**
     * 获取帖子分析数据
     */
    @GetMapping("/post/{id}")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<ItemAnalyticsVO> getPostAnalytics(@PathVariable String id, HttpServletRequest request) {
        ThrowUtils.throwIf(id == null || "undefined".equals(id), ErrorCode.PARAMS_ERROR);
        Long postId;
        try {
            postId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效的ID格式");
        }
        ThrowUtils.throwIf(postId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        // 权限检查：作者
        Post post = postService.getById(postId);
        ThrowUtils.throwIf(post == null, ErrorCode.NOT_FOUND_ERROR);
        if (!java.util.Objects.equals(post.getUserId(), loginUser.getId())) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "无权查看该帖子的统计数据");
        }

        ItemAnalyticsVO analytics = itemAnalyticsService.getPostAnalytics(postId);
        return ResultUtils.success(analytics);
    }

    /**
     * 获取互动列表
     */
    @GetMapping("/interactions")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Page<InteractionUserVO>> getInteractionList(
            @RequestParam String targetId,
            @RequestParam Integer targetType,
            @RequestParam String type,
            @RequestParam(defaultValue = "1") long current,
            @RequestParam(defaultValue = "10") long size,
            HttpServletRequest request) {

        ThrowUtils.throwIf(targetId == null || "undefined".equals(targetId) || targetType == null || type == null, ErrorCode.PARAMS_ERROR);
        Long targetIdLong;
        try {
            targetIdLong = Long.parseLong(targetId);
        } catch (NumberFormatException e) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "无效的ID格式");
        }
        User loginUser = userService.getLoginUser(request);

        // 权限检查
        // 权限检查
        if (targetType == 1) {
            Picture picture = pictureService.getById(targetIdLong);
            ThrowUtils.throwIf(picture == null, ErrorCode.NOT_FOUND_ERROR);
            if (!java.util.Objects.equals(picture.getUserId(), loginUser.getId())) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        } else if (targetType == 2) {
            Post post = postService.getById(targetIdLong);
            ThrowUtils.throwIf(post == null, ErrorCode.NOT_FOUND_ERROR);
            if (!java.util.Objects.equals(post.getUserId(), loginUser.getId())) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }

        Page<InteractionUserVO> interactionList = itemAnalyticsService.getInteractionList(targetIdLong, targetType, type, current, size);
        return ResultUtils.success(interactionList);
    }
}

package com.lumenglover.miapicturebackend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lumenglover.miapicturebackend.common.BaseResponse;
import com.lumenglover.miapicturebackend.common.ResultUtils;
import com.lumenglover.miapicturebackend.exception.BusinessException;
import com.lumenglover.miapicturebackend.exception.ErrorCode;
import com.lumenglover.miapicturebackend.exception.ThrowUtils;
import com.lumenglover.miapicturebackend.model.dto.favoriterecord.FavoriteRecordAddRequest;
import com.lumenglover.miapicturebackend.model.dto.favoriterecord.FavoriteRecordQueryRequest;
import com.lumenglover.miapicturebackend.model.entity.User;
import com.lumenglover.miapicturebackend.model.vo.FavoriteRecordVO;
import com.lumenglover.miapicturebackend.service.UserService;
import com.lumenglover.miapicturebackend.service.FavoriteRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 收藏记录控制器
 */
@RestController
@RequestMapping("/favorite-record")
@Slf4j
public class FavoriteRecordController {

    @Resource
    private FavoriteRecordService favoriteRecordService;

    @Resource
    private UserService userService;

    /**
     * 添加收藏记录
     *
     * @param favoriteRecordAddRequest 请求参数
     * @param request                  HTTP请求
     * @return 结果
     */
    @PostMapping("/add")
    public BaseResponse<Long> addFavoriteRecord(@RequestBody FavoriteRecordAddRequest favoriteRecordAddRequest, HttpServletRequest request) {
        if (favoriteRecordAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = favoriteRecordService.addFavoriteRecord(favoriteRecordAddRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 检查是否已收藏
     *
     * @param userId     用户ID
     * @param targetId   目标ID
     * @param targetType 目标类型
     * @param request    HTTP请求
     * @return 结果
     */
    @GetMapping("/check")
    public BaseResponse<Boolean> checkHasFavorited(@RequestParam Long userId, @RequestParam Long targetId,
                                                   @RequestParam Integer targetType, HttpServletRequest request) {
        ThrowUtils.throwIf(userId == null || targetId == null || targetType == null, ErrorCode.PARAMS_ERROR);

        User loginUser = userService.getLoginUser(request);
        if (!loginUser.getId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "只能查询自己的收藏记录");
        }

        boolean result = favoriteRecordService.hasFavorited(userId, targetId, targetType);
        return ResultUtils.success(result);
    }

    /**
     * 取消收藏
     *
     * @param userId     用户ID
     * @param targetId   目标ID
     * @param targetType 目标类型
     * @param request    HTTP请求
     * @return 结果
     */
    @PostMapping("/cancel")
    public BaseResponse<Boolean> cancelFavorite(@RequestParam Long userId, @RequestParam Long targetId,
                                                @RequestParam Integer targetType, HttpServletRequest request) {
        ThrowUtils.throwIf(userId == null || targetId == null || targetType == null, ErrorCode.PARAMS_ERROR);

        User loginUser = userService.getLoginUser(request);
        if (!loginUser.getId().equals(userId)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR, "只能取消自己的收藏");
        }

        boolean result = favoriteRecordService.cancelFavorite(userId, targetId, targetType);
        return ResultUtils.success(result);
    }

    /**
     * 获取用户被收藏历史
     */
    @PostMapping("/history")
    public BaseResponse<Page<FavoriteRecordVO>> getFavoriteHistory(@RequestBody FavoriteRecordQueryRequest favoriteQueryRequest,
                                                                   HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 限制爬虫
        long size = favoriteQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<FavoriteRecordVO> favoriteHistory = favoriteRecordService.getUserFavoriteHistory(favoriteQueryRequest, loginUser.getId());
        return ResultUtils.success(favoriteHistory);
    }

    /**
     * 获取我的收藏历史
     */
    @PostMapping("/my/history")
    public BaseResponse<Page<FavoriteRecordVO>> getMyFavoriteHistory(@RequestBody FavoriteRecordQueryRequest favoriteQueryRequest,
                                                                     HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 限制爬虫
        long size = favoriteQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<FavoriteRecordVO> favoriteHistory = favoriteRecordService.getMyFavoriteHistory(favoriteQueryRequest, loginUser.getId());
        return ResultUtils.success(favoriteHistory);
    }

    /**
     * 获取未读收藏消息
     */
    @GetMapping("/unread")
    public BaseResponse<List<FavoriteRecordVO>> getUnreadFavorites(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<FavoriteRecordVO> unreadFavorites = favoriteRecordService.getAndClearUnreadFavorites(loginUser.getId());
        return ResultUtils.success(unreadFavorites);
    }

    @GetMapping("/unread/count")
    public BaseResponse<Long> getUnreadFavoritesCount(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        return ResultUtils.success(favoriteRecordService.getUnreadFavoritesCount(loginUser.getId()));
    }
}

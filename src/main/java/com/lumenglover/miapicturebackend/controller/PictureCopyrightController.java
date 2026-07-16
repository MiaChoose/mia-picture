package com.lumenglover.miapicturebackend.controller;

import com.lumenglover.miapicturebackend.annotation.AuthCheck;
import com.lumenglover.miapicturebackend.common.BaseResponse;
import com.lumenglover.miapicturebackend.common.ResultUtils;
import com.lumenglover.miapicturebackend.constant.UserConstant;
import com.lumenglover.miapicturebackend.model.dto.copyright.CopyrightRegisterRequest;
import com.lumenglover.miapicturebackend.model.dto.copyright.CopyrightTraceRequest;
import com.lumenglover.miapicturebackend.model.vo.CopyrightInfoVO;
import com.lumenglover.miapicturebackend.service.PictureCopyrightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片版权接口
 */
@RestController
@RequestMapping("/picture/copyright")
@Slf4j
public class PictureCopyrightController {

    @Resource
    private PictureCopyrightService copyrightService;

    /**
     * 申请版权登记
     */
    @PostMapping("/register")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Long> registerCopyright(@RequestBody CopyrightRegisterRequest registerRequest,
                                                  HttpServletRequest request) {
        Long copyrightId = copyrightService.registerCopyright(registerRequest, request);
        return ResultUtils.success(copyrightId);
    }

    /**
     * 更新版权信息
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.DEFAULT_ROLE)
    public BaseResponse<Boolean> updateCopyright(@RequestBody CopyrightRegisterRequest updateRequest,
                                                   HttpServletRequest request) {
        Boolean result = copyrightService.updateCopyright(updateRequest, request);
        return ResultUtils.success(result);
    }

    /**
     * 版权溯源查询（无需登录）
     */
    @PostMapping("/trace")
    public BaseResponse<CopyrightInfoVO> traceCopyright(@RequestBody CopyrightTraceRequest traceRequest,
                                                         HttpServletRequest request) {
        CopyrightInfoVO copyrightInfo = copyrightService.traceCopyright(traceRequest, request);
        return ResultUtils.success(copyrightInfo);
    }

    /**
     * 根据图片ID获取版权信息
     */
    @GetMapping("/get")
    public BaseResponse<CopyrightInfoVO> getCopyrightByPictureId(@RequestParam String pictureId) {

        CopyrightInfoVO copyrightInfo = copyrightService.getCopyrightInfoByPictureId(Long.valueOf(pictureId));
        return ResultUtils.success(copyrightInfo);
    }
}

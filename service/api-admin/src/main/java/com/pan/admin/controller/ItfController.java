package com.pan.admin.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pan.admin.service.ItfService;
import com.pan.common.constant.PageConstant;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.model.bo.itf.ItfInvokeBO;
import com.pan.model.converter.itf.ItfConverter;
import com.pan.model.converter.itf.ItfVOConverter;
import com.pan.model.entity.Itf;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
import com.pan.model.req.itf.*;
import com.pan.model.vo.itf.ItfDetailsVO;
import com.pan.model.vo.itf.ItfVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-02-21 16:48
 **/
@Slf4j
@RestController
@RequestMapping("/itf")
public class ItfController {
    @Resource
    private ItfService itfService;

    //region 增删改查
    
    @PostMapping("/add")
    public Long addItf(
        @RequestBody ItfAddReq itfAddReq) {
        if (itfAddReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = ItfConverter.INSTANCE.toItf(itfAddReq);
        itfService.validItf(itf, true);

        boolean save = itfService.save(itf);
        if (!save) {
            throw new BusinessException(ResultCode.SAVE_ERR);
        }

        return itf.getId();
    }

    
    @DeleteMapping("/delete/{id}")
    public void deleteItf(@PathVariable Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf oldItf = itfService.getById(id);
        if (oldItf == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = itfService.removeById(id);
        if (!b) {
            throw new BusinessException(ResultCode.DELETE_ERR);
        }
    }

    
    @PutMapping("/update")
    public void updateItf(
        @RequestBody ItfUpdateReq itfUpdateReq) {
        if (itfUpdateReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = ItfConverter.INSTANCE.toItf(itfUpdateReq);
        itfService.validItf(itf, false);

        Itf oldItf = itfService.getById(itf.getId());
        if (oldItf == null) {
            throw new BusinessException(ResultCode.NULL_ERR);
        }

        boolean b = itfService.updateById(itf);
        if (!b) {
            throw new BusinessException(ResultCode.UPDATE_ERR);
        }
    }

    @GetMapping("/get/{id}")
    public ItfVO getItfById(@PathVariable Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = itfService.getById(id);
        return ItfVOConverter.INSTANCE.toItfVO(itf);
    }

    @GetMapping("/details/{id}")
    public ItfDetailsVO getItfDetailsById(@PathVariable Long id) {
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        return itfService.getItfDetailsById(id);
    }

    
    @PostMapping("/list")
    public List<ItfVO> listItf(
        @RequestBody ItfQueryReq itfQueryReq) {
        Itf itfQuery = ItfConverter.INSTANCE.toItf(itfQueryReq);

        QueryWrapper<Itf> wrapper = new QueryWrapper<>(itfQuery);
        List<Itf> itfList = itfService.list(wrapper);

        return itfList.stream()
            .map(ItfVOConverter.INSTANCE::toItfVO)
            .collect(Collectors.toList());
    }

    @PostMapping("/list/page")
    public IPage<ItfVO> listItfByPage(
        @RequestBody ItfQueryReq itfQueryReq) {
        if (itfQueryReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        /*限制爬虫*/
        if (itfQueryReq.getPageSize() > PageConstant.MAX_PAGE_SIZE) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itfQuery = ItfConverter.INSTANCE.toItf(itfQueryReq);

        long pagenum = itfQueryReq.getPageNum();
        long pagesize = itfQueryReq.getPageSize();
        String sortField = itfQueryReq.getSortField();
        boolean sortOrder = itfQueryReq.isSortOrder();
        String name = itfQuery.getName();
        String url = itfQuery.getUrl();
        itfQuery.setName(null);
        itfQuery.setUrl(null);

        QueryWrapper<Itf> wrapper = new QueryWrapper<>(itfQuery);
        /*使用interfaceInfoName字段做模糊查询*/
        wrapper.like(StringUtils.isNotBlank(name), "name", name);
        wrapper.like(StringUtils.isNotBlank(url), "url", url);
        wrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder, sortField);

        IPage<Itf> itfPage = itfService.page(new Page<>(pagenum, pagesize), wrapper);

        return itfPage.convert(ItfVOConverter.INSTANCE::toItfVO);
    }
    //endregion

    //region 接口上线/下线
    
    @PutMapping("/online/{id}")
    public void onlineItf(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = itfService.getById(id);
        if (itf == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "该接口不存在");
        }

        if (itf.getStatus().isOffline()) {
            throw new BusinessException(ResultCode.SYSTEM_ERR, "只有下线的接口才能上线");
        }

        // ItfInvokeBO itfInvokeBO = new ItfInvokeBO(itf);
        // BaseResponse<Object> baseResponse = itfService.invokeItf(itfInvokeBO);
        // if (Objects.isNull(baseResponse)
        //     || ResultCode.isSuccessful(baseResponse.getCode())) {
        //     log.error("接口调用失败, 接口id: [{}], 返回值: [{}]", id, baseResponse);
        //     throw new BusinessException(ResultCode.SYSTEM_ERR, "接口调用失败, 接口id: " + id);
        // }

        Itf itfUpdate = new Itf();
        itfUpdate.setId(id);
        itfUpdate.setStatus(StatusEnum.ONLINE);

        boolean res = itfService.updateById(itfUpdate);
        if (!res) {
            throw new BusinessException(ResultCode.UPDATE_ERR);
        }
    }

    
    @PutMapping("/offline/{id}")
    public void offlineItf(@PathVariable("id") Long id) {
        if (id == null || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = itfService.getById(id);
        if (itf == null) {
            throw new BusinessException(ResultCode.NULL_ERR, "该接口不存在");
        }
        if (itf.getStatus().isOnline()) {
            throw new BusinessException(ResultCode.SYSTEM_ERR, "只有上线的接口才能下线");
        }

        Itf itfUpdate = new Itf();
        itfUpdate.setId(id);
        itfUpdate.setStatus(StatusEnum.OFFLINE);

        boolean res = itfService.updateById(itfUpdate);
        if (!res) {
            throw new BusinessException(ResultCode.UPDATE_ERR);
        }
    }
    //endregion

    @PostMapping("/invoke")
    public BaseResponse<?> invokeItf(
        @RequestBody ItfInvokeReq itfInvokeReq) {
        if (itfInvokeReq == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }
        Long id = itfInvokeReq.getId();
        if (Objects.isNull(id) || id <= 0) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        Itf itf = itfService.getById(id);
        if (itf == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }
        if (itf.getStatus().isOffline()) {
            throw new BusinessException(ResultCode.SYSTEM_ERR, "该接口已下线");
        }

        ItfInvokeBO itfInvokeBO = new ItfInvokeBO(itf, itfInvokeReq);
        return itfService.invokeItf(itfInvokeBO);
    }

    @PostMapping("/url-method")
    public ItfVO getItfByUrlAndMethod(
        @RequestBody UrlMethodReq urlMethodReq) {

        String url = urlMethodReq.getUrl();
        String method = urlMethodReq.getMethod().toUpperCase(Locale.ROOT);
        MethodEnum methodEnum = MethodEnum.valueOf(method.toUpperCase(Locale.ROOT));

        Itf itf = itfService.lambdaQuery()
            .eq(Itf::getUrl, url)
            .eq(Itf::getMethod, methodEnum.getCode())
            .eq(Itf::getStatus, StatusEnum.ONLINE)
            .one();

        return ItfVOConverter.INSTANCE.toItfVO(itf);
    }

    @PostMapping("/path-method")
    public ItfVO getItfByPathAndMethod(
        @RequestBody PathMethodReq pathMethodReq) {

        String path = pathMethodReq.getPath();
        String method = pathMethodReq.getMethod().toUpperCase(Locale.ROOT);
        MethodEnum methodEnum = MethodEnum.valueOf(method.toUpperCase(Locale.ROOT));

        Itf itf = itfService.lambdaQuery()
            .eq(Itf::getPath, path)
            .eq(Itf::getMethod, methodEnum.getCode())
            .eq(Itf::getStatus, StatusEnum.ONLINE)
            .one();

        return ItfVOConverter.INSTANCE.toItfVO(itf);
    }
}

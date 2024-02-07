package com.pan.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pan.common.resp.BaseResponse;
import com.pan.model.bo.itf.ItfInvokeBO;
import com.pan.model.entity.Itf;
import com.pan.model.vo.itf.ItfDetailsVO;

/**
 * @author Mr.Pan
 * @description 针对表【interface_info(接口信息表)】的数据库操作Service
 * @createDate 2023-02-22 10:49:54
 */
public interface ItfService extends IService<Itf> {
    void validItf(Itf itf, boolean add);

    BaseResponse<Object> invokeItf(ItfInvokeBO itfInvokeBO);

    ItfDetailsVO getItfDetailsById(Long id);
}

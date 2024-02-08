package com.pan.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pan.admin.mapper.ItfMapper;
import com.pan.admin.service.ItfService;
import com.pan.admin.service.ParamService;
import com.pan.admin.service.UserService;
import com.pan.common.exception.BusinessException;
import com.pan.common.resp.BaseResponse;
import com.pan.common.resp.ResultCode;
import com.pan.common.util.AuthUtils;
import com.pan.model.bo.itf.ItfInvokeBO;
import com.pan.model.converter.itf.ItfDetailsVOConverter;
import com.pan.model.entity.Itf;
import com.pan.model.entity.Param;
import com.pan.model.entity.User;
import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.param.ParamTypeEnum;
import com.pan.model.vo.itf.ItfDetailsVO;
import com.pan.model.vo.param.ParamVO;
import com.pan.sdk.client.ItfClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Mr.Pan
 * @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
 * @createDate 2023-02-22 10:49:54
 */
@Service
@RequiredArgsConstructor
public class ItfServiceImpl
    extends ServiceImpl<ItfMapper, Itf>
    implements ItfService {
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final ParamService paramService;


    @Override
    public void validItf(Itf itf, boolean add) {
        if (itf == null) {
            throw new BusinessException(ResultCode.PARAMS_ERR);
        }

        String name = itf.getName();
        String url = itf.getUrl();
        MethodEnum method = itf.getMethod();
        String respExample = itf.getRespExample();
        String description = itf.getDescription();

        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, url, description, respExample) || !ObjectUtils.anyNotNull(method)) {
                throw new BusinessException(ResultCode.PARAMS_ERR, "参数不能为空");
            }
        }

        if (Objects.isNull(method)) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "method参数异常");
        }
        if (StringUtils.isNotBlank(name) && name.length() > 256) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "接口名过长");
        }
        if (StringUtils.isNotBlank(url) && url.length() > 1024) {
            throw new BusinessException(ResultCode.PARAMS_ERR, "url过长");
        }
    }

    @SneakyThrows
    @Override
    public BaseResponse<Object> invokeItf(ItfInvokeBO itfInvokeBO) {
        MethodEnum method = itfInvokeBO.getMethod();
        String url = itfInvokeBO.getUrl();
        Map<String, Object> queryParams = itfInvokeBO.getQueryParam();
        Map<String, Object> jsonBody = itfInvokeBO.getBodyParam();

        User user = userService.getById(AuthUtils.getLoginUser().getId());
        ItfClient itfClient = new ItfClient(user.getAccessKey(), user.getSecretKey());

        if (MethodEnum.GET == method) {
            String resJson = itfClient.doGet(url, queryParams);
            return objectMapper.readValue(resJson, new TypeReference<BaseResponse<Object>>() {
            });
        }

        if (MethodEnum.POST == method) {
            String resJson = itfClient.doPost(url, queryParams, jsonBody);
            return objectMapper.readValue(resJson, new TypeReference<BaseResponse<Object>>() {
            });
        }

        throw new BusinessException(ResultCode.PARAMS_ERR, "接口的请求类型不对");
    }

    @Override
    public ItfDetailsVO getItfDetailsById(Long id) {
        List<Param> params = paramService.lambdaQuery()
            .eq(Param::getItfId, id)
            .list();

        Map<ParamTypeEnum, List<Param>> paramMap = params.stream().collect(Collectors.groupingBy(Param::getParamType));

        List<ParamVO> query = ParamVO.build(paramMap.get(ParamTypeEnum.QUERY));
        List<ParamVO> body = ParamVO.build(paramMap.get(ParamTypeEnum.BODY));
        List<ParamVO> respData = ParamVO.build(paramMap.get(ParamTypeEnum.RESP));

        Itf itf = getById(id);
        ItfDetailsVO itfDetailsVO = ItfDetailsVOConverter.INSTANCE.toItfDetailsVo(itf);
        itfDetailsVO.setQueryParam(query);
        itfDetailsVO.setBodyParam(body);
        itfDetailsVO.setCommonResp(ItfDetailsVO.getBaseResp());
        itfDetailsVO.setRespData(respData);

        return itfDetailsVO;
    }
}





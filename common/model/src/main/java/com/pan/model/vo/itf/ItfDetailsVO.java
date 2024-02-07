package com.pan.model.vo.itf;

import com.pan.model.enums.itf.MethodEnum;
import com.pan.model.enums.itf.StatusEnum;
import com.pan.model.enums.param.RequiredEnum;
import com.pan.model.vo.param.ParamVO;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 08:51
 **/
@Data
public class ItfDetailsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String path;

    private String url;

    private MethodEnum method;

        private String description;

    private String queryParamExample;

    private String bodyParamExample;

    private String respExample;

    private String reqHeader;

    private String respHeader;

    private StatusEnum status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<ParamVO> queryParam;

    private List<ParamVO> bodyParam;

    private List<ParamVO> commonResp;

    private List<ParamVO> respData;

    public static List<ParamVO> getBaseResp() {
        ParamVO codeParamVO = new ParamVO(-1L, "code", RequiredEnum.REQUIRED, "number", "响应码, 20000-成功", null);
        ParamVO msgParamVO = new ParamVO(-1L, "msg", RequiredEnum.REQUIRED, "string", "响应消息", null);
        ParamVO dataParamVO = new ParamVO(-1L, "data", RequiredEnum.REQUIRED, "object", "响应数据", null);

        List<ParamVO> paramVOs = new ArrayList<>();
        Collections.addAll(paramVOs, codeParamVO, msgParamVO, dataParamVO);
        return paramVOs;
    }
}

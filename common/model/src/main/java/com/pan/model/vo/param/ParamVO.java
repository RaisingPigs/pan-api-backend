package com.pan.model.vo.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.pan.model.converter.param.ParamVOConverter;
import com.pan.model.entity.Param;
import com.pan.model.enums.param.RequiredEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2024-02-07 08:53
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParamVO implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 参数名
     */
    private String name;

    /**
     * 是否必填, 0否 1是
     */
    private RequiredEnum required;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 描述
     */
    private String description;

    private List<ParamVO> children;

    public static List<ParamVO> build(List<Param> params) {
        if (CollectionUtils.isEmpty(params)) {
            return null;
        }

        // map的key是父id, value是该父亲的所有孩子
        Map<Long, List<ParamVO>> map = new HashMap<>();

        for (Param param : params) {
            Long parentId = param.getParentId();
            Long id = param.getId();
            ParamVO paramVO = ParamVOConverter.INSTANCE.toParamVo(param);

            List<ParamVO> paramVOs = map.computeIfAbsent(parentId, __ -> new ArrayList<>());
            paramVOs.add(paramVO);

            List<ParamVO> currentChildren = map.computeIfAbsent(id, __ -> new ArrayList<>());
            paramVO.setChildren(currentChildren);
        }

        return map.get(0L);
    }
}

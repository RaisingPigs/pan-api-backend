package com.pan.model.req.useritf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * @description:
 * @author: Mr.Pan
 * @create: 2023-03-06 10:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvokeAuthCheckReq implements Serializable {
    private Long userId;
    private Long itfId;
    
    private static final long serialVersionUID = 1L;

    public boolean checkAllAttr() {
        return Objects.nonNull(userId)
            && userId > 0
            && Objects.nonNull(itfId)
            && itfId > 0;
    }
}

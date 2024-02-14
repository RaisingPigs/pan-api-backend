package com.pan.itf.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pan.itf.model.enums.sentence.TypeEnum;
import com.pan.model.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 句子表
 * @TableName sentence
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sentence")
public class Sentence extends BaseEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     *
     */
    @TableField(value = "content")
    private String content;
    /**
     * 类型: 0-毒鸡汤, 1-土味情话,2-笑话
     */
    @TableField(value = "type")
    private TypeEnum type;
}
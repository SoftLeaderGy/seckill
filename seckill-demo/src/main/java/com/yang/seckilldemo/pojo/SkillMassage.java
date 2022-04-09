package com.yang.seckilldemo.pojo;

import com.yang.seckilldemo.vo.GoodsVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 秒杀发送信息实体
 * @Author: Guo.Yang
 * @Date: 2022/04/08/16:59
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillMassage {
    private User user;
    private GoodsVo goodsVo;
}

package com.yang.seckilldemo.controller.businessController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.pojo.SeckillGoods;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.service.SeckillOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SeckillOrder)表控制层
 *
 * @author makejava
 * @since 2022-03-27 23:03:53
 */
@RestController
@RequestMapping("seckillOrder")
public class SeckillOrderController {
    /**
     * 服务对象
     */
    @Resource
    private SeckillOrderService seckillOrderService;

    /**
     * 分页查询
     *
     * @param seckillOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SeckillOrder>> queryByPage(SeckillOrder seckillOrder, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.seckillOrderService.queryByPage(seckillOrder, pageRequest));
        return null;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SeckillOrder> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.seckillOrderService.getBaseMapper().selectById(id));
    }

    /**
     * 新增数据
     *
     * @param seckillOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity add(SeckillOrder seckillOrder) {
        return ResponseEntity.ok(this.seckillOrderService.save(seckillOrder));
    }

    /**
     * 编辑数据
     *
     * @param seckillOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity edit(SeckillOrder seckillOrder) {
        QueryWrapper<SeckillOrder> goodsQueryWrapper = new QueryWrapper<SeckillOrder>();
        goodsQueryWrapper.setEntity(seckillOrder);
        return ResponseEntity.ok(this.seckillOrderService.update(goodsQueryWrapper));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.seckillOrderService.removeById(id));
    }

}


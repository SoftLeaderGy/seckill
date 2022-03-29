package com.yang.seckilldemo.controller.businessController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Order)表控制层
 *
 * @author makejava
 * @since 2022-03-27 23:03:53
 */
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;

    /**
     * 分页查询
     *
     * @param order 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Order>> queryByPage(Order order, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.orderService.queryByPage(order, pageRequest));
        return null;
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Order> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.orderService.getBaseMapper().selectById(id));
    }

    /**
     * 新增数据
     *
     * @param order 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity add(Order order) {
        return ResponseEntity.ok(this.orderService.save(order));
    }

    /**
     * 编辑数据
     *
     * @param order 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity edit(Order order) {
        QueryWrapper<Order> goodsQueryWrapper = new QueryWrapper<Order>();
        goodsQueryWrapper.setEntity(order);
        return ResponseEntity.ok(this.orderService.update(goodsQueryWrapper));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.orderService.removeById(id));
    }

}


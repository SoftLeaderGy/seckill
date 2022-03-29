package com.yang.seckilldemo.controller.businessController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.pojo.SeckillGoods;
import com.yang.seckilldemo.service.SeckillGoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (SeckillGoods)表控制层
 *
 * @author makejava
 * @since 2022-03-27 23:03:53
 */
@RestController
@RequestMapping("seckillGoods")
public class SeckillGoodsController {
    /**
     * 服务对象
     */
    @Resource
    private SeckillGoodsService seckillGoodsService;

    /**
     * 分页查询
     *
     * @param seckillGoods 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<SeckillGoods>> queryByPage(SeckillGoods seckillGoods, PageRequest pageRequest) {
//        return ResponseEntity.ok(this.seckillGoodsService.queryByPage(seckillGoods, pageRequest));
        return null;
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<SeckillGoods> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.seckillGoodsService.getBaseMapper().selectById(id));
    }

    /**
     * 新增数据
     *
     * @param seckillGoods 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity add(SeckillGoods seckillGoods) {
        return ResponseEntity.ok(this.seckillGoodsService.save(seckillGoods));
    }

    /**
     * 编辑数据
     *
     * @param seckillGoods 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity edit(SeckillGoods seckillGoods) {
        QueryWrapper<SeckillGoods> goodsQueryWrapper = new QueryWrapper<SeckillGoods>();
        goodsQueryWrapper.setEntity(seckillGoods);
        return ResponseEntity.ok(this.seckillGoodsService.update(goodsQueryWrapper));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.seckillGoodsService.removeById(id));
    }

}


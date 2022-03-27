package com.yang.seckilldemo.service.impl;

import com.yang.seckilldemo.exception.GlobalException;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.mapper.UserMapper;
import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.utils.CookieUtil;
import com.yang.seckilldemo.utils.MD5Util;
import com.yang.seckilldemo.utils.UUIDUtil;
import com.yang.seckilldemo.vo.LoginVO;
import com.yang.seckilldemo.vo.RespBean;
import com.yang.seckilldemo.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2022-03-22 20:19:12
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userMapper.count(user);
        return new PageImpl<>(this.userMapper.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public RespBean doLogin(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
//        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if(!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
////            RespBean respBean = new RespBean();
////            respBean.setMsg("asdasd");
////            return new respBean;
//        }
//        User user = userMapper.queryById(loginVO.getMobile());
        User user = userMapper.selectById(mobile);
        if (user == null) {
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!MD5Util.formPassToDBpass(password,user.getSlat()).equals(user.getPasword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        // 生成Cookie
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:"+ ticket,user,1, TimeUnit.HOURS);
        CookieUtil.setCookie("userTicket",ticket,request,response);
//        request.getSession().setAttribute(ticket,user);
        return RespBean.success();
    }

    @Override
    public User queryUserByCookie(HttpServletRequest request, HttpServletResponse response, String ticket) {
        if(StringUtils.isEmpty(ticket)){
            return null;
        }
        User user = (User) redisTemplate.opsForValue().get("user:" + ticket);

        if (user == null) {
            CookieUtil.setCookie("userTicket",ticket,request,response);
        }
        return user;
    }
}

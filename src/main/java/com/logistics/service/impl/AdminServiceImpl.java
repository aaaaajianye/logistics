package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.entity.Admin;
import com.logistics.mapper.AdminMapper;
import com.logistics.service.IAdminService;
import com.logistics.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 管理员接口实现类
 */

@Service
public class AdminServiceImpl implements IAdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 管理员登录方法
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Admin login(String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        wrapper.eq("password", Md5Utils.setMD5(password));
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin != null) {
            // 将admin存入session
            session.setAttribute("admin", admin);
            System.out.println(session.getAttribute("admin"));
            return admin;
        } else {
            return null;
        }
    }

    /**
     * 新增管理方法
     *
     * @param username
     * @param password
     * @param name
     * @param phone
     * @return
     */
    @Override
    public String insert(String username, String password, String name, String phone) {
        // 给新的admin对象进行赋值，密码记得要设置Md5码格式进行加密
        Admin admin = new Admin(username, Md5Utils.setMD5(password), name, phone);
        // 新增admin
        Integer count = adminMapper.insert(admin);
        if (count > 0) {
            return "新增管理员成功";
        } else {
            return "新增管理员失败";
        }
    }

    /**
     * 根据用户名username更新我们的admin对象
     *
     * @param username
     * @param password
     * @param name
     * @param phone
     * @return
     */
    @Override
    public String update(String username, String password, String name, String phone) {
        QueryWrapper wrapper = new QueryWrapper();
        // 根据用户名来进行更新
        wrapper.eq("username", username);
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin != null) {
            // 如果密码不为空的话就设置一个Md5码格式的密码进行持久化
            if (password != null && !"".equals(password)) {
                admin.setPassword(Md5Utils.setMD5(password));
            }
            // 如果管理员姓名不为空的话就直接设置管理员的姓名
            if (name != null && !"".equals(name)) {
                admin.setName(name);
            }
            // 如果电话号码不为空的话就设置管理员的联系方式
            if (phone != null && !"".equals(phone)) {
                admin.setPhone(phone);
            }
            Integer count = adminMapper.updateById(admin);
            if (count > 0) {
                return "管理员修改成功";
            } else {
                return "管理员修改失败";
            }
        } else {
            return "此管理账号不存在";
        }
    }

    /**
     * 获取登录的admin对象
     */
    public Admin getAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        // 获取admin对象
        Admin admin = (Admin) session.getAttribute("admin");
        return admin;
    }

}
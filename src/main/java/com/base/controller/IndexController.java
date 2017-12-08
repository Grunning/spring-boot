package com.base.controller;

import com.base.AppProperties;
import com.base.pojo.User;
import com.denghb.dbhelper.impl.DbHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private DbHelperImpl dbHelper;

    @Autowired
    private AppProperties appProperties;

    @RequestMapping("/index")
    private String index(ModelMap model) {
        model.put("name", "Winterbaby High");
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getConfig")
    private Map<String, Object> getConfig() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("field", appProperties.getTestFiled());
        return map;
    }

    @ResponseBody
    @RequestMapping("/insertUser")
    private Map<String, Object> getUserInfo(@RequestParam("name") String name,
                                            @RequestParam("age") int age,
                                            @RequestParam("state") int state) {
        Map<String, Object> map = new HashMap<String, Object>();
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setState(state);
        map.put("msg", dbHelper.insert(user));
        return map;
    }

    @ResponseBody
    @RequestMapping("/selectUser")
    private Map<String, Object> selectUser() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<User> list = dbHelper.list("select * from user", User.class);
        map.put("msg", list);
        return map;
    }

    @ResponseBody
    @RequestMapping("selectById")
    private Map<String, Object> selectById(@RequestParam("id") int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", dbHelper.queryById(User.class, id));
        return map;
    }

}

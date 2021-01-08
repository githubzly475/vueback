package com.example.demo.controllor;

import org.apache.catalina.User;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Date: 2020-12-02
 */
@RestController
@CrossOrigin

public class TestControllor {
    @RequestMapping("/px/login")
    public ModelMap login(@RequestBody User user){
        ModelMap modelMap = new ModelMap();
            modelMap.put("code",0);
        return modelMap;
    }

}

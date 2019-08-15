package com.carl.exportexcle.controller;

import com.carl.exportexcle.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ExcleController {

    @Autowired
    private UserInfoService userService;

    @GetMapping(value = "uc")
    public void getUserCount(HttpServletResponse response, HttpServletRequest request) throws IOException, ParseException {

        /*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format1 = format.format(date);
        System.out.println("String :"+format1);
        Date parse = format.parse(b);
        System.out.println("data :"+parse);*/
        userService.getUserCountByDateAndApp(response,request);
        /* userService.testExcel(response);*/
    }







   /* @GetMapping(value = "ur")
    public void getRightCountByDate(HttpServletResponse response,String b,String e) throws IOException {

        userService.getRightCountByDate(response,b,e);
        *//* userService.testExcel(response);*//*
    }

    @GetMapping(value = "uo")
    public void gotOfferCountByDate(HttpServletResponse response,String b,String e) throws IOException {

        userService.gotOfferCountByDate(response,b,e);
        *//* userService.testExcel(response);*//*
    }

    @GetMapping(value = "ut")
    public void gotTaskUserCountByDate(HttpServletResponse response,String b,String e) throws IOException {

        userService.gotTaskUserCountByDate(response,b,e);
        *//* userService.testExcel(response);*//*
    }*/
}

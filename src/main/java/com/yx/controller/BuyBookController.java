package com.yx.controller;

import com.yx.entities.BookCar;
import com.yx.entities.BookInfo;
import com.yx.service.BuyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("buyBook")
@Controller
public class BuyBookController {

    @Autowired
    private BuyBookService buyBookService;

    //查询账户余额
    @ResponseBody
    @RequestMapping(value = "getUserBalance", method = RequestMethod.GET)
    public Map<String, Object> getUserBalance(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        double userBalance = 0;
        try {
            userBalance = this.buyBookService.selectUserBalanceByUserId(userId);
            map.put("code", 1001);
            map.put("message", "账户余额查询成功");
            map.put("userBalance", userBalance);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "账户余额查询失败");
            return map;
        }
    }

    //充值
    @ResponseBody
    @RequestMapping(value = "addMoney", method = RequestMethod.PUT)
    public Map<String, Object> addMoney(Integer userId, Double addMoney) {
        Map<String, Object> map = new HashMap<>();
        int count = 0;
        try {
            count = this.buyBookService.updateUserBalanceByUserId(userId, addMoney);
            if (count == 0) {
                map.put("code", 1002);
                map.put("message", "充值失败");
                return map;
            } else {
                map.put("code", 1001);
                map.put("message", "充值成功");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "充值失败");
            return map;
        }
    }

    //查询所有图书
    @ResponseBody
    @RequestMapping(value = "getBookInfo", method = RequestMethod.GET)
    public Map<String, Object> getBookInfo(Integer pageSize, Integer pageCurrent) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<BookInfo> bookInfos = this.buyBookService.selectBookInfo(pageSize, pageCurrent);
            if (bookInfos.isEmpty()) {
                map.put("code", 1002);
                map.put("message", "查询失败，图书为空");
                return map;
            }
            map.put("code", 1001);
            map.put("message", "查询成功");
            map.put("bookInfo", bookInfos);
            map.put("total", this.buyBookService.selectBookInfoCount());//查询图书数据笔数
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "查询失败");
            return map;
        }
    }

    //买书
    @ResponseBody
    @RequestMapping(value = "buyBook", method = RequestMethod.PUT)
    public Map<String, Object> buyBook(Integer userId, Integer bookId) {
        Map<String, Object> map = new HashMap<>();
        try {
            this.buyBookService.buyBook(userId, bookId);
            map.put("code", 1001);
            map.put("message", "买书成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "买书失败");
            return map;
        }
    }

    //加入购物车
    @ResponseBody
    @RequestMapping(value = "addCar", method = RequestMethod.POST)
    public Map<String, Object> addCar(Integer userId, Integer bookId) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = this.buyBookService.addCar(userId, bookId);
            if (count == 0) {
                map.put("code", 1002);
                map.put("message", "加入购物车失败");
                return map;
            }
            map.put("code", 1001);
            map.put("message", "加入购物车成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "加入购物车失败");
            return map;
        }
    }


    //购物车列表查询
    @ResponseBody
    @RequestMapping(value = "getBookCar", method = RequestMethod.GET)
    public Map<String, Object> getBookCar(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<BookCar> bookCars = this.buyBookService.getBookCar(userId);
            if (bookCars.isEmpty()) {
                map.put("code", 1002);
                map.put("message", "查询失败");
                return map;
            }
            map.put("code", 1001);
            map.put("message", "查询成功");
            map.put("bookCar", bookCars);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "查询失败");
            return map;
        }
    }

    //修改购物车数量
    @ResponseBody
    @RequestMapping(value = "updateBookCar", method = RequestMethod.PUT)
    public Map<String, Object> updateBookCar(Integer carId, Integer bookAmount) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = this.buyBookService.updateBookCar(carId, bookAmount);
            if (count == 0) {
                map.put("code", 1002);
                map.put("message", "修改失败");
                return map;
            }
            map.put("code", 1001);
            map.put("message", "修改成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "修改失败");
            return map;
        }
    }


    //删除购物车
    @ResponseBody
    @RequestMapping(value = "deleteBookCar", method = RequestMethod.DELETE)
    public Map<String, Object> deleteBookCar(Integer carId) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = this.buyBookService.deleteBookCar(carId);
            if (count == 0) {
                map.put("code", 1002);
                map.put("message", "删除失败");
                return map;
            }
            map.put("code", 1001);
            map.put("message", "删除成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "删除失败");
            return map;
        }
    }


    //提交购物车
    @ResponseBody
    @RequestMapping(value = "submitBookCar", method = RequestMethod.PUT)
    public Map<String, Object> submitBookCar(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        try {
            this.buyBookService.submitBookCar(userId);
            map.put("code", 1001);
            map.put("message", "买书成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 1002);
            map.put("message", "买书失败");
            return map;
        }
    }

}

package com.yx.service;

import com.yx.entities.BookCar;
import com.yx.entities.BookInfo;

import java.util.List;

public interface BuyBookService {

    /*
    查询图书信息
    pageSize：全部页码数
    pageCurrent：当前页码数
     */
    public List<BookInfo> selectBookInfo(Integer pageSize, Integer pageCurrent);

    //查询图书笔数
    public int selectBookInfoCount();

    //查询当前账户余额
    public Double selectUserBalanceByUserId(Integer userId);

    //修改当前账户余额
    public int updateUserBalanceByUserId(Integer userId, Double addMoney);

    //买书
    public void buyBook(Integer userId, Integer bookId);

    //加入购物车
    public int addCar(Integer userId, Integer bookId);

    //获得购物车信息
    public List<BookCar> getBookCar(Integer userId);

    //修改购物车数量
    public int updateBookCar(Integer carId, Integer bookAmount);

    //删除购物车
    public int deleteBookCar(Integer carId);

    //提交购物车
    public void submitBookCar(Integer userId);


}

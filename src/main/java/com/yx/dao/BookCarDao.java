package com.yx.dao;

import com.yx.entities.BookCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookCarDao {

    //将图书加入购物车
    public int insertBookCar(@Param("userId") Integer userId, @Param("bookId")Integer bookId);

    //根据用户Id查询购物车数据
    public List<BookCar> selectBookCarByUserId(@Param("userId")Integer userId);

    //查询当前用户购物车中是否有对应编号的图书
    public int selectCountByUserIdAndBookId(@Param("userId")Integer userId, @Param("bookId")Integer bookId);

    //同一图书多次加入到购物车时修改图书的总价和数量
    public int updateBookCarByUserIdAndBookId(@Param("userId")Integer userId, @Param("boookId")Integer boookId);

    //修改购物车中图书数量
    public int updateBookCarBookAmountAndTotalPrice(@Param("carId")Integer carId, @Param("bookAmount")Integer bookAmount);

    //删除购物车中的图书
    public int deleteBookCar(Integer carId);
}

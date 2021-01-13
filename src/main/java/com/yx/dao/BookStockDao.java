package com.yx.dao;

import com.yx.entities.BookStock;
import org.apache.ibatis.annotations.Param;

public interface BookStockDao {

    //查询图书库存信息
    public BookStock selectBookStockByStockId(@Param("stockId") Integer stockId);

    //更改图书库存
    public int updateBookAmountByBookStockId(@Param("bookId") Integer bookId, @Param("bookNum") Integer bookNum);

    //根据图书id查询图书库存数据
    public int selectBookStockByBookId(@Param("bookId") Integer bookId);
}

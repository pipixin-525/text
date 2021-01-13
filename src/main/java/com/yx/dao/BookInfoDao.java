package com.yx.dao;

import com.yx.entities.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookInfoDao {

    /*
    查询图书信息
    pageSize：全部页码数
    pageCurrent：当前页码数
     */
    public List<BookInfo> selectBookInfo(@Param("pageSize") Integer pageSize, @Param("pageCurrent") Integer pageCurrent);

    //查询数据笔数
    public Integer selectBookInfoCount();

    //查询图书单价
    public Double selectBookPriceByBookId(@Param("bookId") Integer bookId);

}

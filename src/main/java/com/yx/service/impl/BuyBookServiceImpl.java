package com.yx.service.impl;

import com.yx.dao.BookCarDao;
import com.yx.dao.BookInfoDao;
import com.yx.dao.BookStockDao;
import com.yx.dao.UserDao;
import com.yx.entities.BookCar;
import com.yx.entities.BookInfo;
import com.yx.service.BuyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BuyBookServiceImpl implements BuyBookService {

    @Autowired
    private BookInfoDao bookInfoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookStockDao bookStockDao;
    @Autowired
    private BookCarDao bookCarDao;

    @Override
    public List<BookInfo> selectBookInfo(Integer pageSize, Integer pageCurrent) {
        List<BookInfo> bookInfos = this.bookInfoDao.selectBookInfo(pageSize, (pageCurrent - 1) * pageSize);
        for (BookInfo bookInfo : bookInfos) {
            if (null != bookInfo.getStockId()) {
                bookInfo.setBookStock(this.bookStockDao.selectBookStockByStockId(bookInfo.getStockId()));
            }
        }
        return bookInfos;
    }

    @Override
    public int selectBookInfoCount() {
        return this.bookInfoDao.selectBookInfoCount();
    }

    @Override
    public Double selectUserBalanceByUserId(Integer userId) {
        return this.userDao.selectUserBalanceByUserId(userId);
    }

    @Override
    public int updateUserBalanceByUserId(Integer userId, Double addMoney) {
        return this.userDao.updateUserBalance(userId, addMoney);
    }

    //买书
    @Transactional
    @Override
    public void buyBook(Integer userId, Integer bookId) {
        //查询图书单价
        Double price = this.bookInfoDao.selectBookPriceByBookId(bookId);
        //减少账户余额
        int count = this.userDao.updateUserBalance(userId, price * -1);
        //查询账户余额
        if (this.userDao.selectUserBalanceByUserId(userId) < 0) {
            //余额不足，事务回滚
            throw new RuntimeException("账户余额不足");
        }
        //减少图书库存
        count = this.bookStockDao.updateBookAmountByBookStockId(bookId, 1);
        //查询图书库存
        if (this.bookStockDao.selectBookStockByBookId(bookId) < 0) {
            //图书库存不足，事务回滚
            throw new RuntimeException("图书库存不足");
        }
    }


    //加入购物车
    @Override
    public int addCar(Integer userId, Integer bookId) {
        //判断当前用户购物车中是否有该图书
        int count = this.bookCarDao.selectCountByUserIdAndBookId(userId, bookId);
        //如果有数据，则修改，如果没有数据，则添加
        if (count > 0) {
            return this.bookCarDao.updateBookCarByUserIdAndBookId(userId, bookId);
        } else {
            return this.bookCarDao.insertBookCar(userId, bookId);
        }
    }

    //获得购物车信息
    @Override
    public List<BookCar> getBookCar(Integer userId) {
        return this.bookCarDao.selectBookCarByUserId(userId);
    }

    //修改购物车数量
    @Override
    public int updateBookCar(Integer carId, Integer bookAmount) {
        return this.bookCarDao.updateBookCarBookAmountAndTotalPrice(carId, bookAmount);
    }

    //删除购物车
    @Override
    public int deleteBookCar(Integer carId) {
        return this.bookCarDao.deleteBookCar(carId);
    }

    //提交购物车
    @Transactional
    @Override
    public void submitBookCar(Integer userId) {
        //查询userId的所有购物车数据
        List<BookCar> bookCars = this.bookCarDao.selectBookCarByUserId(userId);
        for (BookCar bookCar : bookCars) {
            //减少图书库存
            this.bookCarDao.updateBookCarByUserIdAndBookId(userId, bookCar.getBookId());
            //查询图书库存是否足够
            if (this.bookStockDao.selectBookStockByBookId(bookCar.getBookId()) < 0) {
                throw new RuntimeException("图书库存不足");
            }
            //减少账户余额
            this.userDao.updateUserBalance(userId, bookCar.getBookPrice() * -1);
            //查询账户余额
            if (this.userDao.selectUserBalanceByUserId(userId) < 0) {
                throw new RuntimeException("账户余额不足");
            }
            //清空购物车
            this.bookCarDao.deleteBookCar(bookCar.getCarId());
        }
    }
}

package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.po.Order;
import com.example.demo.service.OrderService;
import com.example.demo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author Lqy
* @description 针对表【order】的数据库操作Service实现
* @createDate 2023-06-09 09:23:02
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

}





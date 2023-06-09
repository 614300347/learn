package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.po.Ticket;
import com.example.demo.service.TicketService;
import com.example.demo.mapper.TicketMapper;
import org.springframework.stereotype.Service;

/**
* @author Lqy
* @description 针对表【ticket】的数据库操作Service实现
* @createDate 2023-06-09 09:23:02
*/
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
    implements TicketService{

}





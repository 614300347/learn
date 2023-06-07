package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.GameplayerMapper;
import com.example.demo.mapper.TicketMapper;
import com.example.demo.pojo.po.Gameplayer;
import com.example.demo.pojo.po.Ticket;
import com.example.demo.pojo.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author :Ligou
 * @date : 2023-6-6 18:38
 */
@Api(tags = "学习controller")
@RestController
@RequestMapping("/learn")
public class LearnController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private GameplayerMapper gameplayerMapper;

    @GetMapping("/test")
    public void test() {
        Page<Gameplayer> page = new Page<>(1, 10000);
        QueryWrapper queryWrapper = new QueryWrapper();
        Page page1 = gameplayerMapper.selectPage(page, null);
        List<Gameplayer> records = page1.getRecords();
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map<String, Gameplayer> collect = records.stream().collect(Collectors.toMap(gameplayer -> gameplayer.getId(), gameplayer -> gameplayer));
        hashOperations.putAll("gameplayer", collect);
    }

    @Autowired
    private TicketMapper ticketMapper;

    @GetMapping("/ticketToRedis")
    public ResponseVo ticketToRedis() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        List<Ticket> tickets = ticketMapper.selectList(null);
        Map<String, Ticket> collect = tickets.stream().collect(Collectors.toMap(ticket -> ticket.getType(), ticket -> ticket));
        collect.entrySet().stream().forEach(stringTicketEntry -> valueOperations.set("ticket"+stringTicketEntry.getKey(),stringTicketEntry.getValue()));


        ResponseVo responseVo = new ResponseVo();
        responseVo.setMes("chenggong");
        return responseVo;
    }

    @PutMapping("/createOrder/{type}/{num}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 300, message = "请求失败")
    })
    public ResponseVo createOrder(@PathVariable("type") String type, @PathVariable("num") Integer num) {
        ResponseVo responseVo = new ResponseVo();
        if ("".equals(type) || 0 == num) {
            responseVo.setCode(300);
            responseVo.setMes("请求失败");
            return responseVo;
        }
//        redisTemplate.watch("ticket");
//        redisTemplate.multi();
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        Ticket ticket = (Ticket) hashOperations.get("ticket", type);
//        Integer totalNum = ticket.getTotalNum();
//        if (totalNum >= num) {
//            ticket.setTotalNum(totalNum - num);
//        }
//        hashOperations.put("ticket",ticket.getType(),ticket);
//        List exec = redisTemplate.exec();
//        responseVo.setMes("成功");
//        responseVo.setCode(200);
//
        List<Object> results = (List<Object>) redisTemplate.execute(new SessionCallback<List<Object>>() {
            @SneakyThrows
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                // 监视key
                redisTemplate.watch("ticket"+type);
                Ticket ticket = (Ticket) operations.opsForValue().get("ticket"+type);
                Integer totalNum = ticket.getTotalNum();
                redisTemplate.multi();
                if (totalNum >= num) {
                    ticket.setTotalNum(totalNum - num);
                }
                Thread.sleep(10000);
                operations.opsForValue().set("ticket"+type,ticket);
                return operations.exec();
            }
        });
        System.out.println("results = " + results);
        return responseVo;
    }

    @PutMapping("/createOrder2/{type}/{num}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 300, message = "请求失败")
    })
    public ResponseVo createOrder2(@PathVariable("type") String type, @PathVariable("num") Integer num) {
        ResponseVo responseVo = new ResponseVo();
        if ("".equals(type) || 0 == num) {
            responseVo.setCode(300);
            responseVo.setMes("请求失败");
            return responseVo;
        }
//        redisTemplate.watch("ticket");
//        redisTemplate.multi();
        ValueOperations valueOperations = redisTemplate.opsForValue();
//        Ticket ticket = (Ticket) hashOperations.get("ticket", type);
//        Integer totalNum = ticket.getTotalNum();
//        if (totalNum >= num) {
//            ticket.setTotalNum(totalNum - num);
//        }
//        hashOperations.put("ticket",ticket.getType(),ticket);
//        List exec = redisTemplate.exec();
//        responseVo.setMes("成功");
//        responseVo.setCode(200);
//
        Ticket ticket = (Ticket) valueOperations.get("ticket"+type);
        Integer totalNum = ticket.getTotalNum();
//        redisTemplate.multi();
        if (totalNum >= num) {
            ticket.setTotalNum(totalNum - num);
        }
        valueOperations.set("ticket"+type,ticket);
        return responseVo;
    }
}

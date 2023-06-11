//package com.example.demo.controller;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.example.demo.annotation.MyLogFirst;
//import com.example.demo.dao.GameplayerMapper;
//import com.example.demo.mapper.TicketMapper;
//import com.example.demo.pojo.po.Gameplayer;
//import com.example.demo.pojo.po.Order;
//import com.example.demo.pojo.po.TestUserPo;
//import com.example.demo.pojo.po.Ticket;
//import com.example.demo.pojo.vo.ResponseVo;
//import com.example.demo.service.GameplayerService;
//import com.example.demo.service.TestUserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sun.jna.platform.mac.SystemB;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.SessionCallback;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
///**
// * @ClassName:TestController
// * @Author:LiGou
// * @Date:2023/5/4 14:33
// * @Version:1.0
// * @Description:
// */
//@RestController
//public class TestController {
//
//    @RequestMapping("/test")
//    @MyLogFirst
//    public void test(Integer id, String a) {
//        System.out.println("id = " + id);
//        testUserService.test();
//    }
//
//    @Autowired
//    TestUserService testUserService;
//
//
//    @Autowired
//    private GameplayerService gameplayerService;
//
//
//    @RequestMapping("/test2")
//    public void test2() {
//        QueryWrapper<Gameplayer> wrapper = new QueryWrapper<>();
//        wrapper.in("sex", "M");
//        List<Gameplayer> gameplayers = gameplayerService.list(wrapper);
//        System.out.println("gameplayers.size() = " + gameplayers.size());
//
//    }
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @GetMapping("/testMqAndRedis")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "成功"),
//            @ApiResponse(code = 300, message = "失败"),
//            @ApiResponse(code = 400, message = "下单失败")
//    })
//    public ResponseVo testMqAndRedis(String type, Integer num) throws JsonProcessingException {
//        ResponseVo responseVo = new ResponseVo();
//        if (type == null || num == null || num == 0) {
//            responseVo.setCode(300);
//            responseVo.setMes("失败");
//            return responseVo;
//        }
//        List<Object> result = (List<Object>) redisTemplate.execute(new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations operations) throws DataAccessException {
//                ValueOperations valueOperations = operations.opsForValue();
//                operations.watch(type);
//                Object ticketObj = valueOperations.get(type);
//                if (ticketObj == null) {
//                    responseVo.setCode(300);
//                    responseVo.setMes("失败");
//                    return responseVo;
//                }
//                Ticket ticket = (Ticket) ticketObj;
//                operations.multi();
//                if (ticket.getTotalNum() > num) {
//                    ticket.setTotalNum(ticket.getTotalNum() - num);
//                }
//                valueOperations.set(type, ticket);
//                return operations.exec();
//            }
//        });
//        if (result.size() > 0) {
//            responseVo.setCode(200);
//            responseVo.setMes("成功");
//            Order order = new Order();
//            order.setCreatorName(SecurityContextHolder.getContext().getAuthentication().getName());
//            order.setStauts("0");
//            ObjectMapper objectMapper = new ObjectMapper();
//            byte[] bytes = objectMapper.writeValueAsBytes(order);
//            Message message = new Message(bytes);
//            rabbitTemplate.convertAndSend("directExchange4", "test4", message);
//        } else {
//            responseVo.setCode(400);
//            responseVo.setMes("下单失败");
//        }
//        return responseVo;
//    }
//
//    @Autowired
//    private TicketMapper ticketMapper;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @GetMapping("/insertredis")
//    public void insertredis() {
//        List<Ticket> tickets = ticketMapper.selectList(null);
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        Map<String, Ticket> collect = tickets.stream().collect(Collectors.toMap(ticket -> ticket.getType(), ticket -> ticket));
//        valueOperations.multiSetIfAbsent(collect);
//    }
//
//
//    @GetMapping("/authtest1")
//    public ResponseVo authtest1() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        ResponseVo<Authentication> responseVo = new ResponseVo("ok",200,new Date(),authentication);
//        return responseVo;
//
//    }
//}

package com.yjy;

import com.yjy.entity.Order;
import com.yjy.mq.OrderProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderProduceTest {

    @Autowired
    private OrderProducer orderProducer;

    @Test
    public void testOrder() {
        Order order1 = new Order();
        Order order2 = new Order();
        Order order3 = new Order();
        Order order4 = new Order();
        order1.setOrderId("orderid_1");
        order2.setOrderId("orderid_2");
        order3.setOrderId("orderid_3");
        order4.setOrderId("orderid_4");

        order1.setOrderType("创建订单");
        orderProducer.sendMsgOrder(order1);

        order3.setOrderType("创建订单");
        orderProducer.sendMsgOrder(order3);

        order3.setOrderType("支付订单");
        orderProducer.sendMsgOrder(order3);

        order2.setOrderType("创建订单");
        orderProducer.sendMsgOrder(order2);

        order2.setOrderType("支付订单");
        orderProducer.sendMsgOrder(order2);

        order3.setOrderType("已发货");
        orderProducer.sendMsgOrder(order3);

        order2.setOrderType("已发货");
        orderProducer.sendMsgOrder(order2);

        order1.setOrderType("支付订单");
        orderProducer.sendMsgOrder(order1);

        order4.setOrderType("创建订单");
        orderProducer.sendMsgOrder(order4);

        order4.setOrderType("支付订单");
        orderProducer.sendMsgOrder(order4);

        order4.setOrderType("已发货");
        orderProducer.sendMsgOrder(order4);

        order1.setOrderType("已发货");
        orderProducer.sendMsgOrder(order1);
    }
}

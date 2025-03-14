package com.Inventory.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Inventory.entity.OrdersEntity;
import com.Inventory.service.OrderService;

import java.util.List;

@Controller
@RequestMapping("/Orders") // Base URL for all endpoints in this controller
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrders(Model model) {
        List<OrdersEntity> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders"; // Refers to orders.html in src/main/resources/templates
    }

    @GetMapping("/new")
    public String showOrderForm(Model model) {
        model.addAttribute("order", new OrdersEntity());
        return "order-form"; // A form page to add a new order
    }
}

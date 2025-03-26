package org.example;


import java.util.ArrayList;
import java.util.List;

public class OrderManager {

    private List<Order> orders;
    private NotificationService notificationService;

    public OrderManager(NotificationService notificationService) {
        this.orders = new ArrayList<>();
        this.notificationService = notificationService;
    }

    public void addOrder(Order order) {
        orders.add(order);
        notificationService.notify("Order added: " + order.getId());
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order searchById(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    public List<Order> searchByCustomerName(String customerName) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomerName().equals(customerName)) {
                result.add(order);
            }
        }
        return result;
    }

    public boolean removeOrder(String id) {
        return orders.removeIf(order -> order.getId().equals(id));
    }
}
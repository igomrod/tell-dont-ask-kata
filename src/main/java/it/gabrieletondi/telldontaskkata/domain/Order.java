package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus status;
    private int id;

    public Order() {
        this.status = OrderStatus.CREATED;
        this.items = new ArrayList<>();

        this.total = new BigDecimal("0.00");
        this.tax = new BigDecimal("0.00");
    }

    public Order(String currency, BigDecimal total, BigDecimal tax) {
        this();
        this.currency = currency;

        this.total = total;
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void ship() {
        this.status = OrderStatus.SHIPPED;
    }

    public void approve() {
        this.status = OrderStatus.APPROVED;
    }

    public void reject() {
        this.status = OrderStatus.REJECTED;
    }

    public void addItem(OrderItem orderItem) {
        this.items.add(orderItem);
        total = total.add(orderItem.getTaxedAmount());
        tax = tax.add(orderItem.getTax());
    }
}

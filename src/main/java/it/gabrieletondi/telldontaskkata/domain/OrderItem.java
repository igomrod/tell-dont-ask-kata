package it.gabrieletondi.telldontaskkata.domain;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal taxedAmount;
    private BigDecimal tax;

    public OrderItem() {

    }

    public OrderItem(Product product, int quantity) {
        this();

        this.product = product;
        this.quantity = quantity;

    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTaxedAmount() {
        return getUnitaryTaxedAmount().multiply(BigDecimal.valueOf(quantity)).setScale(2, HALF_UP);
    }

    private BigDecimal getUnitaryTaxedAmount() {
        return product.getPrice().add(getUnitaryTax()).setScale(2, HALF_UP);
    }

    private BigDecimal getUnitaryTax() {
        return product.getPrice().divide(valueOf(100)).multiply(product.getCategory().getTaxPercentage()).setScale(2, HALF_UP);
    }

    public BigDecimal getTax() {

        return getUnitaryTax().multiply(BigDecimal.valueOf(quantity));
    }

}

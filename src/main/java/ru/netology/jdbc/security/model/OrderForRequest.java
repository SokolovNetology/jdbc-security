package ru.netology.jdbc.security.model;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
public class OrderForRequest {
    private final Date date;
    private final int customer_id;
    private final String product_name;
    private final int amount;

    public OrderForRequest(String date, int customer_id, String product_name, int amount) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.customer_id = customer_id;
        this.product_name = product_name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date='" + date + '\'' +
                ", customer_id=" + customer_id +
                ", product_name='" + product_name + '\'' +
                ", amount=" + amount +
                '}';
    }
}

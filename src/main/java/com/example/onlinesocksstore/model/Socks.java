package com.example.onlinesocksstore.model;

import lombok.Data;

@Data
public class Socks {
    private Color color;
    private Size size;
    private int cotton;
    private int quantity;

    public Socks(Color color, Size size, int cotton, int quantity) {
        this.color = color;
        this.size = size;
        if (cotton > 0 && cotton < 100) {
            this.cotton = cotton;
        } else {
            throw new RuntimeException("Неверно введённые данные, введите данные в диапазоне от 0 до 100!");
        }
        if (quantity > 0) {
            this.quantity = quantity;
        } else {
            throw new RuntimeException("Неверно введённые данные, проверьте еще раз!");
        }
    }
}


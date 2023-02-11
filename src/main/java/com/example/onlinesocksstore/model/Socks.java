package com.example.onlinesocksstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    private Color color;
    private InternationalSockSize size;
    @Size(min = 1, max = 100, message = "Содержание хлопка должно быть 0 до 100")
    private Integer cotton;
    @Size(min = 22, max = 32, message = "Размер носков должен быть от 22,4 до 31,5")
    private Double RussianSockSize;
}

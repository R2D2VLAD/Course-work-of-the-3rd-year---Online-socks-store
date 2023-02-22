package com.example.onlinesocksstore.model;

import lombok.*;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Socks {
    private Color color;
    private SocksSize size;
    @Size(min = 1, max = 100, message = "Содержание хлопка должно быть 0 до 100")
    private Integer cotton;
}

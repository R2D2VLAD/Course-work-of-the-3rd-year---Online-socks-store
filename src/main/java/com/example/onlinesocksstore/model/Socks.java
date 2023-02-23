package com.example.onlinesocksstore.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Socks {
    @NotNull(message = "Поле должно быть заполнено!")
    private Color color;
    @NotNull(message = "Поле должно быть заполнено!")
    private SocksSize size;
    @Size(min = 1, max = 100, message = "Содержание хлопка должно быть 0 до 100")
    private Integer cotton;
}

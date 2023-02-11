package com.example.onlinesocksstore.controllers;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.InternationalSockSize;
import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.services.impl.SocksServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socks")
@RequiredArgsConstructor
@Tag(name = "Склад учета носков", description = "CRUD-операции для упраления складом")
public class SocksController {
    private final SocksServiceImpl socksService;
    @PostMapping("/POST")
    @Operation(
            summary = "Endpoint для добаления носков на склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось добавить приход!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса или они отсутствуют, проверьте данные еще раз!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Void> addSocks(@RequestParam(required = false, name = "Характеристики носков") Socks socks,
                                         @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.addSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/PUT")
    @Operation(
            summary = "Endpoint для отпуска носков на склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось произвести отпуск носков со склада!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Товара нет на складе в нужном количестве или " +
                            "есть ошибка в параметрах запроса, проверьте данные еще раз!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Void> releaseSocks(@RequestParam(required = false, name = "Характеристики носков") Socks socks,
                                             @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.releaseSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/DELETE")
    @Operation(
            summary = "Endpoint для списания испорченных носков")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен, товар списан со склада!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса или они отсутствуют, проверьте данные еще раз!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Void> deleteSocks(@RequestParam(required = false, name = "Характеристики носков") Socks socks,
                                            @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.deleteSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/GET")
    @Operation(
            summary = "Endpoint для информирования о оставшемся товаре на складе")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос успешно выполнен!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Есть ошибка в параметрах запроса или они отсутствуют, проверьте данные еще раз!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "404",
                    description = "URL неверный или такого действия в приложении нет!",
                    content = {
                            @Content(mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Во время выполнения запроса произошла ошибка на сервере!",
                    content = {
                            @Content(mediaType = "application/json")}),})
    public ResponseEntity<Integer> getAllSocks(@RequestParam(required = false, name = "Цвет носков") Color color,
                                               @RequestParam(required = false, name = "Размер носков") InternationalSockSize size,
                                               @RequestParam(required = false, name = "Минимальное количество хлопка") Integer cottonMin,
                                               @RequestParam(required = false, name = "Максимальное количество хлопка") Integer cottonMax) {
        Integer quanlity = socksService.getAllSocks(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok().body(quanlity);
    }
}


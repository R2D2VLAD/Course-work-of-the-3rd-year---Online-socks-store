package com.example.onlinesocksstore.controllers;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.SocksSize;
import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.services.impl.SocksServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/socks")
@RequiredArgsConstructor
@Tag(name = "Склад учета носков", description = "CRUD-операции для упраления складом")
public class SocksController {
    private final SocksServiceImpl socksService;

    @PostMapping
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
    public ResponseEntity<Void> addSocks(@Valid @RequestBody Socks socks,
                                         @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.addSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(
            summary = "Endpoint для отпуска носков на склад")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалось произвести отпуск носков со склада!",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class)))}),
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
    public ResponseEntity<Void> releaseSocks(@Valid @RequestBody Socks socks,
                                             @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.releaseSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Endpoint для списания испорченных носков")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Запрос выполнен, товар списан со склада!",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Socks.class)))}),
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
    public ResponseEntity<Void> deleteSocks(@Valid @RequestBody Socks socks,
                                            @RequestParam(required = false, name = "Количество носков") Integer numberPairs) {
        socksService.deleteSocks(socks, numberPairs);
        return ResponseEntity.ok().build();
    }

    @GetMapping
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
    public ResponseEntity<Integer> getAllSocks(@Valid @RequestParam(required = false, name = "Цвет носков") Color color,
                                               @Valid @RequestParam(required = false, name = "Размер носков") SocksSize size,
                                               @RequestParam(required = false, name = "Минимальное количество хлопка") Integer cottonMin,
                                               @RequestParam(required = false, name = "Максимальное количество хлопка") Integer cottonMax) {
        Integer quanlity = socksService.getAllSocks(color, size, cottonMin, cottonMax);
        if (quanlity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quanlity);
    }
}


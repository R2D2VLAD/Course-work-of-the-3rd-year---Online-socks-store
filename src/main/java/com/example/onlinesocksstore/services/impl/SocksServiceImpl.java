package com.example.onlinesocksstore.services.impl;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.SocksSize;
import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.services.SocksService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class SocksServiceImpl implements SocksService {

    private static final Map<Socks, Integer> socksStore = new HashMap<>();

    @Override
    public void addSocks(Socks socks, Integer numberPairs) {
        if (socksStore.containsKey(socks)) {
            socksStore.put(socks, socksStore.get(socks) + numberPairs);
        } else {
            socksStore.put(socks, numberPairs);
        }
    }

    @Override
    public void releaseSocks(Socks socks, Integer numberPairs) {
        writeAndSaleSocks(socks, numberPairs);
    }

    @Override
    public void deleteSocks(Socks socks, Integer numberPairs) {
        writeAndSaleSocks(socks, numberPairs);
    }

    @Override
    public Integer getAllSocks(Color color, SocksSize size, Integer cottonMin, Integer cottonMax) {
        int quantity = 0;
        for (Map.Entry<Socks, Integer> entry : socksStore.entrySet()) {
            Socks socks = entry.getKey();
            if (socks.getColor().equals(color)
                    && socks.getSize().equals(size)
                    && (cottonMin <= socks.getCotton())
                    && (cottonMax >= socks.getCotton())) {
                quantity += entry.getValue();
            }
        }
        return quantity;
    }

    private void writeAndSaleSocks(Socks socks, Integer numberPairs) {
        int totalNumberSocks = socksStore.getOrDefault(socks, 0);
        if (totalNumberSocks >= numberPairs) {
            socksStore.put(socks, totalNumberSocks - numberPairs);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Товара нет на складе в нужном количестве или " +
                    "есть ошибка в параметрах запроса, проверьте данные еще раз!");
        }
    }
}
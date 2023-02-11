package com.example.onlinesocksstore.services.impl;

import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.services.SocksService;
import org.springframework.stereotype.Service;

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
    public Integer getAllSocks(String color, String size, Integer cottonMin, Integer cottonMax) {
        var entryStream = socksStore.entrySet().stream()
                .filter(color != null ? s -> false : s -> true)
                .filter(size != null ? s -> false : s -> true)
                .filter(cottonMin != null ? s -> cottonMin <= s.getKey().getCotton() : s -> true)
                .filter(cottonMax != null ? s -> cottonMax >= s.getKey().getCotton() : s -> true);
        return Math.toIntExact(entryStream.count());
    }

    private void writeAndSaleSocks(Socks socks, Integer numberPairs) {
        int totalNumberSocks = socksStore.getOrDefault(socks, 0);
        if (totalNumberSocks >= numberPairs) {
            socksStore.put(socks, totalNumberSocks - numberPairs);
        } else {
            throw new RuntimeException("Носков нет!");
        }
    }
}
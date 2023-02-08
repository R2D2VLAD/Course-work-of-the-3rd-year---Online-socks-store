package com.example.onlinesocksstore.services.impl;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.model.Size;
import com.example.onlinesocksstore.services.SocksService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class SocksServiceImpl implements SocksService {
    private static final Map<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public void addSocks(Socks socks) {
        if (socksMap.containsKey(socks)) {
            socksMap.put(socks, socksMap.get(socks) + socks.getQuantity());
        } else {
            socksMap.put(socks, socks.getQuantity());
        }
    }

    @Override
    public void releaseSocks(Socks socks) {
        writeAndSaleSocks(socks);
    }

    @Override
    public void deleteSocks(Socks socks) {
        writeAndSaleSocks(socks);
    }

    @Override
    public long getAllSocks(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        Stream<Map.Entry<Socks, Integer>> entryStream;
        entryStream = socksMap.entrySet().stream()
                .filter(color != null ? s -> color.equals(s.getKey().getColor()) : s -> true)
                .filter(size != null ? s -> size.equals(s.getKey().getSize()) : s -> true)
                .filter(cottonMin != null ? s -> cottonMin <= s.getKey().getCotton() : s -> true)
                .filter(cottonMax != null ? s -> cottonMax >= s.getKey().getCotton() : s -> true);
        return entryStream.count();
    }

    private void writeAndSaleSocks(Socks socks) {
        int totalNumberSocks = socksMap.getOrDefault(socks, 0);
        if (totalNumberSocks >= socks.getQuantity()) {
            socksMap.put(socks, totalNumberSocks - socks.getQuantity());
        } else {
            throw new RuntimeException("Носков нет!");
        }
    }
}
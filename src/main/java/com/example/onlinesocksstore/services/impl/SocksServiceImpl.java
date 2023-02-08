package com.example.onlinesocksstore.services.impl;

import com.example.onlinesocksstore.SocksTwoService;
import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.SocksOne;
import com.example.onlinesocksstore.model.SocksTWO;
import com.example.onlinesocksstore.model.Size;
import com.example.onlinesocksstore.services.SocksOneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksOneService {
    private static final Map<SocksOne, Integer> socksMap = new HashMap<>();

    private final SocksTwoService socksTwoService;
    @Override
    public void addSocks(SocksTWO socks) {
        SocksOne socksOne = socksTwoService.addSocks(socks);
        if (socksMap.containsKey(socksOne)) {
            socksMap.put(socksOne, socksMap.get(socksOne) + socks.getQuantity());
        } else {
            socksMap.put(socksOne, socks.getQuantity());
        }
    }

    @Override
    public void releaseSocks(SocksTWO socks) {
        writeAndSaleSocks(socks);
    }

    @Override
    public void deleteSocks(SocksTWO socks) {
        writeAndSaleSocks(socks);
    }

    @Override
    public long getAllSocks(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        Stream<Map.Entry<SocksOne, Integer>> entryStream;
        entryStream = socksMap.entrySet().stream()
                .filter(color != null ? s -> color.equals(s.getKey().getColor()) : s -> true)
                .filter(size != null ? s -> size.equals(s.getKey().getSize()) : s -> true)
                .filter(cottonMin != null ? s -> cottonMin <= s.getKey().getCotton() : s -> true)
                .filter(cottonMax != null ? s -> cottonMax >= s.getKey().getCotton() : s -> true);
        return entryStream.count();
    }

    private void writeAndSaleSocks(SocksTWO socks) {
        SocksOne socksOne = socksTwoService.addSocks(socks);
        int totalNumberSocks = socksMap.getOrDefault(socksOne, 0);
        if (totalNumberSocks >= socks.getQuantity()) {
            socksMap.put(socksOne, totalNumberSocks - socks.getQuantity());
        } else {
            throw new RuntimeException("Носков нет!");
        }
    }
}
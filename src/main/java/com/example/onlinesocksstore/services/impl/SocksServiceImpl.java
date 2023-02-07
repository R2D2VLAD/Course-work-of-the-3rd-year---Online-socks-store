package com.example.onlinesocksstore.services.impl;

import com.example.onlinesocksstore.model.Socks;
import com.example.onlinesocksstore.services.SocksService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SocksServiceImpl implements SocksService {
    private static final Map<Long, Socks> socksMap = new HashMap<>();

    public static long id = 0;


    @Override
    public long addSocks(Socks socks) {
        socksMap.put(id, socks);
        return id++;
    }

    @Override
    public Socks releaseSocks(long id, Socks socks) {
        if (socksMap.containsKey(id)) {
            socksMap.put(id, socks);
            return socks;
        }
        return null;
    }

    @Override
    public boolean deleteSocks(long id, Socks socks) {
        if (socksMap.containsKey(id)) {
            socksMap.remove(id, socks);
            return true;
        }
        return false;
    }

    @Override
    public Collection<Socks> getAllSocks() {
        return socksMap.values();
    }
}
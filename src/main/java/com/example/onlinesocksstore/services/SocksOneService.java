package com.example.onlinesocksstore.services;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.Size;
import com.example.onlinesocksstore.model.SocksTWO;

public interface SocksOneService {

    void addSocks(SocksTWO socks);

    void releaseSocks(SocksTWO socks);

    void deleteSocks(SocksTWO socks);

    long getAllSocks(Color color, Size size, Integer cottonMin, Integer cottonMax);
}


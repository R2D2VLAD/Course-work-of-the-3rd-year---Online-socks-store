package com.example.onlinesocksstore.services;

import com.example.onlinesocksstore.model.Color;
import com.example.onlinesocksstore.model.Size;
import com.example.onlinesocksstore.model.Socks;

public interface SocksService {

    void addSocks(Socks socks);

    void releaseSocks(Socks socks);

    void deleteSocks(Socks socks);

    long getAllSocks(Color color, Size size, Integer cottonMin, Integer cottonMax);
}


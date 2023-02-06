package com.example.onlinesocksstore.services;

import com.example.onlinesocksstore.model.Socks;

import java.util.Collection;

public interface SocksService {
    long addSocks(Socks socks);

    Socks vacationSocks(long id, Socks socks);

    boolean deleteSocks(long id, Socks socks);

    Collection<Socks> getAllSocks();
}


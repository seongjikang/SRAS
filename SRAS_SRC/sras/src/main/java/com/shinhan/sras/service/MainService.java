package com.shinhan.sras.service;

import java.util.List;

import com.shinhan.sras.mapper.MainMapper;
import com.shinhan.sras.model.Sras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private MainMapper mainMapper;

    public List<Sras> getUserList() throws Exception {

        return mainMapper.getSrasList();
    }
}


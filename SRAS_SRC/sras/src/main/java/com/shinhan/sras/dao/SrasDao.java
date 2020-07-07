package com.shinhan.sras.dao;

import com.shinhan.sras.model.Sras;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SrasDao {
    public List<Sras> selectReviewer();
}

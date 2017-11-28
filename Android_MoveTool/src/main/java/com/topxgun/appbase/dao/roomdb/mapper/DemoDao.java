package com.topxgun.appbase.dao.roomdb.mapper;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.topxgun.appbase.dao.roomdb.table.DemoTb;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by TOPXGUN on 2017/7/7.
 */
@Dao
public interface DemoDao {
    @Insert(onConflict = REPLACE)
    void save(DemoTb user);
    @Query("SELECT * FROM flight_data WHERE id = :flightId LIMIT 1")
    DemoTb load(String flightId);
}

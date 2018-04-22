package com.jwding.appbase.dao.roomdb.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.jwding.appbase.dao.roomdb.mapper.DemoDao;
import com.jwding.appbase.dao.roomdb.table.DemoTb;

/**
 * Created by TOPXGUN on 2017/7/7.
 */

@Database(entities = {DemoTb.class}, version = 1)
public abstract class DemoDb extends RoomDatabase {
    public abstract DemoDao flightDataDao();
}

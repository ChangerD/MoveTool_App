package com.topxgun.appbase.dao.roomdb.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.topxgun.appbase.dao.roomdb.mapper.DemoDao;
import com.topxgun.appbase.dao.roomdb.table.DemoTb;

/**
 * Created by TOPXGUN on 2017/7/7.
 */

@Database(entities = {DemoTb.class}, version = 2)
public abstract class DemoDb extends RoomDatabase {
    public abstract DemoDao flightDataDao();
}

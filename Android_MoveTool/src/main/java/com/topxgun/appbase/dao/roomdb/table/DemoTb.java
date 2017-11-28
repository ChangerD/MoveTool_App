package com.topxgun.appbase.dao.roomdb.table;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.topxgun.appbase.dao.roomdb.table.DemoTb.TABLE_NAME;

/**
 * Created by TOPXGUN on 2017/7/7.
 */
@Entity(tableName = TABLE_NAME)
public class DemoTb {
    public static final String TABLE_NAME = "flight_data";
    @PrimaryKey
    private String id;
    private String path;
    private String flightGpsData;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFlightGpsData() {
        return flightGpsData;
    }

    public void setFlightGpsData(String flightGpsData) {
        this.flightGpsData = flightGpsData;
    }
}

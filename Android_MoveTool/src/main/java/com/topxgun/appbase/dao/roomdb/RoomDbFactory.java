package com.topxgun.appbase.dao.roomdb;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import java.util.HashMap;

/**
 * Created by JWDING on 2017/8/6.
 */

/**
 * RoomDb工厂
 *
 */
public class RoomDbFactory{

    private static RoomDbFactory roomDbFactory = null;

    public static RoomDbFactory getInstance() {
        if (roomDbFactory == null) {
            roomDbFactory = new RoomDbFactory();
        }
        return roomDbFactory;
    }

    private HashMap<String, RoomDatabase> dbMap;

    public <D extends RoomDatabase> D getDatabase(Context context, Class<D> cls) {
        if (null == dbMap) {
            dbMap = new HashMap();
        }
        if (null != cls && dbMap.containsKey(cls.getName())) {
            return cls.cast(dbMap.get(cls.getName()));
        } else if (context != null && cls != null) {
            dbMap.put(cls.getName(), Room.databaseBuilder(context, cls, cls.getName())
                    .allowMainThreadQueries()
                    .build());
            return cls.cast(dbMap.get(cls.getName()));
        } else {
            return null;
        }
    }

}

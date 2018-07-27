package com.demo.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.demo.dao.MyDao
import com.demo.entity.Result


@Database(entities=[(Result::class)],version = 1,exportSchema = false)
abstract class MyAppDatabase: RoomDatabase() {
    abstract fun myDao(): MyDao
}
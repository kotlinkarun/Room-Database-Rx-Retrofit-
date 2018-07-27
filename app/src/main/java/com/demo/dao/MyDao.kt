package com.demo.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.demo.entity.Result

@Dao
interface MyDao{

    @Insert
    fun addEmp(emp: Result)


    @Query("select * from employee")
    fun showAll():List<Result>

    @Query("DELETE FROM employee")
    fun delete()

/*    @Delete
    fun deletByID(user:User)

    @Update
    fun updateByID(user:User)*/




}

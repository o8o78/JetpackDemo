package com.byteroll.dao

import androidx.room.*
import com.byteroll.entity.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long

    @Update
    fun updateUser(newUser: User)

    @Query("select * from User")
    fun loadAllUsers(): List<User>

    @Query("select * from User where age > :age")
    fun loadUserOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)

    @Query("delete from user where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}
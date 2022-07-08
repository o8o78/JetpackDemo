package com.byteroll.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.byteroll.dao.UserDao
import com.byteroll.entity.User
import com.byteroll.utils.ConstUtil

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, ConstUtil.KEY_DATABASE_NAME_APP_DATABASE)
                .build().apply {
                    instance = this
                }
        }

    }

}
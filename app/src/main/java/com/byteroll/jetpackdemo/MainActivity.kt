package com.byteroll.jetpackdemo

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.byteroll.database.AppDatabase
import com.byteroll.entity.User
import com.byteroll.jetpackdemo.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        test()
    }

    private fun init(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun test(){
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        binding.add.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        binding.update.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        binding.delete.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
        binding.query.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()){
                    Log.d("MainActivity", user.toString())
                }
            }
        }
    }

}
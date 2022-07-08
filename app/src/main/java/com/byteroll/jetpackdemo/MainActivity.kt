package com.byteroll.jetpackdemo

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.byteroll.jetpackdemo.databinding.ActivityMainBinding
import com.byteroll.jetpackdemo.observer.MyObserver
import com.byteroll.jetpackdemo.utils.ConstUtil
import com.byteroll.jetpackdemo.viewmodle.MainViewModel
import com.byteroll.jetpackdemo.viewmodle.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var sp: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        test()
    }

    private fun init(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(ConstUtil.KEY_COUNT_RESERVED, 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        lifecycle.let {
            it.addObserver(MyObserver(it))
        }
    }

    private fun test(){
        binding.increase.setOnClickListener {
            viewModel.plusOne()
        }
        binding.clear.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this) { count->
            binding.result.text = count.toString()
        }
        refreshCounter()
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt(ConstUtil.KEY_COUNT_RESERVED, viewModel.counter.value ?: 0)
        }
    }

    private fun refreshCounter(){
        binding.result.text = viewModel.counter.toString()
    }

}
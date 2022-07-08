package com.byteroll.jetpackdemo.viewmodle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.byteroll.jetpackdemo.bean.Repository
import com.byteroll.jetpackdemo.bean.User

class MainViewModel(countReserved: Int) : ViewModel(){

    val counter: LiveData<Int> get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne(){
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear(){
        _counter.value = 0
    }

    private var userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData){
        user->
        "${user.firstName}, ${user.lastName}"
    }

    fun getUser(userId: String): LiveData<User>{
        return Repository.getUser(userId)
    }

    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData){
        userId->
        Repository.getUser(userId)
    }

    fun getUser(userId:String){
        userIdLiveData.value = userId
    }

}
package com.byteroll.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.byteroll.entity.Book

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>

}
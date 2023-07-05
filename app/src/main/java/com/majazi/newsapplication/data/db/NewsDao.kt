package com.majazi.newsapplication.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.trendingnews.Post
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(data:List<Data>)
//
//    @Query("DELETE FROM ")
//    suspend fun deleteAllNews()
//
    @Query("SELECT * FROM news_list WHERE categoryId LIKE :categoryId")
     fun getNewsList(categoryId:String): List<Data>

     @Query("SELECT * FROM news_list WHERE isSave LIKE '1'")
     fun getSavedNews(): Flow<List<Data>>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun saveNewsToSaved(data: Data)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCategoryHomePage(itemNews: List<ItemNews>)


    @Query("SELECT * FROM category_list_home_page")
    fun getCategoryList(): List<ItemNews>

//
//    @Query("SELECT * FROM category_list_home_page")
//    fun getCategoryList(): List<ItemNews>


    @Delete
    suspend fun deleteNews(data: Data)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTrendingNews(data: List<Post>)

    @Query("SELECT * FROM trending_news")
    fun getTradingNewsFromDb():List<Post>

}
package com.majazi.newsapplication.domien.repository

import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.Resource2
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews():Resource2<ItemNews>

    suspend fun getListNews(catId:String):Resource<NewsList>
    suspend fun getDetailNews(id:String):Resource<DetailNews>

    suspend fun saveNews(data: Data)

    suspend fun getNewsFromDb():Flow<List<Data>>

    suspend fun getNewsFromSearch(search:String):Resource<Search>

    suspend fun getComment(id: String): Resource<Comment>


}
package com.majazi.newsapplication.data.repository.news

import android.util.Log
import com.majazi.newsapplication.data.model.detailnews.DetailNews
import com.majazi.newsapplication.data.model.detailnews.comment.Comment
import com.majazi.newsapplication.data.model.homenews.HomeNews
import com.majazi.newsapplication.data.model.homenews.ItemNews
import com.majazi.newsapplication.data.model.newslist.Data
import com.majazi.newsapplication.data.model.newslist.NewsList
import com.majazi.newsapplication.data.model.search.Search
import com.majazi.newsapplication.data.model.trendingnews.TrendingNews
import com.majazi.newsapplication.data.repository.news.datasource.NewsLocalDataSource
import com.majazi.newsapplication.data.repository.news.datasource.NewsRemoteDataSource
import com.majazi.newsapplication.data.utils.Resource
import com.majazi.newsapplication.data.utils.Resource2
import com.majazi.newsapplication.domien.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNews(): Resource2<ItemNews> {
            return Resource2.Success(getCategoryFromDb())
    }

    override suspend fun getListNews(catId:String): Resource<NewsList> {
        return responseToResourceNewsList(remoteDataSource.getNewsList(catId))
    }

    override suspend fun getDetailNews(id: String): Resource<DetailNews> {
        return responseToResourceDetailNews(remoteDataSource.getDetailNews(id))
    }


    override suspend fun saveNews(data: Data) {
        localDataSource.saveNewsToDB(data)
    }

    override suspend fun getNewsFromDb(): Flow<List<Data>> {
        return localDataSource.getNewsFromDb()
    }

    override suspend fun getNewsFromSearch(search: String): Resource<Search> {
        return responseToResourceSearch(remoteDataSource.getNewsFromSearch(search))
    }


    override suspend fun getComment(id: String): Resource<Comment> {
        return responseToResourceComment(remoteDataSource.getComment(id))
    }

    override suspend fun deleteNews(data: Data) {
        localDataSource.deleteNews(data)
    }

    override suspend fun getTrendingNews(): Resource<TrendingNews> {
        return responseToResourceTrending(remoteDataSource.getTrendingNews())
    }


    private fun responseToResourceTrending(response: Response<TrendingNews>):Resource<TrendingNews>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())

    }


    private fun responseToResourceNewsList(response: Response<NewsList>):Resource<NewsList>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    private fun responseToResourceDetailNews(response: Response<DetailNews>):Resource<DetailNews>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceSearch(response: Response<Search>):Resource<Search>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    private fun responseToResourceComment(response: Response<Comment>):Resource<Comment>{
        if (response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }


    suspend fun getCategoryNewsFromApi():List<ItemNews>{
        lateinit var categoryList:List<ItemNews>
        try {
            val response = remoteDataSource.getNews()
            val body = response.body()
            if (body!=null){
                categoryList = body.data
            }
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        return categoryList
    }


    suspend fun getCategoryFromDb():List<ItemNews>{
        lateinit var categoryList:List<ItemNews>
        try {
           categoryList = localDataSource.getCategoryFromDb()
        }catch (e:Exception){
            Log.i("TAG", "getCategoryNewsFromApi: ${e.message}")
        }
        if (categoryList.size>0){
            return categoryList
        }else{
            categoryList =getCategoryNewsFromApi()
            localDataSource.saveCategoryToDb(categoryList)

        }
        return categoryList
    }
}
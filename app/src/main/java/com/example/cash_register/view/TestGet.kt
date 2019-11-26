package com.example.cash_register.view

//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.example.cash_register.R
//import com.example.cash_register.dto.ArticleDto
//import com.example.cash_register.dto.UserAuthenticationDto
//import com.example.cash_register.services.ArticlesService
//import com.example.cash_register.network.HttpClient
//import com.example.cash_register.services.NetworkHttp
//import com.example.cash_register.services.UserService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response

//class TestGet : AppCompatActivity(){


//    private lateinit var nwhttp :HttpClient
//    private val articleService = nwhttp.instanceRetro()?.create(ArticlesService::class.java)
//    private val userService = nwhttp.instanceRetro()?.create(UserService::class.java)


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test_get)
//    }
//
//    fun getArticles(){
//        val courseRequest = articleService?.listArticles()
//
//        courseRequest?.enqueue(object : Callback<List<ArticleDto>> {
//            override fun onResponse(call: Call<List<ArticleDto>>, response: Response<List<ArticleDto>>) {
//                val allArticles = response.body()
//                if (allArticles != null) {
//                    for (article in allArticles)
//                        Toast.makeText(applicationContext, " one article : ${article.name} : ${article.price} ", Toast.LENGTH_SHORT).show()
//                 }
//            }
//            override fun onFailure(call: Call<List<ArticleDto>>, t: Throwable) {
//                error("KO")
//            }
//        })
//    }

//     fun getUser(){
//        val courseRequest = userService?.getUser()
//
//        courseRequest?.enqueue(object : Callback<List<UserAuthenticationDto>> {
//            override fun onResponse(call: Call<List<UserAuthenticationDto>>, response: Response<List<UserAuthenticationDto>>) {
//                val users = response.body()
//                if (users != null) {
//                    for (user in users)
//                    Toast.makeText(
//                        applicationContext,
//                        " user : ${user.username} ",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                }
//            }
//            override fun onFailure(call: Call<List<UserAuthenticationDto>>, t: Throwable) {
//                error("KO")
//            }
//        })
//    }



////    fun postUser(){
////        val courseRequest = service?.listArticles()
////
////        courseRequest?.enqueue(object : Callback<List<ArticleDto>> {
////            override fun onResponse(call: Call<List<ArticleDto>>, response: Response<List<ArticleDto>>) {
////                val allArticles = response.body()
////
////            }
////            override fun onFailure(call: Call<List<ArticleDto>>, t: Throwable) {
////                error("KO")
////            }
////        })
////    }
//}

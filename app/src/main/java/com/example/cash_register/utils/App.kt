package com.example.cash_register.utils
//
//import javax.xml.datatype.DatatypeConstants.SECONDS
//import okhttp3.OkHttpClient
//import com.google.gson.Gson
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.Retrofit
//import android.R.attr.password
//import android.app.Application
//
//
//class App : Application() {
//    private var session: Session? = null
//    private var apiService: ApiService? = null
//    private var authenticationListener: AuthenticationListener? = null
//
//    override fun onCreate() {
//        super.onCreate()
//    }
//
//    // use a storage option to store the
//    // credentails and user info
//    // i.e: SQLite, SharedPreference etc.
//    fun getSession(): Session {
//        if (session == null) {
//            session = object : Session() {
//                // check if token exist or not
//                // return true if exist otherwise false
//                // assuming that token exists
//                val isLoggedIn: Boolean
//                    get() = true
//
//                // return the token that was saved earlier
//                val token: String
//                    get() = token
//
//                val email: String
//                    get() = email
//
//                // decrypt and return
//                val password: String
//                    get() = password
//
//                fun saveToken(token: String) {
//                    // save the token
//                }
//
//                fun saveEmail() {
//                    // save user's email
//                }
//
//                fun savePassword() {
//                    // encrypt and save
//                }
//
//                fun invalidate() {
//                    // get called when user become logged out
//                    // delete token and other user info
//                    // (i.e: email, password)
//                    // from the storage
//
//                    // sending logged out event to it's listener
//                    // i.e: Activity, Fragment, Service
//                    if (authenticationListener != null) {
//                        authenticationListener!!.onUserLoggedOut()
//                    }
//                }
//            }
//        }
//
//        return session
//    }
//
//    interface AuthenticationListener {
//        fun onUserLoggedOut()
//    }
//
//    fun setAuthenticationListener(listener: AuthenticationListener) {
//        this.authenticationListener = listener
//    }
//
//    fun getApiService(): ApiService {
//        if (apiService == null) {
//            apiService =
//                provideRetrofit(ApiService.URL).create<ApiService>(ApiService::class.java!!)
//        }
//        return apiService
//    }
//
//    private fun provideRetrofit(url: String): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(url)
//            .client(provideOkHttpClient())
//            .addConverterFactory(GsonConverterFactory.create(Gson()))
//            .build()
//    }
//
//    private fun provideOkHttpClient(): OkHttpClient {
//        val okhttpClientBuilder = OkHttpClient.Builder()
//        okhttpClientBuilder.connectTimeout(30, TimeUnit.SECONDS)
//        okhttpClientBuilder.readTimeout(30, TimeUnit.SECONDS)
//        okhttpClientBuilder.writeTimeout(30, TimeUnit.SECONDS)
//        return okhttpClientBuilder.build()
//    }
//}
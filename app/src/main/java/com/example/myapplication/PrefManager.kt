package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

class PrefManager (context : Context?) {

    //shared pref mode
    val PRIVATE_MODE = 0
    //Shared pref file name
    private val PREF_NAME = "SharedPreference"
    private val IS_LOGIN = "is_login"

    val pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
    val editor: SharedPreferences.Editor? = pref?.edit()

    fun setLogin(isLogin :Boolean){
        editor?.putBoolean(IS_LOGIN,isLogin)
        editor?.commit()
    }

    fun setUserData(name: String?,email: String?,phone:String?,address: String?,pw:String){
        editor?.putString("name",name)
        editor?.putString("email",email)
        editor?.putString("phone",phone)
        editor?.putString("address",address)
        editor?.putString("pw",pw)
        editor?.commit()
    }

    fun setUsername(username: String?){
        editor?.putString("username",username)//NIC
        editor?.commit()
    }

    fun setName(name: String?){
        editor?.putString("name",name)
        editor?.commit()
    }

    fun isLogin(): Boolean?{
        return pref?.getBoolean(IS_LOGIN,false)
    }

    fun getUsername(): String?{
        return pref?.getString("username","")
    }

    fun getName(): String?{
        return pref?.getString("name","")
    }
    fun getEmail(): String?{
        return pref?.getString("email","")
    }
    fun getPhone(): String?{
        return pref?.getString("phone","")
    }
    fun getAddress(): String?{
        return pref?.getString("address","")
    }
    fun getPw(): String?{
        return pref?.getString("pw","")
    }

    fun removeData(){
        editor?.clear()
        editor?.commit()
    }
}
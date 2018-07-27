package com.demo

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.demo.adapter.AccountAdapter
import com.demo.database.MyAppDatabase
import com.demo.entity.Result
import com.demo.restservices.APIService
import com.demo.restservices.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   private lateinit var myAppDatabase:MyAppDatabase
   private lateinit var list:ArrayList<Result>

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var apiServices: APIService
    private lateinit var context: AppCompatActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this@MainActivity
        apiServices = ApiUtils.apiService

        list= ArrayList()

        rv_animal_list.layoutManager = LinearLayoutManager(this)


        myAppDatabase=Room.databaseBuilder(applicationContext,
                MyAppDatabase::class.java, "userdbs").allowMainThreadQueries().build()

           initJson()
    }



    private fun initJson() {
        list.clear()
        progress.visibility=View.VISIBLE
        val m= Result()
        apiServices.getCategoryShopAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            Log.d(TAG,"Res: ${it.result}")
                            myAppDatabase.myDao().delete()
                            for(r: Result in it.result){
                                m.id=r.id
                                m.accountName=r.accountName
                                m.acountNo=r.acountNo
                                m.mobile=r.mobile
                                m.address=r.address
                                m.acType=r.acType
                              addOffline(m)
                            }
                            progress.visibility=View.GONE
                            ShowData()
                        },
                        { error ->
                            progress.visibility=View.GONE
                            Log.d(TAG,"ERROR ${error.message }")
                            ShowData()
                        }
                )
    }

    private fun addOffline(list: Result) {

       myAppDatabase.myDao().addEmp(list)

    }

    private fun ShowData(){
        val l=myAppDatabase.myDao().showAll()
        for(ll in l){
            list.add(ll)
        }
        rv_animal_list.adapter = AccountAdapter(list)
    }

}

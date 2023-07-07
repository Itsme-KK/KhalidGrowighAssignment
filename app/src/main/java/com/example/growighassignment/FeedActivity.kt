package com.example.growighassignment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.growighassignment.databinding.ActivityFeedBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections

class FeedActivity : AppCompatActivity() {
    private var binding: ActivityFeedBinding? = null
    private var mProgressDialog: Dialog? = null
    private var BASE_URL = "https://api.github.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.feedRecyclerView?.setHasFixedSize(true)

        getAllData()

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            getAllData()
            binding?.swipeRefreshLayout?.isRefreshing = false
        }
    }

    private fun getAllData() {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create(APIDataInterface::class.java)

        val retroData = retrofit.getData()

        showCustomProgressDialog()

        retroData.enqueue(object : Callback<List<ImageData>> {
            override fun onResponse(
                call: Call<List<ImageData>>, response: Response<List<ImageData>>
            ) {
                hideProgressDialog()
                val data = response.body()

                val adapter = FeedAdapter(data!!)
                Collections.shuffle(data)

                binding?.feedRecyclerView?.adapter = adapter

                Log.i("DATA : ", data.toString())
            }

            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
                Log.e("ERROOORRRR!", t.message.toString())
                Toast.makeText(this@FeedActivity, t.message.toString(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showCustomProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog?.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null) mProgressDialog?.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
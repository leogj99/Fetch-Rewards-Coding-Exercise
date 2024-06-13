package com.example.fetchexercise.data_display

import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import com.example.fetchexercise.R
import com.example.fetchexercise.data_display.ExpandableListAdapter
import com.example.fetchexercise.data_display.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataDisplay : AppCompatActivity() {

    private val baseUrl = "https://fetch-hiring.s3.amazonaws.com/"
    private val records = mutableListOf<Record>()
    private lateinit var expandableList: ExpandableListView
    private lateinit var expandableListAdapter: ExpandableListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_display)

        expandableList = findViewById(R.id.expandable_list)
        getAllData()
    }

    private fun getAllData() { // Collects the data from the Url
        val api = Retrofit.Builder() // Initializes Retrofit instance
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitAPI::class.java)

        api.getData().enqueue(object : Callback<List<Record>> { // Makes HTTP request to fetch data from the API
            override fun onResponse(call: Call<List<Record>>, response: Response<List<Record>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        for (record in it) {
                            if (!record.name.isNullOrEmpty()) { // Only adds non-null or empty record to list
                                records.add(record)
                            }
                        }
                        sortData() // Modifies data and set adapter after successful data collection
                    }
                }
            }

            override fun onFailure(call: Call<List<Record>>, t: Throwable) {
                Log.i("Record", "Data: ERROR")
            }
        })
    }

    private fun sortData() {
        val groupsById = records.groupBy { it.listId }      // Group the records by listId
        val groupList = groupsById.keys.sorted()    // Gets the group numbers and sorts them

        val childList = groupList.map { listIdNumber ->   // Create a list of child items for each group
            // Sorts the records of each group based on the names
            // Modifies the list to only contain the name property
            groupsById[listIdNumber]?.sortedBy { it.name }?.map { it.name }
        }
        setAdapter(groupList, childList) // Passes the data to the expandable list
    }

    private fun setAdapter(groupList: List<String>, childList:  List<List<String>?>){
        expandableListAdapter = ExpandableListAdapter(this, groupList, childList)  // Create adapter
        expandableList.setAdapter(expandableListAdapter)   // Set adapter
    }


}

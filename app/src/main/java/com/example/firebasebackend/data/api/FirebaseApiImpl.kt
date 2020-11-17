package com.example.firebasebackend.data.api

import androidx.lifecycle.MutableLiveData
import com.example.firebasebackend.BuildConfig.FIREBASE_URL
import com.example.firebasebackend.data.model.NewsItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

interface FirebaseApi {
    fun loadData(): MutableLiveData<ArrayList<NewsItem>>
}

class FirebaseApiImpl : FirebaseApi {

    override fun loadData(): MutableLiveData<ArrayList<NewsItem>> {
        val data = MutableLiveData<ArrayList<NewsItem>>()

        val database = Firebase.database
        val ref = database.getReferenceFromUrl(FIREBASE_URL)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list: ArrayList<NewsItem>? = arrayListOf()
                for (postSnapshot in snapshot.children) {
                    val point = postSnapshot.getValue(NewsItem::class.java)
                    point?.let { list?.add(it) }
                }
                data.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                data.postValue(null)
            }
        })

        return data
    }
}


package com.example.myapplication.creator

import com.example.myapplication.data.RetrofitNetworkClient
import com.example.myapplication.data.TracksRepositoryImpl
import com.example.myapplication.domain.api.TracksRepository

object Creator {

    fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(
            RetrofitNetworkClient(Storage())
        )
    }
}
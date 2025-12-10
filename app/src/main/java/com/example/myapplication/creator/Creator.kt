package com.example.myapplication.creator




import com.example.myapplication.data.RetrofitNetworkClient
import com.example.myapplication.data.TracksRepositoryImpl
import com.example.myapplication.domain.api.TracksRepository

object Creator {
    private fun getTracksRepository(): TracksRepository {
        return TracksRepositoryImpl(RetrofitNetworkClient(Storage()))
    }

    fun provideTracksRepository(): TracksRepository {
        return getTracksRepository()
    }
}

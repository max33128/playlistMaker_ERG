package com.example.myapplication.data.network

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}
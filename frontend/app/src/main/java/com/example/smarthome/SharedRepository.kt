package com.example.smarthome

import com.google.gson.JsonObject
class SharedRepository {

    suspend fun getCommandList(token:String):ArrayList<Command>?{
        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).getCommandList(token)

        if (request.isSuccessful){
            return request.body()!!
        }

        return null

    }


    suspend fun getIntentList(token: String):ArrayList<IntentClass>?{
        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).getIntentList(token)

        if (request.isSuccessful){
            return request.body()!!
        }

        return null
    }

    suspend fun getDetailIntent(token:String, slug:String):IntentClass?{
        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).getDetailIntent(token, slug)
        if (request.isSuccessful){
            return request.body()!!
        }

        return null
    }

    suspend fun postChat(chat:String):String?{
        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).postChat(chat)

        if (request.isSuccessful){
            return request.body()!!
        }

        return null

    }

    // Get sensor data list
    suspend fun getSensorData(token: String): ArrayList<SensorData>? {
        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).getSensorData(token)

        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getWeatherForecast():JsonObject?{
        val request = ServiceBuilder.getInstance().create(APIInterface::class.java).getWeatherForecast()

        if (request.isSuccessful){
            return request.body()!!
        }

        return null
    }

//    suspend fun postCommand(command: Command):String?{
//        val request = ServiceBuilder.getretrofit().create(APIInterface::class.java).postCommand(command)
//
//        if (request.isSuccessful){
//            return request.body()!!
//        }
//
//        return null
//    }

//    val client = HttpClient(CIO){
//        install(WebSockets){
//            pingInterval = 20_000
//        }
//    }

}

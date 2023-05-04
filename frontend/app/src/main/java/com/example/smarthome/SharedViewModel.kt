package com.example.smarthome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import okhttp3.WebSocket

class SharedViewModel:ViewModel() {
    private val repository = SharedRepository()

    private val _arrayListLiveData = MutableLiveData<ArrayList<Command>>()
    val arrayListLiveData:LiveData<ArrayList<Command>> = _arrayListLiveData

    private val _arrayListLiveDataIntent = MutableLiveData<ArrayList<IntentClass>>()
    val arrayListLiveDataIntent:LiveData<ArrayList<IntentClass>> = _arrayListLiveDataIntent

    private val _detailIntent = MutableLiveData<IntentClass>()
    val detailIntent:LiveData<IntentClass> = _detailIntent

    private val _sensorLiveData = MutableLiveData<JsonObject>()
    val sensorLiveData:LiveData<JsonObject> = _sensorLiveData

    private val _statusLiveData = MutableLiveData<JsonObject>()
    val statusLiveData:LiveData<JsonObject> = _statusLiveData

    private val _chatLiveData = MutableLiveData<String>()
    val chatLiveData:LiveData<String> = _chatLiveData

    companion object{
        lateinit var socket:WebSocket
    }


    fun refreshCommandList(token:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getCommandList(token)
            _arrayListLiveData.postValue(response)
        }
    }


    fun refreshIntentList(token: String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getIntentList(token)
            _arrayListLiveDataIntent.postValue(response)
        }
    }


    fun refreshDetailIntent(token: String, slug:String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getDetailIntent(token, slug)
            _detailIntent.postValue(response)
        }
    }

    fun refreshSensorData(jsonObj:JsonObject){
        viewModelScope.launch(Dispatchers.IO){
            _sensorLiveData.postValue(jsonObj)
        }
    }

    fun refreshStatus(jsonObj: JsonObject){
        viewModelScope.launch(Dispatchers.Main) {
            _statusLiveData.value = jsonObj
        }
    }

    fun refreshChatResponse(chat:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.postChat(chat)
            _chatLiveData.postValue(response)
        }
    }

}
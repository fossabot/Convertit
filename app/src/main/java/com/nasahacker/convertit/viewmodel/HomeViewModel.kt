package com.nasahacker.convertit.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nasahacker.convertit.App
import com.nasahacker.convertit.util.Constant
import com.nasahacker.convertit.util.Constant.IS_SUCCESS
import com.nasahacker.convertit.util.AppUtil
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class HomeViewModel : ViewModel() {

    private val _uriList = MutableStateFlow<ArrayList<Uri>>(ArrayList())
    val uriList: StateFlow<ArrayList<Uri>> = _uriList

    private val _conversionStatus = MutableStateFlow<Boolean?>(null)
    val conversionStatus: StateFlow<Boolean?> = _conversionStatus

    private val mMsgReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val isSuccess = intent?.getBooleanExtra(IS_SUCCESS, false)
            viewModelScope.launch {
                _conversionStatus.value = isSuccess
            }
        }
    }

    init {
        startListening()
    }


    fun updateUriList(intent: Intent?) {
        viewModelScope.launch {
            intent?.let {
                val uris = AppUtil.getUriListFromIntent(it)
                _uriList.value = ArrayList(_uriList.value).apply { addAll(uris) }
            }
        }
    }


    fun startListening() {
        val mFilter = IntentFilter(Constant.CONVERT_BROADCAST_ACTION)
        ContextCompat.registerReceiver(
            App.application, mMsgReceiver, mFilter, AppUtil.receiverFlags()
        )
    }


    fun clearUriList() {
        viewModelScope.launch {
            _uriList.value = ArrayList()
        }
    }


    fun setConversionStatus(status: Boolean) {
        viewModelScope.launch {
            _conversionStatus.value = status
        }
    }


    fun resetConversionStatus() {
        viewModelScope.launch {
            _conversionStatus.value = null
        }
    }


    override fun onCleared() {
        super.onCleared()
        App.application.unregisterReceiver(mMsgReceiver)
    }
}

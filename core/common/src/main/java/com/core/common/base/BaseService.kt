package com.core.common.base

import android.util.Log
import com.core.common.model.DataResult

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

open class BaseService {

    suspend fun <T> wrapFetchResult(action: suspend () -> DataResult<T>): DataResult<T> {
        return withContext(Dispatchers.IO) {
            try {
                action()
            } catch (e: IOException) {
                Log.d(TAG, "Error ${e.message.toString()}")
                DataResult.Error(e.message.toString())
            } catch (e: HttpException) {
                Log.d(TAG, "Error ${e.message.toString()}")
                DataResult.Error(
                    message = e.message.toString()
                )
            }
        }
    }

    companion object {
        private const val TAG = "BaseService"
    }
}
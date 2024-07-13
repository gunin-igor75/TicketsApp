package com.core.network.dataprovides

import android.content.Context
import android.util.Log
import com.core.network.NetworkSource
import com.core.network.dto.OffersContainer
import com.core.network.dto.TicketsContainer
import com.core.network.dto.TicketsOffersContainer
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.io.BufferedReader
import java.io.InputStreamReader

class FakeNetworkSource(
    private val context: Context,
) : NetworkSource {

    override suspend fun fetchOffers(): OffersContainer {
        delay(500L)
        val json = readJsonFromAssets(MOCK_OFFERS)
        return Gson().fromJson(json, OffersContainer::class.java)
    }

    override suspend fun fetchRecommendationTickets(): TicketsOffersContainer {
        delay(500L)
        val json = readJsonFromAssets(MOCK_TICKETS_OFFERS)
        return Gson().fromJson(json, TicketsOffersContainer::class.java)
    }

    override suspend fun fetchTickets(): TicketsContainer {
        delay(500L)
        val json = readJsonFromAssets(MOCK_TICKETS)
        return Gson().fromJson(json, TicketsContainer::class.java)
    }

    private fun readJsonFromAssets(path: String): String {
        return try {
            val file = context.assets.open(path)
            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            stringBuilder.toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error reading JSON: $e.")
            e.printStackTrace()
            ""
        }
    }

    companion object {
        private const val TAG = "ReadJSONFromAssets"
        private const val MOCK_OFFERS = "offers.json"
        private const val MOCK_TICKETS_OFFERS = "tickets_offers.json"
        private const val MOCK_TICKETS = "tickets.json"
    }
}
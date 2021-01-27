package tech.watanave.smsreceiver

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

@Serializable
data class SlackMessageBody(val text: String, val username: String)

interface SlackService {

    @POST("Please enter slack webhook url")
    suspend fun postMessage(@Body body: SlackMessageBody)

}

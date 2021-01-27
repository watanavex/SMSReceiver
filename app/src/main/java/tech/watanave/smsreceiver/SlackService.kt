package tech.watanave.smsreceiver

import kotlinx.serialization.Serializable
import retrofit2.http.Body
import retrofit2.http.POST

@Serializable
data class SlackMessageBody(val text: String, val username: String)

interface SlackService {

    @POST("Put the slack webhook url here. It should be after https://hooks.slack.com/services/")
    suspend fun postMessage(@Body body: SlackMessageBody)

}

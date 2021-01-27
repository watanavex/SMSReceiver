package tech.watanave.smsreceiver

import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit


class BroadcastReceiver: android.content.BroadcastReceiver() {

    @ExperimentalSerializationApi
    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle = intent?.extras ?: return

        val pdus = bundle["pdus"] as? Array<ByteArray> ?: return
        val format = bundle["format"] as? String ?: return

        val contentType = MediaType.get("application/json")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hooks.slack.com/services/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()

        val service = retrofit.create(SlackService::class.java)

        pdus.forEach { pdu ->
            val message = SmsMessage.createFromPdu(pdu, format)
            val text = """
                OriginatingAddress: ${message.originatingAddress}
                MessageBody: ${message.messageBody}
            """.trimIndent()

            GlobalScope.launch {
                service.postMessage(
                    SlackMessageBody(
                        text,
                        "Android"
                    )
                )
            }
        }
    }

}

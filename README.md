This is an Android app that receives SMS and posts the contents of the message to Slack.
It was created to pass the Apple certification.

created to pass the Apple certification.

### How to use
You need to replace slack's WebHookUrl in the code.  
```Kotlin
interface SlackService {
    @POST("Put the slack webhook url here. It should be after `https://hooks.slack.com/services/`")
    suspend fun postMessage(@Body body: SlackMessageBody)
}
```
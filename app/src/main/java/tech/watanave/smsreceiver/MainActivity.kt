package tech.watanave.smsreceiver

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.runtime.*
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : AppCompatActivity() {

    private val permission = mutableStateOf(false)
    private val message = mutableStateOf("")
    private val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        permission.value = result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActivityContent(permission, message)
        }

        requestPermission.launch(Manifest.permission.RECEIVE_SMS)
    }
}

@Composable
fun ActivityContent(permission: State<Boolean>, message: State<String>) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "SMS Receiver")
            })
        }
    ) {

        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Row {
                Icon(Icons.Filled.VpnKey)
                Text(
                    "️RECEIVE_SMS Permission",
                    style = typography.subtitle2
                )
            }
            Text(
                "${permission.value}",
                style = typography.body1
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row {
                Icon(Icons.Filled.Message)
                Text(
                    "Message",
                    style = typography.subtitle2
                )
            }
            Text(
                "${message.value}",
                style = typography.body1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityContent(
        mutableStateOf(true),
        mutableStateOf("""
                OriginatingAddress: 00000000000
                MessageBody: お客様の Apple ID 確認コードは次の通りです：123456
            """.trimIndent())
    )
}

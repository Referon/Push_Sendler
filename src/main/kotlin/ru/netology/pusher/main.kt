package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream


fun main() {
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    val likesNotification = Message.builder()
        .putData("action", "LIKE")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()

    val postNotification = Message.builder()
        .putData("action", "POST")
        .putData("content", """ {
          "userId": 1,
          "userName": "Vasiliy",
          "content": "Eu eam dolores lobortis percipitur, quo te equidem deleniti disputando.
           Mandamus abhorreant deseruisse mea at, mea elit deserunt persequeris at, in putant fuisset
            honestatis qui. An eos iusto solet, id mel dico habemus. Solum vituperata definitiones te vis. "
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(postNotification)
}

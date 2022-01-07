package com.wife.plugin.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.MongoClientSettings.getDefaultCodecRegistry
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.Updates
import org.bson.codecs.configuration.CodecRegistries.fromProviders
import org.bson.codecs.configuration.CodecRegistries.fromRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.bukkit.entity.Player
import kotlin.reflect.KClass


data class LoginInfo(val username: String, val password: String)

enum class Collections(val collectionName: String) {

    BANS("bans"),
    MUTES("mutes")
    // etc...

}

class MongoDB(loginInfo: LoginInfo, cluster: String, databaseName: String) {

    val database: MongoDatabase

    init {
        val pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build()
        val pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider))
        val uri = "mongodb+srv://${loginInfo.username}:${loginInfo.password}@${cluster}.mongodb.net/" +
                "${cluster.substringBefore('.')}?retryWrites=true&w=majority"
        val connectionString = ConnectionString(uri)
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        val mongoClient = MongoClients.create(settings)
        database = mongoClient.getDatabase(databaseName).withCodecRegistry(pojoCodecRegistry)
    }

    inline fun <reified T> collectionOf(collection: Collections): MongoCollection<T>
            = database.getCollection(collection.collectionName, T::class.java)

}

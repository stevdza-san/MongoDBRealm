package com.stevdza.san.mongodemo.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Person : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.invoke()
    var owner_id: String = ""
    var name: String = ""
    @Index
    var age: Int = 18
    @Ignore
    var adult: Boolean = age >= 18
    var address: Address? = null
    var pets: RealmList<Pet> = realmListOf()
    var timestamp: RealmInstant = RealmInstant.now()
}

class Address : EmbeddedRealmObject {
    var streetName: String = ""
    var streetNumber: Int = 0
}

class Pet: EmbeddedRealmObject {
    var type: String = ""
}
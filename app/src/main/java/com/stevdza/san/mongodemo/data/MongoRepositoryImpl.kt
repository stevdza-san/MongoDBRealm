package com.stevdza.san.mongodemo.data

import android.util.Log
import com.stevdza.san.mongodemo.model.Person
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.mongodb.kbson.ObjectId

class MongoRepositoryImpl(val realm: Realm) : MongoRepository {
    override fun getData(): Flow<List<Person>> {
        return realm.query<Person>().asFlow().map { it.list }
    }

    override fun filterData(name: String): Flow<List<Person>> {
        return realm.query<Person>(query = "name CONTAINS[c] $0", name).asFlow().map { it.list }
    }

    override suspend fun insertPerson(person: Person) {
        realm.write { copyToRealm(person) }
    }

    override suspend fun updatePerson(person: Person) {
        realm.write {
            val queriedPerson = query<Person>(query = "_id == $0", person._id).first().find()
            queriedPerson?.name = person.name
        }
    }

    override suspend fun deletePerson(id: ObjectId) {
        realm.write {
            val person = query<Person>(query = "_id == $0", id).first().find()
            try {
                person?.let { delete(it) }
            } catch (e: Exception) {
                Log.d("MongoRepositoryImpl", "${e.message}")
            }
        }
    }
}
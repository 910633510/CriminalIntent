package com.example.criminalintent
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import java.util.UUID


private const val DATABASE_NAME = "crime-database"
class CrimeRepository private constructor(context: Context){
    private val database:CrimeDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            CrimeDatabase::class.java,
            DATABASE_NAME
        ).build()

    suspend fun getCrimes(): List<Crime> = database.crimeDao().getCrimes()

    suspend fun getCrime(id: UUID): Crime = database.crimeDao().getCrime(id)
    companion object{
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context){
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository{
            return INSTANCE ?:
            throw java.lang.IllegalStateException("CrimeRepository must be initialized")
        }
    }
}
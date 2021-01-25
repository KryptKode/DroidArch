package com.kryptkode.core.cache.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kryptkode.core.cache.user.DbUser.Companion.TABLE_NAME
import com.kryptkode.core.remote.entities.user.Location
import java.util.Date

@Entity(tableName = TABLE_NAME)
data class DbUser(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID) val id: String,
    @ColumnInfo(name = COLUMN_LAST_NAME) val lastName: String,
    @ColumnInfo(name = COLUMN_FIRST_NAME) val firstName: String,
    @ColumnInfo(name = COLUMN_EMAIL) val email: String,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_PICTURE) val picture: String,
    @ColumnInfo(name = COLUMN_GENDER) val gender: String = "",
    @ColumnInfo(name = COLUMN_PHONE) val phone: String = "",
    @ColumnInfo(name = COLUMN_LOCATION) val location: Location = Location(),
    @ColumnInfo(name = COLUMN_REGISTRATION_DATE) val registerDate: Date? = null,
    @ColumnInfo(name = COLUMN_DATE_OF_BIRTH) val dateOfBirth: Date? = null,
) {

    val noDetails: Boolean
        get() = phone.isEmpty() ||
            gender.isEmpty() ||
            registerDate == null ||
            dateOfBirth == null ||
            location.city.isEmpty() ||
            location.state.isEmpty() ||
            location.street.isEmpty() ||
            location.timezone.isEmpty() ||
            location.country.isEmpty()

    companion object {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = "id"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_LAST_NAME = "lastName"
        const val COLUMN_FIRST_NAME = "firstName"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_TITLE = "title"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_PICTURE = "picture"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_REGISTRATION_DATE = "registerDate"
        const val COLUMN_DATE_OF_BIRTH = "dateOfBirth"
    }
}

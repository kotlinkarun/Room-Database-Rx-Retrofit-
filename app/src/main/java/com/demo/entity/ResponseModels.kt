package com.demo.entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class ResponseModels(
    @SerializedName("result") var result: List<Result>
)

@Entity(tableName = "employee")
data class Result(
        @PrimaryKey
        @SerializedName("id") var id: Int? = 0,
        @ColumnInfo(name="acount_No")
        @SerializedName("acount_No") var acountNo: String? = "",
        @SerializedName("account_name") var accountName: String? = "",
        @SerializedName("mobile") var mobile: String? = "",
        @SerializedName("address") var address: String? = "",
        @SerializedName("ac_type") var acType: String? = ""
)
package com.selsela.composebaseproject.data.remote.orders.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Order(
    @SerializedName("additional_cost")
    val additionalCost: Int? = 0,
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("case")
    val case: Case = Case(),
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("grand_total")
    val grandTotal: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("is_rated")
    val isRated: Int = 0,
    @SerializedName("logs")
    val logs: List<Log> = listOf(),
    @SerializedName("number")
    val number: String = "",
    @SerializedName("order_date")
    val orderDate: String = "",
    @SerializedName("order_service")
    val orderService: List<OrderService> = listOf(),
    @SerializedName("supervisor")
    val supervisor: Supervisor? = Supervisor(),
    @SerializedName("transaction")
    val transaction: Transaction? = Transaction(),
    @SerializedName("work_period")
    val workPeriod: WorkPeriod = WorkPeriod()
)
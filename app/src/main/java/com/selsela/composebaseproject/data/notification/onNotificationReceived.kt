package com.selsela.composebaseproject.data.notification

interface OnNotificationReceived {
    fun onReceived(vararg params: String?)
}
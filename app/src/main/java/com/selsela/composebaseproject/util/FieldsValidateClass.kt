package com.selsela.composebaseproject.util

import android.content.Context
import com.selsela.composebaseproject.R
import com.selsela.composebaseproject.util.FieldsValidateConstraintsClass.isEmailValidPattern
import com.selsela.composebaseproject.util.FieldsValidateConstraintsClass.isPasswordValidLength
import com.selsela.composebaseproject.util.FieldsValidateConstraintsClass.isPhoneNumberOnly
import com.selsela.composebaseproject.util.FieldsValidateConstraintsClass.isPhoneNumberValidLength


fun String.validateEmail(mContext: Context,fieldName: String): String {
    var message = ""
    if (this == "") {
        message = mContext.getString(R.string.valid_required2) + " " + fieldName
    } else if (!isEmailValidPattern(this)) {
        message = mContext.getString(R.string.warning_email_pattern)
    }
    return message
}


fun String.validatePhone(mContext: Context,fieldName: String): String {
    var message = ""
    if (this == "") {
        message = mContext.getString(R.string.valid_required2) + " " + fieldName
    } else if (!isPhoneNumberOnly(this)) {
        message = mContext.getString(R.string.warning_phone_pattern)
    } else if (!isPhoneNumberValidLength(this)) {
        message = mContext.getString(R.string.warning_phone_length_pattern)
    } else if (this.startsWith("0", ignoreCase = true)) {
        message = mContext.getString(R.string.not_start_with_zero)
    }
    return message
}

fun String?.validatePhone(mContext: Context): String {
    var message = ""
    if (this == "" || this == null) {
        message = mContext.getString(R.string.valid_required)
    } else if (!isPhoneNumberOnly(this)) {
        message = mContext.getString(R.string.warning_phone_pattern)
    } else if (!isPhoneNumberValidLength(this)) {
        message = mContext.getString(R.string.warning_phone_length_pattern)
    }
    return message
}
fun String?.validatePassword(mContext: Context): String {
    var message = ""
    if (this == "" || this == null) {
        message = mContext.getString(R.string.valid_required)
    } else if (!isPasswordValidLength(this)) {
        message = mContext.getString(R.string.warning_password_length)
    }
    return message
}

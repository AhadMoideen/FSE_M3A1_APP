package com.ahad.fse.models

data class Student (
    var id: Int = Int.MIN_VALUE,
    var fullName: String = "",
    var userName: String = "",
    var dob: String? = "",
    var userType: String? = ""
)
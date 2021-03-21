package com.ahad.fse.models

data class Course(
    var courseId: Int = Int.MIN_VALUE,
    var courseName: String = "",
    var description: String = "",
    var faculty: String? = ""
)
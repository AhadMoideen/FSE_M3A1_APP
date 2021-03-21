package com.ahad.fse.models

data class EvaluationComponent(
    var evaluationComponentId: Int = Int.MIN_VALUE,
    var marks: Int = Int.MIN_VALUE,
    var noOfQuestions: Int = Int.MIN_VALUE,
    var dateTime: String = "",
    var type: String = ""
)
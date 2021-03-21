package com.ahad.fse.models

data class CourseDetail(
    var courseId: Int = Int.MIN_VALUE,
    var courseName: String = "",
    var description: String = "",
    var faculty: String? = "",
    var modules: Array<Module>? = emptyArray(),
    var evaluationComponents: Array<EvaluationComponent>? = emptyArray(),
    var students: Array<Student>? = emptyArray()
)

{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CourseDetail
        if (courseId != other.courseId) return false
        if (courseName != other.courseName) return false
        if (description != other.description) return false
        if (faculty != other.faculty) return false
        return true
    }

    override fun hashCode(): Int {
        var result = courseId
        result = 31 * result + courseName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + (faculty?.hashCode() ?: 0)
        result = 31 * result + (modules?.contentHashCode() ?: 0)
        result = 31 * result + (evaluationComponents?.contentHashCode() ?: 0)
        result = 31 * result + (students?.contentHashCode() ?: 0)
        return result
    }
}
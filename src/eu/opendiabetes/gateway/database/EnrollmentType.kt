package eu.opendiabetes.gateway.database

enum class EnrollmentType(
    val followupId: Int?
) {
    ADULT_USING_DIYAPS(0),
    ADULT_NOT_USING_DIYAPS(1),
    PARENT_USING_DIYAPS(2),
    PARENT_NOT_USING_DIYAPS(3),
    TEENAGER_USING_DIYAPS(null),
    PARTNER_USING_DIYAPS(null),
    PARTNER_NOT_USING_DIYAPS(null),
}

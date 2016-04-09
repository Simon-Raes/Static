package be.simonraes.statictv

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat

/**
 * Created by SimonRaes on 09/04/16.
 */
class DateUtils {
    companion object {}
}

val fmt = DateTimeFormat.forPattern("Y-M-d");

fun DateUtils.Companion.firstDayOfMonth(): DateTime = LocalDate().withDayOfMonth(1).toDateTimeAtStartOfDay()

fun DateTime.toYyyyMmDdFormat(): String = fmt.print(this)
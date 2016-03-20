package be.simonraes.statictv.extensions

import org.joda.time.DateTime

/*
* Converts a ISO 8601 timestamp to the milliseconds version.
*/
fun String.toLongTimeStamp() = DateTime(this).millis



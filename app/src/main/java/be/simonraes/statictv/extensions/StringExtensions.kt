package be.simonraes.statictv.extensions

import org.joda.time.DateTime

/*
* Converts an ISO 8601 timestamp to the milliseconds version.
*/
fun String.toLongTimeStamp() = DateTime(this).millis



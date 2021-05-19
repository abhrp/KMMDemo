package com.abhrp.kmmdemo

import kotlinx.datetime.*


class Greeting {
  fun greeting(): String {
    return "Hello, ${Platform().platform}! There are only ${daysUntilNewYear()} days left!"
  }

  private fun daysUntilNewYear(): Int {
    val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
    val closestNewYear = LocalDate(today.year + 1, 1, 1)
    return today.daysUntil(closestNewYear)
  }
}
package com.abhrp.kmmdemo

import com.abhrp.kmmdemo.cache.Database
import com.abhrp.kmmdemo.cache.DatabaseDriverFactory
import com.abhrp.kmmdemo.network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
  private val database = Database(databaseDriverFactory)
  private val api = SpaceXApi()

  @Throws(Exception::class)
  suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
    val cachedLaunches = database.getAllLaunches()
    return if (cachedLaunches.isNotEmpty() && !forceReload) {
      cachedLaunches
    } else {
      api.getAllLaunches().also {
        database.clearDatabase()
        database.createLaunches(it)
      }
    }
  }

}

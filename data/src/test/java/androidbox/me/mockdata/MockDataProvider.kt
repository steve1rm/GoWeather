@file:JvmName("MockDataProvider")

package androidbox.me.mockdata

import java.util.*

object MockDataProvider {
    fun getInt(): Int = (100..1000).shuffled().first()

    fun getLong(): Long = getInt().toLong()

    fun getString(): String = UUID.randomUUID().toString()
}

package me.androidbox.interactors.forecast.dropdays

class DropInitialDaysImp : DropInitialDays {
    private companion object {
        const val numberOfDays: Int = 1
    }

    override fun numberOfInitialDaysToDrop() = numberOfDays
}

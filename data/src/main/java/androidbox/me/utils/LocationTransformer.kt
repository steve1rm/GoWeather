package androidbox.me.utils

object LocationTransformer {

    @JvmStatic
    fun provideLocationCoordinates(latitude: Double, longitude: Double): String {
        return StringBuilder().let {
            it.append(latitude)
            it.append(",")
            it.append(longitude)

            String(it)
        }
    }
}

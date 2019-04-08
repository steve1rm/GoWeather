package androidbox.me.mappers

interface Mapper<in D, out E> {
    fun map(domain: D): E
}

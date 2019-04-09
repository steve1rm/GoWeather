package androidbox.me.mappers

interface MapperToEntity<in D, out E> {
    fun map(domain: D): E
}

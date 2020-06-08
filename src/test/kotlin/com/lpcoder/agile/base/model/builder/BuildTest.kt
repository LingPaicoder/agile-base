package com.lpcoder.agile.base.model.builder

/**
 * @author liurenpeng
 * Created on 2020-05-26
 */

const val USER_ID = "user_id"
const val DOUGA_COUNTS = "douga_counts"

fun main() {

    GlobalScope inject USER_ID by ::visitor

    Douga::class indexBy Douga::id
    Douga::class buildBy ::getDougaByIds
    Douga::class join User::class by Douga::authorId
    Douga::class join User::class by Douga::checkerId
    Douga::class flat Video::class by ::getVideosByDougaIds
    Douga::class bind DOUGA_COUNTS by ::getDougaLikeCount

    User::class indexBy User::id
    User::class buildBy ::getUserByIds

    Video::class indexBy Video::id
    Video::class buildBy ::getVideoByIds
    Video::class join Source::class by Video::sourceId

    Source::class indexBy Source::id
    Source::class buildBy ::getSourceByIds

    val dougaId = 1L
    val videoId = 1L
    val dougaIds = listOf(1L, 2L)
    val videoIds = listOf(1L, 2L)

    val userId: Long = GlobalScope extract USER_ID
    println("userId:$userId.")

    val dougaView1 = ModelBuilder buildSingle DougaView::class by the(Douga::class, dougaId)
    val dougaView2 = ModelBuilder buildSingle DougaView::class by
            (the(Douga::class, dougaId) and the(Video::class, videoId))
    val dougaViews1 = ModelBuilder buildMulti DougaView::class by the(Douga::class, dougaIds)
    val dougaViews2 = ModelBuilder buildMulti DougaView::class by
            (the(Douga::class, dougaIds) which ::canBePush and the(Video::class, videoIds))
    val dougaLikeCounts: Map<Long, Int> = Douga::class extract DOUGA_COUNTS

    val dougaView3Source = the(Douga::class, dougaId) and the(Video::class, videoId)
    val dougaView3 = ModelBuilder buildSingle DougaView::class by dougaView3Source

    val dougaViews4Source = the(Douga::class, dougaIds) which ::canBePush and
            (the(Video::class, videoIds) which ::canBeShow)
    val dougaViews4 = ModelBuilder buildMulti DougaView::class by dougaViews4Source

    println(
        "userId:$userId. dougaView1:$dougaView1. dougaView2:$dougaView2." +
                "dougaViews1:$dougaViews1. dougaViews2:$dougaViews2. dougaLikeCounts:$dougaLikeCounts" +
                "dougaView3:$dougaView3. dougaViews4:$dougaViews4"
    )
}

fun visitor(): Long {
    return 0
}

fun getDougaByIds(ids: Collection<Long>): Map<Long, Douga> {
    return emptyMap()
}

fun getVideosByDougaIds(id: Collection<Long>): Map<Long, Collection<Video>> {
    return emptyMap()
}

fun getVideoByIds(ids: Collection<Long>): Map<Long, Video> {
    return emptyMap()
}

fun getSourceByIds(ids: Collection<Long>): Map<Long, Source> {
    return emptyMap()
}

fun getUserByIds(ids: Collection<Long>): Map<Long, User> {
    return emptyMap()
}

fun getDougaLikeCount(ids: Collection<Long>): Map<Long, Int> {
    return emptyMap()
}

data class DougaView(
    val douga: Douga,
    val videos: Collection<Video>,
    val sources: Collection<Source>,
    val groupId: String)

data class Douga(val id: Long, val videoId: Long, val authorId: Long, val checkerId: Long)

data class User(val id: Long)

data class Video(val id: Long, val sourceId: Long)

data class Source(val id: Long)

fun canBePush(douga: Douga): Boolean = true
fun canBeShow(video: Video): Boolean = true



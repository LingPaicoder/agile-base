package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.model.builder.annotation.Accompany
import com.lpcoder.agile.base.model.builder.annotation.Join
import com.lpcoder.agile.base.model.builder.annotation.MultiMap
import com.lpcoder.agile.base.model.builder.annotation.SingleMap
import com.lpcoder.agile.base.model.builder.annotation.TargetModel
import com.lpcoder.agile.base.model.builder.relation.by
import com.lpcoder.agile.base.model.builder.relation.join
import com.lpcoder.agile.base.model.builder.relation.multiMap
import com.lpcoder.agile.base.model.builder.relation.singleMap

/**
 * 特性
 * 1. 支持批量构建
 * 2. 全部lazy式构建
 * 3. 语义性强的api
 * 4. 业务代码侵入性低
 * 5. * 支持异步化 回调
 * @author liurenpeng
 * Created on 2020-05-26
 */

const val movieId = 1L
val movieIds = listOf(1L, 2L)

fun init() {
    Movie::class indexBy Movie::id
    Movie::class buildBy ::getMovieByIds

    Movie::class join User::class by Movie::authorId
    Movie::class join User::class by Movie::checkerId
    Movie::class singleMap MovieCount::class by ::getCountsByMovieIds
    Movie::class multiMap Video::class by ::getVideosByMovieIds

    User::class indexBy User::id
    User::class buildBy ::getUserByIds

    Video::class indexBy Video::id
    Video::class buildBy ::getVideoByIds
    Video::class singleMap Source::class by ::getSourcesByVideoIds

    Source::class indexBy Source::id
    Source::class buildBy ::getSourceByIds
}

fun main() {
    val movieView = ModelBuilder buildSingle MovieView::class by movieId
    val movieViews = ModelBuilder buildMulti MovieView::class by movieIds
    println("movieView:$movieView. movieViews:$movieViews.")
}

@TargetModel
data class MovieView @Accompany constructor(val movie: Movie) {

    @MultiMap
    lateinit var videos: Collection<VideoDTO>

    @SingleMap
    lateinit var count: MovieCount

    @Join("authorId")
    lateinit var author: User

    @Join("checkerId")
    lateinit var checker: User

    var visitor: Long = 0

    var groupId: String = ""
}

@TargetModel
data class VideoDTO @Accompany constructor(val video: Video) {

    @SingleMap
    lateinit var source: Source
}

data class Movie(val id: Long, val authorId: Long, val checkerId: Long)

data class User(val id: Long)

data class Video(val id: Long)

data class Source(val id: Long)

data class MovieCount(var movieCounts: Map<Int, Int>) {
    fun getByType(type: MovieCountType) : Int = movieCounts[type.value] ?: 0
}

enum class MovieCountType(val value: Int) {
    UNKNOWN(0),
    COMMENT(1), // 评论数
    PLAY(2), // 播放数
}

// mock
val allUsers = (1..9L).toList().map { it to User(it) }.toMap()
val allSources = (1..9L).toList().map { it to Source(it) }.toMap()
val allVideos = (1..9L).toList().map { it to Video(it) }.toMap()
val videoIdToSourceIdMap = (1..9L).toList().map { it to it }.toMap()
val allMovies = (1..3L).toList().map { it to Movie(it, it, it) }.toMap()
val movieIdToVideoIdsMap = (1..3L).toList().map { it to (3 * it - 2 .. 3 * it).toList() }

fun getMovieByIds(ids: Collection<Long>): Map<Long, Movie> {
    return allMovies.filter { ids.contains(it.key) }
}

fun getVideosByMovieIds(ids: Collection<Long>): Map<Long, Collection<Video>> {
    return emptyMap()
}

fun getCountsByMovieIds(ids: Collection<Long>): Map<Long, MovieCount> {
    return emptyMap()
}

fun getSourcesByVideoIds(ids: Collection<Long>): Map<Long, Source> {
    val idMap = videoIdToSourceIdMap.filter { ids.contains(it.key) }
    val sourceMap = getSourceByIds(idMap.values)
    return idMap.mapValues { sourceMap[it.value] }
        .filter { it.value != null }.mapValues { it.value!! }
}

fun getVideoByIds(ids: Collection<Long>): Map<Long, Video> {
    return allVideos.filter { ids.contains(it.key) }
}

fun getSourceByIds(ids: Collection<Long>): Map<Long, Source> {
    return allSources.filter { ids.contains(it.key) }
}

fun getUserByIds(ids: Collection<Long>): Map<Long, User> {
    return allUsers.filter { ids.contains(it.key) }
}



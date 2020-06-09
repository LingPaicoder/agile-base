package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.model.builder.annotation.Accompany
import com.lpcoder.agile.base.model.builder.annotation.Join
import com.lpcoder.agile.base.model.builder.annotation.MultiMap
import com.lpcoder.agile.base.model.builder.annotation.SingleMap
import com.lpcoder.agile.base.model.builder.annotation.TargetModel
import com.lpcoder.agile.base.model.builder.relation.by
import com.lpcoder.agile.base.model.builder.relation.flatMap
import com.lpcoder.agile.base.model.builder.relation.join
import com.lpcoder.agile.base.model.builder.relation.map

/**
 * @author liurenpeng
 * Created on 2020-05-26
 */

fun main() {

    Movie::class indexBy Movie::id
    Movie::class buildBy ::getMovieByIds
    Movie::class join User::class by Movie::authorId
    Movie::class join User::class by Movie::checkerId
    Movie::class map MovieCount::class by ::getCountsByMovieIds
    Movie::class flatMap Video::class by ::getVideosByMovieIds

    User::class indexBy User::id
    User::class buildBy ::getUserByIds

    Video::class indexBy Video::id
    Video::class buildBy ::getVideoByIds
    Video::class map Source::class by ::getSourcesByVideoIds

    Source::class indexBy Source::id
    Source::class buildBy ::getSourceByIds

    val movieId = 1L
    val movieIds = listOf(1L, 2L)
    val movieView = ModelBuilder buildSingle MovieView::class by movieId
    val movieViews = ModelBuilder buildMulti MovieView::class by movieIds
    println("movieView:$movieView. movieViews:$movieViews.")
}

fun getMovieByIds(ids: Collection<Long>): kotlin.collections.Map<Long, Movie> {
    return emptyMap()
}

fun getVideosByMovieIds(id: Collection<Long>): kotlin.collections.Map<Long, Collection<Video>> {
    return emptyMap()
}

fun getCountsByMovieIds(id: Collection<Long>): kotlin.collections.Map<Long, MovieCount> {
    return emptyMap()
}

fun getSourcesByVideoIds(id: Collection<Long>): kotlin.collections.Map<Long, Source> {
    return emptyMap()
}

fun getVideoByIds(ids: Collection<Long>): kotlin.collections.Map<Long, Video> {
    return emptyMap()
}

fun getSourceByIds(ids: Collection<Long>): kotlin.collections.Map<Long, Source> {
    return emptyMap()
}

fun getUserByIds(ids: Collection<Long>): kotlin.collections.Map<Long, User> {
    return emptyMap()
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

data class MovieCount(var movieCounts: kotlin.collections.Map<Int, Int>) {
    fun getByType(type: MovieCountType) : Int = movieCounts[type.value] ?: 0
}

enum class MovieCountType(val value: Int) {
    UNKNOWN(0),
    COMMENT(1), // 评论数
    PLAY(2), // 播放数
}



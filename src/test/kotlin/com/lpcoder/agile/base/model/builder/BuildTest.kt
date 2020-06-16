package com.lpcoder.agile.base.model.builder

import com.lpcoder.agile.base.model.builder.CurrentScope.visitor
import com.lpcoder.agile.base.model.builder.annotation.Accompany
import com.lpcoder.agile.base.model.builder.annotation.Join
import com.lpcoder.agile.base.model.builder.annotation.MultiMap
import com.lpcoder.agile.base.model.builder.annotation.OutJoin
import com.lpcoder.agile.base.model.builder.annotation.SingleMap
import com.lpcoder.agile.base.model.builder.annotation.TargetModel
import com.lpcoder.agile.base.model.builder.relation.buildBy
import com.lpcoder.agile.base.model.builder.relation.by
import com.lpcoder.agile.base.model.builder.relation.indexBy
import com.lpcoder.agile.base.model.builder.relation.join
import com.lpcoder.agile.base.model.builder.relation.multiMap
import com.lpcoder.agile.base.model.builder.relation.outJoin
import com.lpcoder.agile.base.model.builder.relation.singleMap
import com.lpcoder.agile.base.scope.ScopeKey

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

fun main() {
    initScope()
    initModelBuilder()

    val movieView = ModelBuilder buildSingle MovieView::class by movieId
    val movieViews = ModelBuilder buildMulti MovieView::class by movieIds
    println("movieView:$movieView. movieViews:$movieViews.")
}

const val movieId = 1L
val movieIds = listOf(1L, 2L)

const val SHARED = "shared"
const val VIEWED = "viewed"

fun initModelBuilder() {
    Movie::class indexBy Movie::id
    Movie::class buildBy ::getMovieByIds

    Movie::class join User::class by Movie::authorId
    Movie::class join User::class by Movie::checkerId
    Movie::class outJoin SHARED by ::isShared
    Movie::class outJoin VIEWED by ::isViewed
    Movie::class singleMap MovieCount::class by ::getCountsByMovieIds
    Movie::class singleMap MovieInteraction::class by ::getInteractionsByMovieIds
    Movie::class multiMap Video::class by ::getVideosByMovieIds

    User::class indexBy User::id
    User::class buildBy ::getUserByIds

    Video::class indexBy Video::id
    Video::class buildBy ::getVideoByIds
    Video::class singleMap Source::class by ::getSourcesByVideoIds

    Source::class indexBy Source::id
    Source::class buildBy ::getSourceByIds
}

object CurrentScope{
    val visitor: ScopeKey<Long> = ScopeKey.withDefaultValue(0)
    fun visitor() = visitor.get()
}

fun initScope() {
    visitor.set(1)
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
    var checker: User? = null

    @OutJoin(SHARED)
    var shared: Boolean = false

    @OutJoin(VIEWED)
    var viewed: Boolean = false

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

data class MovieCount(var movieCounts: Map<MovieCountType, Int>) {
    fun getByType(type: MovieCountType) : Int = movieCounts[type] ?: 0
}

/**
 * count类型枚举
 * 指"点"的状态，例如当前movie自身的状态
 */
enum class MovieCountType(val value: Int) {
    UNKNOWN(0),
    COMMENT(1), // 评论数
    PLAY(2), // 播放数
}

data class MovieInteraction(var movieCounts: Map<MovieInteractionType, Int>) {
    fun getByType(type: MovieInteractionType) : Int = movieCounts[type] ?: 0
}

/**
 * 交互类型枚举
 * 指"边"的状态，例如当前登录者和movie之间的状态
 * 可以有"权重"，例如打赏的数量，边不存在时，权重为0，边存在时权重默认为1，也可自定义权重值
 */
enum class MovieInteractionType(val value: Int) {
    UNKNOWN(0),
    LIKE(1), // 点赞
    REWARD(2), // 打赏
}

// mock
val allUsers = (1..9L).toList().map { it to User(it) }.toMap()
val allSources = (1..9L).toList().map { it to Source(it) }.toMap()
val allVideos = (1..9L).toList().map { it to Video(it) }.toMap()
val videoIdToSourceIdMap = (1..9L).toList().map { it to it }.toMap()
val allMovies = (1..3L).toList().map { it to Movie(it, it, it) }.toMap()
val movieIdToVideoIdsMap = (1..3L).toList().map { it to (3 * it - 2 .. 3 * it).toList() }.toMap()
val movieIdToCountMap = (1..3L).toList().map { it to (MovieCount(MovieCountType.values()
    .toList().map { type -> type to (it * type.value).toInt() }.toMap()))}.toMap()
val movieIdToInteractionMap = (1..3L).toList().map { it to (MovieInteraction(MovieInteractionType.values()
    .toList().map { type -> type to if(it != visitor()) 0 else (it * type.value).toInt() }.toMap()))}.toMap()

fun getMovieByIds(ids: Collection<Long>): Map<Long, Movie> {
    return allMovies.filter { ids.contains(it.key) }
}

fun getVideosByMovieIds(ids: Collection<Long>): Map<Long, Collection<Video>> {
    return movieIdToVideoIdsMap.filter { ids.contains(it.key) }
        .mapValues { getVideoByIds(it.value).values }
}

fun getCountsByMovieIds(ids: Collection<Long>): Map<Long, MovieCount> {
    return movieIdToCountMap.filter { ids.contains(it.key) }
}

fun getInteractionsByMovieIds(ids: Collection<Long>): Map<Long, MovieInteraction> {
    return movieIdToInteractionMap.filter { ids.contains(it.key) }
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

/**
 * 这里mock的逻辑是：id相同时为true
 */
fun isShared(ids: Collection<Long>): Map<Long, Boolean> {
    val userId = visitor()
    return ids.toSet().map { it to (userId == it) }.toMap()
}

/**
 * 这里mock的逻辑是：id相同时为true
 */
fun isViewed(ids: Collection<Long>): Map<Long, Boolean> {
    val userId = visitor()
    return ids.toSet().map { it to (userId == it) }.toMap()
}

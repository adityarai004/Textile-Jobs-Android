package com.example.trendingtimesjetpack.core.constants

object NetworkConstants {
    const val BASE_URL = "https://trendingtimesbackend.onrender.com"

    const val LOGIN = "/auth/login"
    const val SIGN_UP = "/auth/register"
    private const val NEWS_SUB_END_POINT = "/news/category-wise"

    private const val TOP_HEADLINES = "${NEWS_SUB_END_POINT}/top_headlines"
    private const val BUSINESS = "${NEWS_SUB_END_POINT}/business"
    private const val SCIENCE = "${NEWS_SUB_END_POINT}/science"
    private const val POLITICS = "${NEWS_SUB_END_POINT}/politics"
    private const val HEALTH = "${NEWS_SUB_END_POINT}/health"
    private const val EDUCATION = "${NEWS_SUB_END_POINT}/education"
    private const val TECHNOLOGY = "${NEWS_SUB_END_POINT}/technology"
    private const val ENTERTAINMENT = "${NEWS_SUB_END_POINT}/entertainment"
    private const val OPINION = "${NEWS_SUB_END_POINT}/opinion"
    private const val SPORTS = "${NEWS_SUB_END_POINT}/sports"

    val NEWS_ENDPOINTS = arrayOf(
        TOP_HEADLINES,
        TECHNOLOGY,
        POLITICS,
        HEALTH,
        SCIENCE,
        ENTERTAINMENT,
        SPORTS,
        OPINION,
        BUSINESS,
        EDUCATION,
    )
}
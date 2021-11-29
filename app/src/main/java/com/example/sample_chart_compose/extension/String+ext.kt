package com.example.sample_chart_compose.extension

fun String.appendPath(path: String) : String {
    return "$this/$path"
}
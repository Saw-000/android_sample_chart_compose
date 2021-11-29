package com.example.sample_chart_compose.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sample_chart_compose.R

sealed class PageNav(val route: String, @StringRes val description: Int) {
    object Chart: PageNav("chart", R.string.page_description_chart)
    object SomeOther: PageNav("some_other", R.string.page_description_some_other)

    // get tab icon
    fun getTabIcon(): ImageVector {
        return when (this) {
            Chart -> Icons.Outlined.Share
            SomeOther -> Icons.Outlined.Info
        }
    }

    companion object {
        const val HOME_TAB_ROOT_ROUTE = "home"
    }
}
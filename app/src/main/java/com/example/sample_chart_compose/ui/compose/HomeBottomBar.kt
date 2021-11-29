package com.example.sample_chart_compose.ui.compose

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.sample_chart_compose.ui.navigation.PageNav

// debug: NavHostControllerでなく、タップメソッドを渡す。
@Composable
fun HomeBottomBar(
    tabs: List<PageNav>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
) {
    BottomNavigation {
        tabs.forEach { page ->
            val route = page.route
            val iconTitle = stringResource(id = page.description)
            BottomNavigationItem(
                icon = {
                    val imgVec = page.getTabIcon()
                    if (imgVec != null) {
                        Icon(imageVector = imgVec, contentDescription = null)
                    }
                },
                label = { Text(text = iconTitle) },
                selected = currentRoute == page.route ,
                onClick = { navigateToRoute(route) }
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomeBottomBar(){
    HomeBottomBar(
        tabs = listOf(PageNav.Chart, PageNav.SomeOther),
        currentRoute = "debug",
        navigateToRoute = {}
    )
}
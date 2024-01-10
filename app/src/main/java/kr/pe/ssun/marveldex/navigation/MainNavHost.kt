package kr.pe.ssun.marveldex.navigation

import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kr.pe.ssun.marveldex.ui.common.MyWebView

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    showToast: (String) -> Toast,
    onBack: () -> Unit,
    startDestination: String = homeNavigationRoute,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        // 홈
        homeScreen(
            enterTransition = defaultEnterTransition(),
            exitTransition = defaultExitTransition(),
            popEnterTransition = defaultPopEnterTransition(),
            popExitTransition = defaultPopExitTransition(),
            navigate = { route, params -> navigate(navController, route, params) },
            showToast = showToast,
            onBack = onBack
        )
        // 포토
        characterDetailScreen(
            enterTransition = defaultEnterTransition(),
            exitTransition = defaultExitTransition(),
            popEnterTransition = defaultPopEnterTransition(),
            popExitTransition = defaultPopExitTransition(),
            navigate = { route, params -> navigate(navController, route, params) },
            showToast = showToast,
            onBack = onBack
        )
        // 웹뷰
        composable(
            route = "webview/{url}",
            arguments = listOf(
                navArgument("url" ) { type = NavType.StringType }
            ),
            enterTransition = { defaultEnterTransition() },
            exitTransition = { defaultExitTransition() },
            popEnterTransition = { defaultPopEnterTransition() },
            popExitTransition = { defaultPopExitTransition() },
        ) { backStackEntry ->
            val encodedUrl = backStackEntry.arguments?.getString("url")
            val decodedUrl = String(Base64.decode(encodedUrl, 0))
            MyWebView(url = decodedUrl)
        }
    }
}

fun defaultEnterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { fullWidth -> fullWidth },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)

fun defaultExitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { fullHeight -> -fullHeight },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)

fun defaultPopEnterTransition(): EnterTransition = slideInHorizontally(
    initialOffsetX = { fullWidth -> -fullWidth },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)

fun defaultPopExitTransition(): ExitTransition = slideOutHorizontally(
    targetOffsetX = { fullWidth -> fullWidth },
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing
    )
)

fun navigate(
    navController: NavHostController,
    dest: String,
    params: Any? = null,
) {
    when (dest) {
        characterDetailNavigationRoute -> (params as? Pair<*, *>)?.let { (title, url) ->
            navController.navigateToCharacterDetail(title as String, url as String)
        }
        else -> TODO()
    }
}
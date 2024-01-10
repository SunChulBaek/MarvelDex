package kr.pe.ssun.marveldex.navigation

import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import kr.pe.ssun.marveldex.ui.home.HomeScreen
import com.google.accompanist.navigation.animation.composable
import timber.log.Timber

const val homeNavigationRoute = "home"

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeScreen(
    enterTransition: EnterTransition = EnterTransition.None,
    exitTransition: ExitTransition = ExitTransition.None,
    popEnterTransition: EnterTransition = EnterTransition.None,
    popExitTransition: ExitTransition = ExitTransition.None,
    navigate: (String, Any?) -> Unit,
    showToast: (String) -> Toast,
    onBack: () -> Unit,
) {
    composable(
        route = homeNavigationRoute,
        enterTransition = {
            Timber.d("[sunchulbaek] home.enterTransition()")
            enterTransition },
        exitTransition = {
            Timber.d("[sunchulbaek] home.exitTransition()")
            exitTransition },
        popEnterTransition = {
            Timber.d("[sunchulbaek] home.popEnterTransition()")
            popEnterTransition },
        popExitTransition = {
            Timber.d("[sunchulbaek] home.popExitTransition()")
            popExitTransition }
    ) {
        HomeScreen(navigate, showToast, onBack)
    }
}
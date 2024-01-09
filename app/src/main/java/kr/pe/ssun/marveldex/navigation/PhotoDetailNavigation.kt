package kr.pe.ssun.marveldex.navigation

import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import kr.pe.ssun.marveldex.ui.detail.PhotoDetailScreen
import com.google.accompanist.navigation.animation.composable

const val photoDetailNavigationRoute = "photo_detail"

const val photoDetailTitleArgs = "title"
const val photoDetailUrlArgs = "url"

fun NavController.navigateToPhotoDetail(title: String, url: String, navOptions: NavOptions? = null) {
    val encoded = Base64.encodeToString(url.toByteArray(), Base64.DEFAULT)
    this.navigate("$photoDetailNavigationRoute/$title/$encoded", navOptions)
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.photoDetailScreen(
    enterTransition: EnterTransition = EnterTransition.None,
    exitTransition: ExitTransition = ExitTransition.None,
    popEnterTransition: EnterTransition = EnterTransition.None,
    popExitTransition: ExitTransition = ExitTransition.None,
    navigate: (String, Any?) -> Unit,
    showToast: (String) -> Toast,
    onBack: () -> Unit,
) {
    composable(
        route = "$photoDetailNavigationRoute/{$photoDetailTitleArgs}/{$photoDetailUrlArgs}",
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition },
    ) { backStackEntry ->
        val title = backStackEntry.arguments?.getString(photoDetailTitleArgs)
        val encodedUrl = backStackEntry.arguments?.getString(photoDetailUrlArgs)
        val decodedUrl = String(Base64.decode(encodedUrl, 0))
        PhotoDetailScreen(
            title = title,
            url = decodedUrl,
        )
    }
}
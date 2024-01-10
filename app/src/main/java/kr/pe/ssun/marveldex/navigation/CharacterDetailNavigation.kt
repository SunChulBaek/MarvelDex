package kr.pe.ssun.marveldex.navigation

import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kr.pe.ssun.marveldex.ui.detail.CharacterDetailScreen

const val characterDetailNavigationRoute = "character_detail"

const val characterDetailTitleArgs = "title"
const val characterDetailUrlArgs = "url"

fun NavController.navigateToCharacterDetail(title: String, url: String, navOptions: NavOptions? = null) {
    val encoded = Base64.encodeToString(url.toByteArray(), Base64.DEFAULT)
    this.navigate("$characterDetailNavigationRoute/$title/$encoded", navOptions)
}

fun NavGraphBuilder.characterDetailScreen(
    enterTransition: EnterTransition = EnterTransition.None,
    exitTransition: ExitTransition = ExitTransition.None,
    popEnterTransition: EnterTransition = EnterTransition.None,
    popExitTransition: ExitTransition = ExitTransition.None,
    navigate: (String, Any?) -> Unit,
    showToast: (String) -> Toast,
    onBack: () -> Unit,
) {
    composable(
        route = "$characterDetailNavigationRoute/{$characterDetailTitleArgs}/{$characterDetailUrlArgs}",
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition },
    ) { backStackEntry ->
        val title = backStackEntry.arguments?.getString(characterDetailTitleArgs)
        val encodedUrl = backStackEntry.arguments?.getString(characterDetailUrlArgs)
        val decodedUrl = String(Base64.decode(encodedUrl, 0))
        CharacterDetailScreen(
            title = title,
            url = decodedUrl,
        )
    }
}
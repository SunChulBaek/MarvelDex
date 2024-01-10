package kr.pe.ssun.marveldex.navigation

import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.pe.ssun.marveldex.ui.detail.CharacterDetailScreen

const val characterDetailNavigationRoute = "character_detail"

const val characterDetailIdArgs = "id"
const val characterDetailNameArgs = "name"
const val characterDetailUrlArgs = "url"

fun NavController.navigateToCharacterDetail(id: Int, name: String, thumbnail: String, navOptions: NavOptions? = null) {
    val encoded = Base64.encodeToString(thumbnail.toByteArray(), Base64.DEFAULT)
    this.navigate("$characterDetailNavigationRoute/$id/$name/$encoded", navOptions)
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
        route = "$characterDetailNavigationRoute/{$characterDetailIdArgs}/{$characterDetailNameArgs}/{$characterDetailUrlArgs}",
        arguments = listOf(
            navArgument(characterDetailIdArgs) {
                type = NavType.IntType
            }
        ),
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition },
    ) { backStackEntry ->
        val name = backStackEntry.arguments?.getString(characterDetailNameArgs)
        val encodedUrl = backStackEntry.arguments?.getString(characterDetailUrlArgs)
        val decodedUrl = String(Base64.decode(encodedUrl, 0))
        CharacterDetailScreen(
            name = name,
            thumbnail = decodedUrl,
        )
    }
}
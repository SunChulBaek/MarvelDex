package kr.pe.ssun.marveldex.navigation

import android.net.Uri
import android.util.Base64
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kr.pe.ssun.marveldex.ui.detail.CharacterDetailRoute

const val characterDetailNavigationRoute = "character_detail"

const val characterDetailIdArgs = "id"
const val characterDetailNameArgs = "name"
const val characterDetailUrlArgs = "url"

internal class CharacterDetailArgs(val id: Int) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = Uri.decode(checkNotNull(savedStateHandle[characterDetailIdArgs])).toInt(),
    )
}

fun NavController.navigateToCharacterDetail(id: Int, name: String, thumbnail: String, navOptions: NavOptions? = null) {
    val encodedName = Base64.encodeToString(name.toByteArray(), Base64.DEFAULT)
    val encodedUrl = Base64.encodeToString(thumbnail.toByteArray(), Base64.DEFAULT)
    this.navigate("$characterDetailNavigationRoute/$id/$encodedName/$encodedUrl", navOptions)
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
        enterTransition = { enterTransition },
        exitTransition = { exitTransition },
        popEnterTransition = { popEnterTransition },
        popExitTransition = { popExitTransition },
    ) { backStackEntry ->
        val encodedName = backStackEntry.arguments?.getString(characterDetailNameArgs)
        val decodedName = String(Base64.decode(encodedName, 0))
        val encodedUrl = backStackEntry.arguments?.getString(characterDetailUrlArgs)
        val decodedUrl = String(Base64.decode(encodedUrl, 0))
        CharacterDetailRoute(
            name = decodedName,
            thumbnail = decodedUrl,
            onBack = onBack
        )
    }
}
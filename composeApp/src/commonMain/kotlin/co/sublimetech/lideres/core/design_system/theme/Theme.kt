package co.sublimetech.lideres.core.design_system.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

val LightColorScheme = lightColorScheme(
    primary = LightBlue,
    background = White,
    secondary = DarkBlue,
    tertiary = LightBlack,
    outline = LightGray,
    primaryContainer = LightGray
)


@Composable
fun LideresTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FuturaTypography(),
        content = content
    )
}
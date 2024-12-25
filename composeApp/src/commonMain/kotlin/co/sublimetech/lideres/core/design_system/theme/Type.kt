package co.sublimetech.lideres.core.design_system.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.futura_bk_bt
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font


@Composable
fun FuturaBk() = FontFamily(
    Font(Res.font.futura_bk_bt)
)
@Composable
fun FuturaMd() = FontFamily(
    Font(Res.font.futura_md_bt)
)


@Composable
fun FuturaTypography() = Typography().run {

    val fontBkFamily = FuturaBk()
    val fontMdFamily = FuturaMd()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontMdFamily),
        displayMedium = displayMedium.copy(fontFamily = fontMdFamily),
        displaySmall = displaySmall.copy(fontFamily = fontMdFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontMdFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontMdFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontMdFamily),
        titleLarge = titleLarge.copy(fontFamily = fontMdFamily),
        titleMedium = titleMedium.copy(fontFamily = fontMdFamily),
        titleSmall = titleSmall.copy(fontFamily = fontMdFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontBkFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontBkFamily),
        bodySmall = bodySmall.copy(fontFamily = fontBkFamily),
        labelLarge = labelLarge.copy(fontFamily = fontBkFamily),
        labelMedium = labelMedium.copy(fontFamily = fontBkFamily),
        labelSmall = labelSmall.copy(fontFamily = fontBkFamily)
    )
}

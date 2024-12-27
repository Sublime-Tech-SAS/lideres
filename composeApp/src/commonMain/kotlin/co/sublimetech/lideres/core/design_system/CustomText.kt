package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.sublimetech.lideres.core.design_system.theme.Black
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font


@Composable
fun BlockTitle(
    title: String?,
    subtitle: String?,
    onClick: (() -> Unit)? = null,
    width: Boolean = false,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = if (subtitle != null) Alignment.CenterVertically else Alignment.Top) {
            if (title != null) {
                Text(
                    title,
                    modifier = Modifier
                        .then(if (width) Modifier.width(330.dp) else Modifier)
                        .padding(start = 16.dp, end = if(onClick != null)6.dp else 12.dp)
                        .padding(top = 16.dp, bottom = if (subtitle == null) 16.dp else 0.dp),
                    color = MaterialTheme.colorScheme.background,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(Res.font.futura_md_bt))
                )
            }
            if (onClick != null) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Expandir gráfica",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier
                        .padding(top = if (subtitle != null) 16.dp else 19.dp)
                        .size(16.dp)
                )
            }
        }
        if (subtitle != null) {
            Text(
                subtitle,
                modifier = modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = if (title == null) 12.dp else 0.dp),
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.background,
                fontFamily = FontFamily(Font(Res.font.futura_md_bt))
            )

        }
    }
}

@Composable
fun Title(title: String, bottomPadding: Int = 20) {
    Text(
        title,
        modifier = Modifier
            .padding(top = 10.dp, bottom = bottomPadding.dp),
        color = Black,
        fontSize = 14.sp,
        fontFamily = FontFamily(
            Font(Res.font.futura_md_bt)
        )
    )
}

@Composable
fun Disclaimer(
    title: String,
    bottomPadding: Int = 20,
    fontSize: Int = 12,
    justify: Boolean = false,
) {
    Text(
        title,
        modifier = Modifier
            .padding(bottom = bottomPadding.dp)
            .padding(horizontal = 4.dp),
        color = MaterialTheme.colorScheme.tertiary,
        fontSize = fontSize.sp,
        fontFamily = FontFamily(
            Font(Res.font.futura_md_bt)
        ),
        textAlign = if (justify) TextAlign.Justify else TextAlign.Start // Corrected this line
    )
}


@Composable
fun BlockTitlePreview() {
    BlockTitle(
        "Diligenciamiento del formulario",
        "1. Fecha y lugar de Diligenciamiento del Formulario:",
        {}
    )
}
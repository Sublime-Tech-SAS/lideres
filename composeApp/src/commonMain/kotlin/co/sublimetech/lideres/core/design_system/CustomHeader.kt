package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.sublimetech.lideres.core.design_system.theme.DarkBlue
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.back_icon
import lideres.composeapp.generated.resources.form_icon
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomHeader(title: String, atForm: Boolean, onClick: () -> Unit) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(DarkBlue)
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 20.dp, bottom = 10.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = if (!atForm) painterResource(Res.drawable.form_icon) else painterResource(
                    Res.drawable.back_icon
                ),
                contentDescription = null,
                modifier = Modifier.clickable { onClick() },
                tint = Color.White
            )
            Spacer(modifier = Modifier.weight(0.8f))
            Text(
                text = title,
                style = TextStyle(
                    fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.background
                )
            )
            Spacer(modifier = Modifier.weight(1.2f))
        }
    }
}
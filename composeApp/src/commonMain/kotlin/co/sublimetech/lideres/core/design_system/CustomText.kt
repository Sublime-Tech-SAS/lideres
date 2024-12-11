package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomText(title: String, modifier: Modifier =Modifier) {
    Text(
        title,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .padding(top = 16.dp, start = 16.dp),
        style = LocalTextStyle.current.copy(
            color = Color.Black
        ),
    )
}
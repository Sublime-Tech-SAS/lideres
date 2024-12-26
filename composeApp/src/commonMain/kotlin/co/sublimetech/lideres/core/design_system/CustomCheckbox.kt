package co.sublimetech.lideres.core.design_system


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font

@Composable
fun CustomCheckbox(
    isFilled: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .size(12.dp)
            .background(
                if (isFilled) MaterialTheme.colorScheme.tertiary else Color.Transparent,
                RoundedCornerShape(4.dp)
            )
            .border(1.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(4.dp))
            .clickable { onCheckedChange(!isFilled) }
    )
}

@Composable
fun OptionsGrid(
    options: List<Pair<String, TextFieldState>>,
    columns: Int,
    onOptionSelected: (Int) -> Unit,
) {
    val rows = (options.size + columns - 1) / columns

    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (rowIndex in 0 until rows) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                for (columnIndex in 0 until columns) {
                    val optionIndex = rowIndex * columns + columnIndex
                    if (optionIndex < options.size) {
                        val option = options[optionIndex]
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                //.weight(1f)
                                .padding(start =4.dp, top = 2.dp, bottom = 2.dp, end = 24.dp)
                        ) {
                            Text(
                                text = option.first,
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.padding(end = 12.dp),
                                fontFamily = FontFamily(Font(Res.font.futura_md_bt))
                            )
                            CustomCheckbox(
                                isFilled = option.second.text == "true",
                                onCheckedChange = { isSelected ->
                                    if (isSelected) {
                                        onOptionSelected(optionIndex)
                                    }
                                }
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}


@Composable
fun OptionsGridPreview() {
    val options by remember {
        mutableStateOf(
            listOf(
                "Cédula de Ciudadanía" to TextFieldState("false"),
                "Cédula de Extranjería" to TextFieldState("false"),
                "NUIP" to TextFieldState("false"),
            )
        )
    }

    Column(modifier = Modifier.background(Color.White)) {
        OptionsGrid(
            options = options,
            columns = 2,
            onOptionSelected = { selectedIndex ->
                options.forEachIndexed { index, pair ->
                    pair.second.edit {
                        replace(
                            0,
                            length,
                            if (index == selectedIndex) options[index].first else ""
                        )
                    }
                }
            }
        )
    }
}


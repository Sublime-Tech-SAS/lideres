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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.foreign_id
import lideres.composeapp.generated.resources.futura_md_bt
import lideres.composeapp.generated.resources.national_id
import lideres.composeapp.generated.resources.nuip
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.stringResource

@Composable
fun CustomCheckbox(
    isFilled: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
) {
    Box(
        modifier = modifier
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
    bottomPadding: Int = 30,
    inverted: Boolean = false,
) {
    val selectedIndex = remember { mutableStateOf(-1) }

    val rows = (options.size + columns - 1) / columns
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = bottomPadding.dp),
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
                        val isSelected = selectedIndex.value == optionIndex

                        if (inverted) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier
                                    .padding(start = 4.dp, top = 2.dp, bottom = 2.dp, end = 24.dp)
                            ) {

                                CustomCheckbox(
                                    isFilled = isSelected,
                                    modifier = Modifier.padding(top = 6.dp),
                                    onCheckedChange = {
                                        selectedIndex.value =
                                            if (isSelected) -1 else optionIndex // Toggle logic
                                        onOptionSelected(selectedIndex.value)
                                    }
                                )
                                Text(
                                    text = option.first,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.padding(start = 12.dp),
                                    fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
                                    textAlign = TextAlign.Justify,
                                    maxLines = Int.MAX_VALUE
                                )
                            }
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(start = 4.dp, top = 2.dp, bottom = 2.dp, end = 24.dp)
                            ) {

                                Text(
                                    text = option.first,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    modifier = Modifier.padding(end = 12.dp).widthIn(max = 270.dp),
                                    fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
                                    maxLines = Int.MAX_VALUE
                                )

                                CustomCheckbox(
                                    isFilled = isSelected,
                                    onCheckedChange = {
                                        selectedIndex.value =
                                            if (isSelected) -1 else optionIndex
                                        onOptionSelected(selectedIndex.value)
                                    }
                                )
                            }
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
    val options = listOf(
        stringResource(Res.string.national_id) to rememberTextFieldState("cedula"),
        stringResource(Res.string.foreign_id) to rememberTextFieldState("extranejra"),
        stringResource(Res.string.nuip) to rememberTextFieldState("nuip")
    )
    Column(
        modifier = Modifier.background(Color.White)
    ) {
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

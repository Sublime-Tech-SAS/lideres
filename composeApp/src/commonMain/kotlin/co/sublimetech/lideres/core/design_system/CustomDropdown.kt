package co.sublimetech.lideres.core.design_system


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import lideres.composeapp.generated.resources.Res
import lideres.composeapp.generated.resources.futura_md_bt
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropdown(
    selectedValue: String,
    onSelectedValueChange: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    val filteredOptions = listOf("Últimos 7 días", "Últimos 30 días", "Siempre")


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.width(155.dp)
            .height(50.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {},
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(Res.font.futura_md_bt))
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp).padding(bottom = 2.dp)
                )
            },
            modifier = Modifier.menuAnchor(
                type = MenuAnchorType.PrimaryEditable,
                enabled = true
            ),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedIndicatorColor = MaterialTheme.colorScheme.outline,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.outline,
                disabledIndicatorColor = Color.Transparent
            )


        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp)
        ) {
            filteredOptions.forEach { option: String ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(Res.font.futura_md_bt)),
                            modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                            textAlign = TextAlign.End
                        )
                    },
                    onClick = {
                        expanded = false
                        onSelectedValueChange(option)
                    },
                    colors = MenuDefaults.itemColors()
                )
            }
        }
    }
}


package co.sublimetech.lideres.core.design_system

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownComponent() {

    var expanded by remember { mutableStateOf(false) }
    val options = listOf("Option 1", "Option 2", "Option 3")
    val textFieldState = rememberTextFieldState(options[0])


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        BasicTextField(
            state = textFieldState,
            readOnly = true,
            textStyle = TextStyle.Default.copy(color = MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .background(Color.White)
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(16.dp),

            decorator = { innerBox ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                    .clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    innerBox()
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector =
                        Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = Color.LightGray
                    )
                }
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            option,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    onClick = {
                        textFieldState.setTextAndPlaceCursorAtEnd(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun DropdownPreview() {
    val selectedValue = "this"
    // Variable that controls whether the dropdown menu is expanded or not.
    var expanded by remember { mutableStateOf(false) }
    // Variable that keeps track of the text input by the user.
    var typedText by remember { mutableStateOf(selectedValue) }

    // Keep `typedText` synchronized with `selectedValue`
    LaunchedEffect(selectedValue) {
        typedText = selectedValue
    }



    // Filter options based on `typedText` if `isEditable` is true.
    val filteredOptions = listOf("Option 1", "Option 2", "Option 3")

    // Dropdown menu box provided by Material3
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },

    ) {
        // Custom text field, based on `MyTextField`
        TextField(
            readOnly = true, // If not editable, the field is read-only.
            value = typedText,
            onValueChange = { newText:String ->
                typedText = newText
                // Expand the menu if it is not expanded when the user types.
                if (!expanded) {
                    expanded = true
                }
            },
            label = {"label"},
            //isMandatory = false,
            //contextInfoMessage = "contextInfoMessage",
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable, enabled = true),
            trailingIcon = {
                // Icon indicating whether the menu is expanded.
//ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        // Dropdown menu to show the available options.
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = MaterialTheme.colorScheme.background,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
            shadowElevation = 5.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            if (filteredOptions.isNotEmpty()) {
                // Show each option as an item in the dropdown menu.
                filteredOptions.forEach { option: String ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            expanded = false
                            typedText = option
                            // Trigger the event when a new option is selected.
                           // onValueChangedEvent(option)
                        },
                        colors = MenuDefaults.itemColors()
                    )
                }
            } else {
                // Show a message when no matching options are found.
                DropdownMenuItem(
                    text = { Text(text = "No options found") },
                    onClick = { /* Do nothing */ },
                    enabled = false
                )
            }
        }
    }
}
package com.frommetoyou.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.frommetoyou.common.UiText
import com.frommetoyou.core.R

@Preview(showBackground = true)
@Composable
fun BreedSelector(
    text: String = "¿Raza?",
    options: List<String?> = listOf(),
    selectedItem: String? = "Ninguno",
    onClick: (item: String?) -> Unit = {}
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    Spacer(Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(.8f)
        )
        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
                    .pointerInput(true) {
                    detectTapGestures {
                        expanded = true
                    }
                }
            ) {
                Text(
                    text = UiText.DynamicString(selectedItem ?: UiText.StringResource(R.string.dogs_4).asString(context)).asString(
                        LocalContext.current
                    ),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_drop_down_24),
                    contentDescription = UiText.StringResource(R.string.description).asString(context),
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                offset = DpOffset(x = 0.dp, y = 8.dp)
            ) {
                options.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        text = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = UiText.DynamicString(item ?: UiText.StringResource(R.string.dogs_4).asString(context))
                                        .asString(LocalContext.current)
                                )
                            }
                        },
                        onClick = {
                            expanded = false
                            onClick(item)
                        })
                }
            }
        }
    }
}
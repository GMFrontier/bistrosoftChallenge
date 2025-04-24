package com.frommetoyou.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.frommetoyou.common.LifecycleEventLogger
import com.frommetoyou.common.UiText
import com.frommetoyou.core.R

@Composable
fun FactorialScreen(
    modifier: Modifier = Modifier,
    viewModel: FactorialViewModel = hiltViewModel()
) {

    val factorialResult = viewModel.factorial.collectAsState()

    LifecycleEventLogger("FactorialScreen") { screenName, event ->
        viewModel.addEvent(screenName, event)
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    var factorial by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = UiText.StringResource(R.string.factorial_0).asString(context),
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = UiText.StringResource(R.string.factorial_1).asString(context)
        )
        Text(
            modifier = Modifier.padding(top = 56.dp),
            text = UiText.StringResource(R.string.factorial_2).asString(context)
        )
        Card(
            colors = CardDefaults.cardColors(
                containerColor = CardDefaults.cardColors().containerColor.copy(
                    alpha = 0.5f
                )
            ),
        ) {
            TextField(
                value = factorial,
                onValueChange = { factorial = it },
                label = { Text(UiText.StringResource(R.string.factorial_3).asString(context)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                enabled = factorial.isBlank().not(),
                colors = ButtonDefaults.buttonColors(containerColor = if (factorial.isBlank().not()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant),
                shape = RoundedCornerShape(16.dp),
                onClick = {
                    viewModel.calculateFactorial(factorial)
                }
            ) {
                Text(
                    UiText.StringResource(R.string.calculate)
                        .asString(context)
                )
            }
            Text(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .fillMaxWidth()
                    .testTag("FactorialResult"),
                text = factorialResult.value,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}
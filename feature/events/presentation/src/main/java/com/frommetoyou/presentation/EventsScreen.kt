package com.frommetoyou.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.frommetoyou.common.LifecycleEventLogger
import com.frommetoyou.common.UiText
import com.frommetoyou.common.formatDate
import com.frommetoyou.core.R

@Composable
fun EventsScreen(
    modifier: Modifier = Modifier,
    viewModel: EventsViewModel = hiltViewModel()
) {

    val events = viewModel.events.collectAsState()
    val screenStats = viewModel.screenStats.collectAsState()
    val context = LocalContext.current
    val screenDimensions = LocalConfiguration.current

    LifecycleEventLogger("EventsScreen") { screenName, event ->
        viewModel.addEvent(screenName, event)
    }

    LaunchedEffect(Unit) {
        viewModel.getEvents()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = UiText.StringResource(R.string.events_0).asString(context),
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(top = 36.dp),
            text = UiText.StringResource(R.string.events_1).asString(context)
        )


        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            enabled = true,
            colors = ButtonDefaults.buttonColors(containerColor = if (true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant),
            shape = RoundedCornerShape(16.dp),
            onClick = {
                viewModel.clearEvents()
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_baseline_cleaning_services_24),
                    contentDescription = UiText.StringResource(R.string.description)
                        .asString(context),
                )
                Text(
                    UiText.StringResource(R.string.clear_data)
                        .asString(context)
                )
            }
        }

        Card(
            modifier = Modifier.padding(top = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardDefaults.cardColors().containerColor.copy(
                    alpha = 0.5f
                )
            ),
        ) {
            Column(
                modifier = Modifier.padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height((screenDimensions.screenHeightDp * .35).dp)
                ) {
                    items(events.value) { event ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column(
                                    modifier = Modifier.weight(.6f),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = event.screenName,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(text = event.name)
                                }
                                Text(
                                    modifier = Modifier.weight(.4f),
                                    text = formatDate(event.date.toLong())
                                )
                            }
                            HorizontalDivider(
                                Modifier
                                    .padding(4.dp)
                                    .width(200.dp)
                            )
                        }
                    }
                }
                HorizontalDivider(Modifier.padding(4.dp))
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = UiText.StringResource(R.string.events_2)
                            .asString(context),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    screenStats.value.entries.sortedByDescending { it.value }
                        .forEach { (screen, percent) ->
                            Text(text = "$screen: ${"%.1f".format(percent)}%")
                        }
                }
            }
        }
    }
}
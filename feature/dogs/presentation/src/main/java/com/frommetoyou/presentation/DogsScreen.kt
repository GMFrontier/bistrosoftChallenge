package com.frommetoyou.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.frommetoyou.common.LifecycleEventLogger
import com.frommetoyou.common.UiText
import com.frommetoyou.core.R
import com.frommetoyou.domain.model.Dog

@Composable
fun DogsScreen(
    modifier: Modifier = Modifier,
    viewModel: DogsViewModel = hiltViewModel()
) {

    LifecycleEventLogger("DogsScreen") { screenName, event ->
        viewModel.addEvent(screenName, event)
    }

    val dog = viewModel.dog.collectAsState()
    val breed = viewModel.breed.collectAsState()
    val loading = viewModel.loading.collectAsState()

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(scrollState),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = UiText.StringResource(R.string.dogs_0).asString(context),
            fontSize = 24.sp
        )
        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = UiText.StringResource(R.string.dogs_1).asString(context)
        )
        Card(
            modifier = Modifier.padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardDefaults.cardColors().containerColor.copy(
                    alpha = 0.5f
                )
            ),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (loading.value)
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    else
                        AsyncImage(
                            modifier = Modifier
                                .size(375.dp)
                                .align(Alignment.CenterHorizontally)
                                .clip(RoundedCornerShape(16.dp)),
                            model = ImageRequest.Builder(context)
                                .data(dog.value.image)
                                .crossfade(true)
                                .build(),
                            contentDescription = UiText.StringResource(R.string.description)
                                .asString(context),
                            contentScale = ContentScale.Crop,
                        )
                }

                BreedSelector(
                    text = LocalContext.current.getString(R.string.select_breed),
                    Dog.allBreeds(),
                    selectedItem = breed.value,
                ) { breed ->
                    viewModel.setBreed(breed)
                }
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    enabled = true,
                    colors = ButtonDefaults.buttonColors(containerColor = if (true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        viewModel.getRandomDog()
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_random),
                            contentDescription = UiText.StringResource(R.string.description)
                                .asString(context),
                        )
                        Text(
                            UiText.StringResource(R.string.randomize)
                                .asString(context)
                        )
                    }
                }
            }
        }
    }
}
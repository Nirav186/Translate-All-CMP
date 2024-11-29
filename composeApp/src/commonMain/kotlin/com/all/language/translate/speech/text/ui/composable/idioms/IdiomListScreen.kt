package com.all.language.translate.speech.text.ui.composable.idioms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.data.model.IdiomData
import com.all.language.translate.speech.text.ui.composable.components.EmptyScreen
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

class IdiomListScreen(private val type: String) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val idiomViewModel = rememberScreenModel { IdiomViewModel() }
        LaunchedEffect(true) { idiomViewModel.initMain() }
        IdiomListScreenContent(
            type = type,
            idiomViewModel = idiomViewModel,
            goBack = {
                navigator.pop()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdiomListScreenContent(
    type: String,
    idiomViewModel: IdiomViewModel,
    goBack: () -> Boolean
) {

    val idiomsDataList by idiomViewModel.getIdiomData(type).collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        goBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "setting"
                        )
                    }
                },
                title = { Text(text = type, fontWeight = FontWeight.Medium) },
            )
        }
    ) {
        if (idiomsDataList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.sdp)
            ) {
                items(idiomsDataList) { idiom ->
                    IdiomListItem(
                        idiom = idiom,
                    )
                }
            }
        } else {
            EmptyScreen(modifier = Modifier.padding(it))
        }
    }
}


@Composable
fun IdiomListItem(
    modifier: Modifier = Modifier,
    idiom: IdiomData.IdiomCategoryData.Idiom,
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.sdp),
        elevation = CardDefaults.cardElevation(1.sdp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
        ),
        shape = RoundedCornerShape(8.sdp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp)
        ) {
            Text(
                text = "Idioms",
                fontWeight = FontWeight.Bold,
                fontSize = 14.ssp
            )
            Text(
                text = idiom.idiom,
                fontSize = 12.ssp
            )
            Spacer(modifier = Modifier.height(5.sdp))
            Text(
                text = "Define",
                fontWeight = FontWeight.Bold,
                fontSize = 14.ssp
            )
            Text(
                text = idiom.define,
                fontSize = 12.ssp
            )
            Spacer(modifier = Modifier.height(5.sdp))
            Text(
                text = "Example",
                fontWeight = FontWeight.Bold,
                fontSize = 14.ssp
            )
            Text(
                text = idiom.ex,
                fontSize = 12.ssp
            )
        }
    }
}

package com.translate.ui.composable.idioms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.ui.composable.components.EmptyScreen
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

class IdiomScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val idiomViewModel = rememberScreenModel { IdiomViewModel() }
        LaunchedEffect(true) { idiomViewModel.initMain() }
        IdiomsScreenContent(
            idiomViewModel = idiomViewModel,
            goToIdiomList = { type ->
                navigator.push(IdiomListScreen(type))
            },
            goBack = {
                navigator.pop()
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdiomsScreenContent(
    idiomViewModel: IdiomViewModel,
    goToIdiomList: (String) -> Unit,
    goBack: () -> Boolean,
) {

    val idiomsList by idiomViewModel.idiomCategory.collectAsState()

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
                title = { Text(text = "Idioms", fontWeight = FontWeight.Medium) },
            )
        }
    ) {
        if (idiomsList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.sdp)
            ) {
                items(idiomsList) { idiom ->
                    IdiomItem(
                        idiom = idiom,
                        onClick = { goToIdiomList(idiom) }
                    )
                }
            }
        } else {
            EmptyScreen(modifier = Modifier.padding(it))
        }
    }


}

@Composable
fun IdiomItem(
    modifier: Modifier = Modifier,
    idiom: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.sdp),
        elevation = CardDefaults.cardElevation(1.sdp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.dp)
        ),
        onClick = onClick,
        shape = RoundedCornerShape(8.sdp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.ssp)) {
                        append(idiom)
                    }
                }
            )
            IconButton(
                modifier = Modifier.size(20.sdp),
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                onClick = onClick
            ) {
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .size(14.sdp),
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = "",
                )
            }
        }
    }
}

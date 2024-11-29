package com.translate.ui.composable.quotes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Translate
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.lerp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.data.model.History
import com.translate.data.model.Quote
import com.translate.shareText
import com.translate.theme.getMontBFont
import com.translate.ui.composable.components.EmptyScreen
import com.translate.ui.composable.components.TypewriterText
import com.translate.ui.composable.translate.TranslateScreen
import com.translate.utils.getQuoteIcon
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.quotes
import kotlin.math.absoluteValue

class QuotesListScreen(val quote: Quote) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        QuotesListScreenContent(
            quote = quote,
            onBackPress = {
                navigator.pop()
            },
            navigateToTranslate = {
                navigator.push(TranslateScreen(History.empty().copy(originalText = it)))
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun QuotesListScreenContent(
    quote: Quote,
    onBackPress: () -> Unit,
    navigateToTranslate: (String) -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                },
                title = { Text(text = quote.name) },
            )
        }
    ) {
        if (quote.quotes.isNotEmpty()) {
            val pagerState =
                rememberPagerState(initialPage = 0, pageCount = { quote.quotes.size })
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color = Color(0xFFf1f1f1)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val icon by remember { mutableStateOf(getQuoteIcon(quote.name)) }

                Spacer(modifier = Modifier.height(50.sdp))
                Box(
                    modifier = Modifier
                        .padding(4.sdp)
                        .size(78.sdp)
                        .clip(RoundedCornerShape(50))
                        .background(color = MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier.size(74.sdp)
                    ) {
                        icon?.let {
                            Image(
                                painter = painterResource(it),
                                contentDescription = quote.name + "image"
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(50.sdp))
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    Card(
                        Modifier
                            .padding(horizontal = 16.sdp)
                            .fillMaxWidth()
                            .height(250.sdp)
                            .graphicsLayer {
                                val pageOffset =
                                    ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            },
                        elevation = CardDefaults.cardElevation(1.sdp)
                    )
                    {
                        QuotePageItem(
                            quote.quotes[page],
                            navigateToTranslate
                        )
                    }
                }
            }
        } else {
            EmptyScreen(modifier = Modifier.padding(it))
        }
    }
}

@Composable
fun QuotePageItem(
    text: String,
    navigateToTranslate: (String) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp, vertical = 4.sdp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(32.sdp),
                painter = painterResource(Res.drawable.quotes),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 10.sdp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TypewriterText(
                text = text,
                style = TextStyle(
                    fontFamily = getMontBFont(),
                    fontSize = 14.ssp,
                    textAlign = TextAlign.Center
                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.sdp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.sdp)
                    .scale(scaleX = -1f, scaleY = 1f),
                painter = painterResource(Res.drawable.quotes),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.sdp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                // copy text
                clipboardManager.setText(AnnotatedString(text = text))
            }) {
                Icon(
                    imageVector = Icons.Filled.ContentCopy,
                    contentDescription = "copy",
                )
            }
            IconButton(onClick = {
                shareText(text = text)
            }) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "copy",
                )
            }
            IconButton(onClick = {
                navigateToTranslate(text)
            }) {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    imageVector = Icons.Filled.Translate,
                    contentDescription = "copy",
                )
            }
        }
    }
}

package com.all.language.translate.speech.text.ui.composable.quotes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.all.language.translate.speech.text.data.model.Quote
import com.all.language.translate.speech.text.theme.getMontRFont
import com.all.language.translate.speech.text.theme.getMontSBFont
import com.all.language.translate.speech.text.ui.composable.components.EmptyScreen
import com.all.language.translate.speech.text.utils.getQuoteIcon
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource


class QuotesScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val quoteViewModel = rememberScreenModel { QuotesViewModel() }
        QuotesScreenContent(
            quoteViewModel = quoteViewModel,
            onBackPress = {
                navigator.pop()
            },
            onItemClick = {
                navigator.push(
                    QuotesListScreen(it)
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesScreenContent(
    quoteViewModel: QuotesViewModel,
    onBackPress: () -> Unit,
    onItemClick: (Quote) -> Unit
) {

    val quoteList by quoteViewModel.quoteList.collectAsState()

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
                title = { Text(text = "Famous Quotes") },
            )
        }
    ) {
        if (quoteList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Color(0xFFf1f1f1)),
                verticalArrangement = Arrangement.spacedBy(4.sdp),
                contentPadding = PaddingValues(4.sdp)
            ) {
                items(quoteList.sortedBy { itm -> itm.name }) { quote ->
                    QuoteItem(
                        quoteAuthor = quote.name,
                        duration = quote.dates,
                        onClick = {
                            onItemClick(quote)
                        }
                    )
                }
            }
        } else {
            EmptyScreen(modifier = Modifier.padding(it))
        }
    }
}


@Composable
fun QuoteItem(
    modifier: Modifier = Modifier,
    quoteAuthor: String,
    duration: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 4.sdp),
        elevation = CardDefaults.cardElevation(2.sdp),
        onClick = onClick,
        shape = RoundedCornerShape(2.sdp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(8.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(52.sdp)
                    .clip(RoundedCornerShape(50))
                    .background(color = MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier.size(48.sdp)
                ) {
                    getQuoteIcon(quoteAuthor)?.let {
                        Image(
                            painter = painterResource(it),
                            contentDescription = quoteAuthor + "image"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(5.sdp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = quoteAuthor,
                    fontSize = 16.ssp,
                    fontFamily = getMontSBFont()
                )
                Text(
                    text = duration,
                    fontWeight = FontWeight.ExtraLight,
                    fontFamily =getMontRFont()
                )
            }
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

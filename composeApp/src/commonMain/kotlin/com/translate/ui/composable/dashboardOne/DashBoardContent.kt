package com.translate.ui.composable.dashboardOne

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.twotone.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.translate.data.storage.KeyValueStorage
import com.translate.data.storage.KeyValueStorageImpl
import com.translate.ui.composable.components.RotatingSwapIcon
import com.translate.utils.Constant
import com.translate.utils.getDrawableByName
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import translate.composeapp.generated.resources.Res
import translate.composeapp.generated.resources.icn_dictionary
import translate.composeapp.generated.resources.icn_favorite
import translate.composeapp.generated.resources.icn_feedback
import translate.composeapp.generated.resources.icn_history
import translate.composeapp.generated.resources.icn_idioms
import translate.composeapp.generated.resources.icn_instant_translation
import translate.composeapp.generated.resources.icn_quotes
import translate.composeapp.generated.resources.icn_rate

@ExperimentalMaterial3Api
@Composable
fun DashBoardContent(
    dashBoardViewModel: DashBoardViewModel,
    navigateToLanguageSelection: (Boolean) -> Unit,
    navigateToTranslateScreen: () -> Unit,
    navigateToHistoryScreen: () -> Unit,
    navigateToIdiomsListScreen: () -> Unit,
    navigateToFavoriteScreen: () -> Unit,
    navigateToDictionaryScreen: () -> Unit,
    navigateToQuotesScreen: () -> Unit,
) {
    val keyValueStorage: KeyValueStorage = KeyValueStorageImpl()
    val selectedFromLang by keyValueStorage.observableFromLanguage.collectAsState(initial = Constant.languageList.first())
    val selectedToLang by keyValueStorage.observableToLanguage.collectAsState(initial = Constant.languageList.first())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.surface,
                    actionIconContentColor = MaterialTheme.colorScheme.surface,
                    navigationIconContentColor = MaterialTheme.colorScheme.surface
                ),
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "setting")
                    }
                },
                title = { Text(text = "Translator", fontWeight = FontWeight.Medium) },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier
                    .padding(8.sdp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DummyDropDown(
                    modifier = Modifier.weight(1f),
                    language = selectedFromLang.name,
                    iconRes = selectedFromLang.iconResId,
                    bgColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
                    onClick = {
                        navigateToLanguageSelection(true)
                    }
                )
                RotatingSwapIcon(
                    dashBoardViewModel = dashBoardViewModel,
                    selectedFromLang = selectedFromLang,
                    selectedToLang = selectedToLang
                )
                DummyDropDown(
                    modifier = Modifier.weight(1f),
                    language = selectedToLang.name,
                    iconRes = selectedToLang.iconResId,
                    bgColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f),
                    onClick = {
                        navigateToLanguageSelection(false)
                    }
                )
            }
            Card(
                modifier = Modifier.padding(horizontal = 8.sdp),
                elevation = CardDefaults.cardElevation(1.sdp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.sdp)
                ),
                shape = RoundedCornerShape(12.sdp),
                onClick = {
                    navigateToTranslateScreen()
                }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.sdp)
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 16.sdp, horizontal = 8.sdp),
                        text = "Enter Text ..",
                        style = TextStyle(
                            fontSize = 16.ssp,
                            color = Color.Gray.copy(.8f)
                        )
                    )
                    Surface(
                        modifier = Modifier
                            .padding(8.sdp)
                            .align(Alignment.BottomEnd)
                            .clickable(onClick = {

                            }),
                        tonalElevation = 8.sdp,
                        shadowElevation = 0.sdp,
                        shape = RoundedCornerShape(50),
                        color = MaterialTheme.colorScheme.primary.copy(alpha = .2f)
                    ) {
                        Icon(
                            modifier = Modifier
                                .padding(8.sdp)
                                .size(20.sdp),
                            imageVector = Icons.TwoTone.Mic,
                            contentDescription = "mic",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            Row {
                DashBoardPageItem2(
                    modifier = Modifier.weight(1f),
                    startImg = Res.drawable.icn_dictionary,
                    bgColor = Color(0xFFe4f2ed),
                    textColor = Color(0xFF32574a),
                    title = "Dictionary\n",
                    onClick = { navigateToDictionaryScreen() }
                )
                DashBoardPageItem2(
                    modifier = Modifier.weight(1f),
                    startImg = Res.drawable.icn_instant_translation,
                    bgColor = Color(0xFFebf3fe),
                    textColor = Color(0xFF64593f),
                    title = "Instant\nTranslation",
                    onClick = {}
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.sdp)
            ) {
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_quotes,
                    bgColor = Color(0x19d28eea),
                    textColor = Color(0xFF6f557a),
                    title = "Quote",
                    onClick = {
                        navigateToQuotesScreen()
                    }
                )
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_idioms,
                    bgColor = Color(0xFFf0f5e6),
                    textColor = Color(0xFF5e664f),
                    title = "Idioms",
                    onClick = { navigateToIdiomsListScreen() }
                )
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_history,
                    bgColor = Color(0x2674cbfd),
                    textColor = Color(0xFF415968),
                    title = "History",
                    onClick = { navigateToHistoryScreen() }
                )
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_favorite,
                    bgColor = Color(0x19fe5364),
                    textColor = Color(0xFF684143),
                    title = "Favourite",
                    onClick = { navigateToFavoriteScreen() }
                )
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_feedback,
                    bgColor = Color(0x198699ff),
                    textColor = Color(0xFF4f5574),
                    title = "Feedback",
                    onClick = {}
                )
                DashBoardPageItem1(
                    startImg = Res.drawable.icn_rate,
                    bgColor = Color(0x33fdc956),
                    textColor = Color(0xFF64593f),
                    title = "Rate Us",
                    onClick = {}
                )
            }
        }
    }
}


@Composable
fun DashBoardPageItem1(
    modifier: Modifier = Modifier,
    startImg: DrawableResource,
    bgColor: Color,
    textColor: Color? = null,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.sdp)
            .clip(RoundedCornerShape(12.sdp))
            .fillMaxWidth()
            .background(bgColor)
            .clickable(
                onClick = onClick
            )
    ) {
        Row(
            modifier = Modifier.padding(10.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 10.sdp)
                    .size(28.sdp),
                painter = painterResource(startImg),
                contentDescription = null
            )
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.ssp,
                    color = textColor ?: MaterialTheme.colorScheme.surface
                )
            )
        }
    }
}

@Composable
fun DashBoardPageItem2(
    modifier: Modifier = Modifier,
    startImg: DrawableResource,
    bgColor: Color,
    textColor: Color? = null,
    title: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(horizontal = 4.sdp, vertical = 4.sdp)
            .clip(RoundedCornerShape(12.sdp))
            .fillMaxWidth(.5f)
            .background(bgColor)
            .clickable(
                onClick = onClick
            )
    ) {
        Column(
            modifier = Modifier
                .padding(20.sdp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .padding(horizontal = 10.sdp)
                    .size(48.sdp),
                painter = painterResource(startImg),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(10.sdp))
            Text(
                text = title,
                style = TextStyle(
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.ssp,
                    textAlign = TextAlign.Center,
                    color = textColor ?: MaterialTheme.colorScheme.surface
                )
            )
//            endIcon?.let {
//                AsyncImage(model = it, contentDescription = title)
//            }
        }
    }
}

@Composable
fun DummyDropDown(
    modifier: Modifier = Modifier,
    language: String,
    iconRes: String?,
    bgColor: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.sdp))
            .background(bgColor)
            .clickable(
                onClick = onClick
            )
    ) {
        Row(
            modifier = modifier.padding(horizontal = 8.sdp, vertical = 4.sdp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            iconRes?.getDrawableByName()?.let { resource ->
                Image(
                    modifier = Modifier
                        .padding(vertical = 2.sdp)
                        .size(28.sdp)
                        .padding(4.sdp),
                    painter = painterResource(resource),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.weight(1f),
                text = language,
                fontWeight = FontWeight.Medium,
                fontSize = 14.ssp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.width(2.sdp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "drop down")
        }
    }
}
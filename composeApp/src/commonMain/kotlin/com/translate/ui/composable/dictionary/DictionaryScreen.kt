package com.translate.ui.composable.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.translate.data.model.DictionaryResponseItem
import com.translate.ui.composable.components.CustomSearchBar
import com.translate.ui.composable.components.DictionaryResponseCard
import com.translate.ui.composable.components.MeaningItems
import com.translate.utils.Result
import multiplatform.network.cmptoast.showToast
import network.chaintech.sdpcomposemultiplatform.sdp


class DictionaryScreen() : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow
        val dictionaryViewModel = rememberScreenModel { DictionaryViewModel() }
        DictionaryScreenContent(
            dictionaryViewModel = dictionaryViewModel,
            navigateBack = { navigator.pop() }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreenContent(
    dictionaryViewModel: DictionaryViewModel,
    navigateBack: () -> Unit
) {
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
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = { Text(text = "Dictionary", fontWeight = FontWeight.Medium) },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFf1f1f1))
                .padding(innerPadding)
        ) {
            item {
                CustomSearchBar(
                    onSearch = {
                        dictionaryViewModel.onDictionaryEvent(DictionaryEvent.OnSearch)
                    },
                    onQueryChange = {
                        dictionaryViewModel.onDictionaryEvent(DictionaryEvent.OnQueryChange(it))
                    },
                    onClearQuery = {
                        dictionaryViewModel.onDictionaryEvent(DictionaryEvent.OnQueryClear)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.sdp, vertical = 8.sdp),
                    query = dictionaryViewModel.searchQuery.collectAsState().value
                )
            }

            item {
                when (dictionaryViewModel.dictionaryResponseState.collectAsState().value) {
                    is Result.Error -> {
                        val error =
                            dictionaryViewModel.dictionaryResponseState.value as Result.Error
                        showToast(error.error.data)
                    }

                    Result.Idle -> {}
                    is Result.Success -> {
                        val data =
                            (dictionaryViewModel.dictionaryResponseState.value as Result.Success<DictionaryResponseItem?>).data

                        DictionaryResponseCard(
                            modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp),
                            word = data?.word ?: "",
                            phonetic = data?.phonetic ?: "",
                        )

                        data?.meanings?.let {
                            it.forEach { meaning ->
                                MeaningItems(
                                    modifier = Modifier.padding(horizontal = 8.sdp, vertical = 4.sdp),
                                    meaning = meaning
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.all.language.translate.speech.text.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider

//@ExperimentalMaterial3Api
//@Composable
//fun RootNavigation(
//    navHostController: NavHostController
//) {
//    val quoteViewModel: QuotesViewModel = hiltViewModel()
//    val languageSelectionViewModel: LanguageSelectionViewModel = hiltViewModel()
//    val historyViewModel: HistoryViewModel = hiltViewModel()
//
//    NavHost(
//        modifier = Modifier.fillMaxSize(),
//        navController = navHostController,
//        startDestination = Screens.SplashScreen
//    ) {
////        composable<Screens.DashBoardOne> {
////            DashBoardOne(
////                navController = navHostController,
////            )
////        }
////        composable<Screens.DashBoardTwo> {
////            DashBoardTwo(
////                navController = navHostController,
////            )
////        }
////
////        composable<Screens.TranslatePage> {
////            TranslateScreen(
////                navHostController = navHostController
////            )
////        }
//
//        composable<Screens.SplashScreen> {
//            SplashScreen(navHostController = navHostController)
//        }
//
////        composable<Screens.OnBoard> {
////            OnBoardScreen(navHostController = navHostController)
////        }
////
////        composable<Screens.LanguageSelection> {
////            val isFrom = it.arguments?.getBoolean("isFrom") ?: false
////            LanguageSelection(
////                languageSelectionViewModel = languageSelectionViewModel,
////                isFrom = isFrom
////            ) {
////                navHostController.navigateUp()
////            }
////        }
////
////        composable<Screens.Quotes> {
////            QuotesScreen(
////                quoteViewModel = quoteViewModel,
////                onBackPress = {
////                    navHostController.navigateUp()
////                }
////            )
////        }
////
////        composable<Screens.History> {
////            HistoryScreen(
////                historyViewModel = historyViewModel,
////                navHostController = navHostController
////            )
////        }
////
////        composable<Screens.Favorite> {
////            FavoriteScreen(
////                historyViewModel = historyViewModel,
////                navHostController = navHostController
////            )
////        }
//    }
//}

//@kotlinx.serialization.Serializable
//sealed class Screens {
//    @kotlinx.serialization.Serializable
//    object DashBoardOne
//
//    @kotlinx.serialization.Serializable
//    object DashBoardTwo
//
//    @kotlinx.serialization.Serializable
//    object TranslatePage
//
//    @kotlinx.serialization.Serializable
//    data class LanguageSelection(
//        val isFrom: Boolean = false
//    )
//
//    @kotlinx.serialization.Serializable
//    object OnBoard
//
//    @kotlinx.serialization.Serializable
//    object SplashScreen
//
//    @kotlinx.serialization.Serializable
//    object Quotes
//
//    @kotlinx.serialization.Serializable
//    object History
//
//    @kotlinx.serialization.Serializable
//    object Favorite
//}

sealed class SharedScreen : ScreenProvider {
    object SplashScreen : SharedScreen()
    object HomeScreen : SharedScreen()
//    data class PostDetails(val id: String) : SharedScreen()
}
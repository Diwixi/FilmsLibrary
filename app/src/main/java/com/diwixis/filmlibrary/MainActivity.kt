package com.diwixis.filmlibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.view.WindowCompat
import com.diwixis.filmlibrary.presentation.FlApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent { FlApp(windowSizeClass = calculateWindowSizeClass(this)) }

//        setContent {
//            FilmLybraryTheme {
//                Surface(color = MaterialTheme.colorScheme.background) {
//                    NetworkErrorWrapper {
//                        AuthorizedGraph()
//                    }
////                    Box(modifier = Modifier.fillMaxSize()) {
////                        TestBS()
////                    }
//                }
//            }
//        }
    }
}


//--------------TEST---------------
//@Composable
//fun TestBS() {
//    val scope = rememberCoroutineScope()
//    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
//    Box(contentAlignment = Alignment.Center) {
//        Button(onClick = {
//            scope.launch {
//                if (sheetState.isVisible) sheetState.hide()
//                else sheetState.show()
//            }
//        }) {
//            Text(text = if (sheetState.isVisible) "Hide BS" else "Show BS")
//        }
//    }
//    SheetModal(sheetState, onDismiss = {
//        scope.launch {
//            sheetState.hide()
//        }
//    })
//}
//
//@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
//@Composable
//fun SheetModal(
//    sheetState: ModalBottomSheetState,
//    onDismiss: () -> Unit
//) {
//    if (sheetState.isVisible) {
//        Dialog(
//            onDismissRequest = onDismiss,
//            properties = DialogProperties(usePlatformDefaultWidth = false)
//        ) {
//            ModalBottomSheetLayout(
//                sheetState = sheetState,
//                sheetContent = {
//                    repeat(31) { index ->
//                        Text("Line number $index")
//                    }
//                },
//                content = {}
//            )
//        }
//    }
//}
//
////----------------------TEST 2
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun TestBS2() {
////    val scope = rememberCoroutineScope()
////    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
//    var show by remember { mutableStateOf(false) }
//    Box(contentAlignment = Alignment.Center) {
//        Button(onClick = {
//            show = true
//        }) {
//            Text(text = "Show BS")
//        }
//    }
//    SheetModal2(show, onDismiss = { show = false })
//}
//
//@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
//@Composable
//fun SheetModal2(
//    show: Boolean,
//    onDismiss: () -> Unit
//) {
//    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
//    val scope = rememberCoroutineScope()
//
//    LaunchedEffect(key1 = show) {
//        scope.launch {
//            if (show) state.show()
//            else state.hide()
//        }
//    }
//
//    if (state.isVisible) {
//        Dialog(
//            onDismissRequest = onDismiss,
//            properties = DialogProperties(usePlatformDefaultWidth = false)
//        ) {
//            ModalBottomSheetLayout(
//                sheetState = state,
//                sheetContent = {
//                    repeat(31) { index ->
//                        Text("Line number $index")
//                    }
//                },
//                content = {}
//            )
//        }
//    }
//}

//@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
//@Composable
//private fun SheetModal(
//    state: ModalBottomSheetState,
//    onDismiss: () -> Unit,
//    shape: RoundedCornerShape,
//    padding: PaddingValues,
//    backgroundColor: Color,
//    content: @Composable () -> Unit,
//    modifier: Modifier,
//) {
//    val scope = rememberCoroutineScope()
//    Dialog(
//        onDismissRequest = {
//            scope.launch { state.hide() }
//        },
//        properties = DialogProperties(usePlatformDefaultWidth = false)
//    ) {
//        LaunchedEffect(Unit) {
//            scope.launch { state.show() }
//        }
//
//        // to make sure that we aren't in the initial state we observe the changes in the state.targetValue
//        // if target state is different to the current state it means a transition had started
//        val isInitialState = remember { mutableStateOf(true) }
//        LaunchedEffect(state.targetValue) {
//            if (state.targetValue != state.currentValue) {
//                isInitialState.value = false
//            }
//        }
//
//        // we must call the onDismiss callback when the transition of state finishes
//        LaunchedEffect(state.isAnimationRunning, state.currentValue) {
//            when {
//                // ignore the update if the animation is in progress or no transition have ever been requested
//                state.isAnimationRunning || isInitialState.value -> return@LaunchedEffect
//                state.currentValue == ModalBottomSheetValue.Hidden -> onDismiss()
//            }
//        }
//
//        ModalBottomSheetLayout(
//            modifier = modifier,
//            backgroundColor = backgroundColor,
//            sheetShape = shape,
//            sheetState = state,
//            padding = padding,
//            scrimColor = Colors.Black.copy(alpha = 1f - ModalTokens.shadowOpacity)
//        ) {
//            content()
//        }
//    }
//}
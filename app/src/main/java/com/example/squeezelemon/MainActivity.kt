package com.example.squeezelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.squeezelemon.ui.theme.SqueezeLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SqueezeLemonTheme {
                SqueezeLemonApp()
            }
        }
    }
}

@Preview
@Composable
fun SqueezeLemonApp(){
    val currentScreen = remember {
        mutableStateOf(1)
    }
    val numberOfClicks = when(currentScreen.value){
        1 -> 1
        2 -> (2..3).random()
        else -> 1
    }
    val goto = when(currentScreen.value){
        1 -> 2
        2 -> 1
        else -> 1
    }

    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        SqueezeStack(goto = goto, currentScreen = currentScreen, numberOfClicks = numberOfClicks)
    }
}

@Composable
fun SqueezeStack(goto: Int, numberOfClicks: Int, currentScreen: MutableState<Int>){
    val countClicks = remember {
        mutableStateOf(0)
    }

    if (countClicks.value == numberOfClicks) {
        currentScreen.value = goto
        countClicks.value = 0
    }


    when(currentScreen.value){
        1 -> LemonTree{countClicks.value++}
        2 -> LemonSqueeze{countClicks.value++}/*
        else -> LemonTree{countClicks.value++}*/
    }

}

@Composable
fun LemonTree(onClick: () -> Unit){
    Column(modifier = Modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally,
    ) {
        Text(text = "Tap the lemon tree to select a lemon")
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.lemon_tree),
            contentDescription = "Lemon tree",
            Modifier.clickable{
                onClick()
            }
        )
    }
}

@Composable
fun LemonSqueeze(onClick: () -> Unit){
    Column(modifier = Modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Text(text = "Keep tapping the lemon to squeeze it")
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.lemon_squeeze), contentDescription = "Lemon",
            Modifier.clickable{
                onClick()
            }
        )
    }
}
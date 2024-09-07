package com.example.digitallemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.digitallemonade.ui.theme.DigitalLemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DigitalLemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp(
    modifier: Modifier = Modifier
) {
    var step by remember { mutableIntStateOf(1) }
    val imageResource = when(step){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val stepDescription = when(step) {
        1 -> R.string.step1
        2 -> R.string.step2
        3 -> R.string.step3
        else -> R.string.step4
    }
    val imgDescription = when(step) {
        1 -> R.string.lemonTree
        2 -> R.string.lemon
        3 -> R.string.glassOfLemonade
        else -> R.string.emptyGlass
    }
    var squeezeCount by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lemonade",
            fontSize = 40.sp,
            modifier = Modifier.padding(60.dp),
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            when(step) {
                                1 -> {
                                    step = 2
                                    squeezeCount = (2..4).random()
                                }
                                2 -> {
                                    squeezeCount--
                                        if (squeezeCount == 0) {
                                            step = 3
                                        }

                                }
                                3 -> {
                                    step = 4
                                }
                                else -> {
                                    step = 1
                                }
                            }
                        }
                    )
                    .padding(16.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .background(
                        Color.Gray,
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .size(200.dp, 220.dp),
                painter = painterResource(imageResource),
                contentDescription = stringResource(imgDescription)
            )
            Text(
                modifier = Modifier.padding(16.dp),
                fontSize = 17.sp,
                text = stringResource(stepDescription)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LemonadeAppPreview() {
    DigitalLemonadeTheme {
        LemonadeApp()
    }
}
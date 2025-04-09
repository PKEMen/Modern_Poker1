package com.example.modern_poker1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Poker_UI : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContent {
            //PokerTable()
        }
    }


@Composable
fun AvatarImages() {
    Box(
        modifier = Modifier
            .size(80.dp)
            .padding(16.dp)
            .background(Color.Blue)
    )
}

@Composable
fun CardImages() {
    //Placeholder for 3 cards in the middle (Will revel with each hand)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 380.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //placeHolder for card 1
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        //placeholder for card 2
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(2.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        //placeholder for card 3
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(120.dp)
                .background(Color.White)
                .border(2.dp, Color.Black, RoundedCornerShape(2.dp))
        )
    }
}


@Composable
fun AvatarRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Replace with actual avatar images
        AvatarImages()
        AvatarImages()
        AvatarImages()
        AvatarImages()
    }
}
@Composable

fun PokerChips() {
    Box(
        modifier = Modifier
        //.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.Red)
                .padding(100.dp)
            //.padding(start = 40.dp, top = 680.dp)
        ) {
            Box (
                modifier = Modifier
                    .size(79.dp)
                    .clip(CircleShape)
                    .background(Color.Green)
            )
        }
    }
}
@Composable
fun PokerTable() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val ovalWidth = size.width * 0.9f
        val ovalHeight = size.height * 0.6f
        val borderThickness = 50f
        //Create the brush
        val gradient = Brush.verticalGradient(
            colors = listOf(Color(0xFF648164),Color(0xFF2E3A2C))
        )

        drawOval(
            color = Color(0xFFCCA374),
            size = Size(ovalWidth + borderThickness, ovalHeight + borderThickness),
            topLeft = Offset(
                (size.width -ovalWidth - borderThickness) /2,
                size.height * 0.2f - (borderThickness)/2
            )
        )
        drawOval(
            brush = gradient,
            size = Size(ovalWidth, ovalHeight),
            topLeft = Offset(
                (size.width - ovalWidth) /2,
                size.height * 0.2f
            )
        )
    }
    CardImages()
}

@Composable
fun BackgroundWithButtons () {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color (0xFF545352),Color(0xFF8f857b))
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)//Brush.verticalGradient())
    ) {
        //PokerChips()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Buttons for Betting, raising and calling
            Button(
                onClick = { },
                modifier = Modifier
                    .border(3.dp, Color.Black, CircleShape),
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF937D07),
                    contentColor = Color.Black)
            ) {
                Text("Bet")
            }

            Button(
                onClick = { },
                modifier = Modifier
                    .border(3.dp,Color.Black, CircleShape),
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF937D07),
                    contentColor = Color.Black)
            ) {
                Text("Raise")
            }
            Button(onClick = { },
                modifier = Modifier
                    .border(3.dp, Color.Black, CircleShape),
                colors =  ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF937D07),
                    contentColor = Color.Black)
            ) {
                Text("Call")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackGroundWithButtons() {
    BackgroundWithButtons()
    PokerTable()
    AvatarRow()
    PokerChips()
}
package com.soarapp.create

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import coil.compose.AsyncImage
import com.soarapp.core.http.RequestClient
import com.soarapp.create.ui.theme.SoarAppTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SoarAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column {
                        Greeting("Android")
                        container(context = this@MainActivity)

                    }

                }
            }
        }

        GlobalScope.launch {
//            RequestClient().test()
        }
    }
}


@SuppressLint("UnrememberedMutableState", "CoroutineCreationDuringComposition")
@Composable
fun container(context:Context){
    Column(modifier = Modifier.verticalScroll(enabled = true, state = ScrollState(2), reverseScrolling = true)) {
        Text(text = "are you ok?")
        Image(painter = painterResource(id = R.mipmap.test), contentDescription = "" , modifier = Modifier
            .width(20.dp)
            .height(20.dp))
        ElevatedButton(onClick = {
            Toast.makeText(context, "this is textButton", Toast.LENGTH_SHORT).show()
        }, shape = CircleShape.copy(CornerSize(10.dp),CornerSize(2.dp),CornerSize(6.dp),CornerSize(7.dp))) {
            Text(text = "ElevatedButton" , color = colorResource(id = R.color.teal_700))
        }
        TextButton(onClick = {
            Toast.makeText(context, "this is textButton", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "TextButton" , color = colorResource(id = R.color.purple_200))
        }

        OutlinedButton(onClick = {

        }) {
            Text(text = "OutlinedButton" , color = colorResource(id = R.color.purple_200))

        }

        FilledTonalButton(onClick = {

        }) {
            Text(text = "FilledTonalButton" , color = colorResource(id = R.color.purple_200))
        }



        FilledIconButton(onClick = {

        }) {
            Text(text = "FilledIconButton" , color = colorResource(id = R.color.purple_200))
        }


        (0 .. 300).forEach {

            if(it == 200){
                list(context)
            }else {

                OutlinedButton(modifier = Modifier.fillMaxWidth(1f), onClick = {

                    Toast.makeText(context, "你点击了 ${it}", Toast.LENGTH_SHORT).show()

                    context.startActivity(Intent(context , MainActivity::class.java))
                }) {
                    Text(
                        text = it.toString(),
                        modifier = Modifier.size(200.dp, 20.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

        }


        Box(modifier = Modifier
            .fillMaxWidth(0.8f)

            ) {
            Text(text = "Box 测试一下", modifier = Modifier.size(200.dp , 20.dp) , textAlign = TextAlign.Center)

            OutlinedButton(onClick = {

            }) {
                Text(text = "这是一个OutlinedButton", modifier = Modifier.size(100.dp , 20.dp))
            }
        }


        AsyncImage(model = "https://img1.baidu.com/it/u=413643897,2296924942&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500", contentDescription = "")



        var text = remember{ mutableStateOf("测试一下") }

        Text(text.value)


        Text(text="click" , modifier = Modifier
            .clickable(enabled = true, onClickLabel = "测试") {
                text.value = "${text.value}--->1"
            }
            .width(100.dp)
            .height(50.dp)
            .background(colorResource(id = R.color.purple_200)))


        var showdialog = remember {
            mutableStateOf(false)
        }

        Text(text="测试dialog" , Modifier.clickable (enabled = true , onClickLabel = "dialog"){
            showdialog.value = true
        })


        if(showdialog.value) {
            Dialog(onDismissRequest = {
                text.value = "dialog dissmiss"
                showdialog.value = false
            },
                properties = DialogProperties(
                    dismissOnBackPress = false,
                    dismissOnClickOutside = true,
                    securePolicy = SecureFlagPolicy.Inherit
                )) {

                list(context = context)

            }
        }


    }
}


@Composable
fun list(context:Context){
    var messages = arrayListOf<String>()
    (1..200).forEach {
        messages.add("this is message---> ${it} ")
    }


    val state = rememberLazyListState()

    Row(modifier = Modifier
        .horizontalScroll(state = ScrollState(0), enabled = true, reverseScrolling = false)
        .height(400.dp)
        .background(color = colorResource(id = R.color.teal_700))
        ) {
        messages.forEach {
            Text(text = it, fontSize = 24.sp, modifier = Modifier
                .fillMaxWidth(1f)
                .width(200.dp)
                .align(Alignment.CenterVertically)
                .clickable(enabled = true) {
                    Toast
                        .makeText(context, "you click the mess", Toast.LENGTH_SHORT)
                        .show()
                }
//                .wrapContentWidth(Alignment.Start)
                .background(
                    colorResource(id = R.color.teal_200)
                ), textAlign = TextAlign.Center ,textDecoration = TextDecoration.Underline)
//            Spacer(modifier = Modifier
//                .height(1000.dp)
//                .background(colorResource(id = R.color.teal_200)))





            Divider(modifier = Modifier
                .fillMaxHeight(1f)
                .width(5.dp)
                .background(colorResource(id = R.color.purple_200)))
        }
    }

//    LazyRow(state = state, modifier = Modifier.horizontalScroll(state = ScrollState(0) , enabled = true , reverseScrolling = false)){
//
//
//        items(items = messages) { item ->
//            if (item != null) {
//                Text(text = item, fontSize = 24.sp)
//                Spacer(modifier = Modifier.height(4.dp))
//
//            }
//        }
//
//    }
}





fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = layout { measurable, constraints ->
    // Measure the composable
    val placeable = measurable.measure(constraints)

    // Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // Height of the composable with padding - first baseline
    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
    val height = placeable.height + placeableY
    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Text(text = "compose ")
}
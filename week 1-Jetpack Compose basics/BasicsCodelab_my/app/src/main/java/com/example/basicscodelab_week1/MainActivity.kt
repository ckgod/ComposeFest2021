package com.example.basicscodelab_week1

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab_week1.ui.theme.BasicsCodelab_week1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // setContent안 xml 대신 레이아웃을 구성할 Composable 함수가 들어갈 수 있다.
            BasicsCodelab_week1Theme {
                MyApp()
            }
        }
    }
}

@Composable
private fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnBoardingPreview() {
    BasicsCodelab_week1Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
private fun Greetings(names : List<String> = List(1000) {"$it"}) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable // 컴포저블 함수로 인식
fun Greeting(name: String) {
    // 여기에 rememberSaveable을 사용하면 아래로 스크롤하고 다시 위로올려도 확장된 상태가 유지된다.
    val expanded = remember { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if(expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) { // coerceAtLeast가 뭘까??
                // 애니메이션이 진행되면서 패딩이 음수값으로 가는것을 방지함 음수값으로 가버리면 앱터짐
                Text(text = "Hello,")
                Text(text = "$name !", style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                // h1 ~ h6 , body1, body2, caption, subtitle1
            }
            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }
}

@Composable
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    var shouldShowingOnboarding by rememberSaveable {
        mutableStateOf(true)
    }
    if(shouldShowingOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowingOnboarding = false})
    } else {
        Greetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicsCodelab_week1Theme {
        Greetings()
    }
}
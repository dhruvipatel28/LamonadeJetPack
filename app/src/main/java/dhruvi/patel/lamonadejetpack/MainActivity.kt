package dhruvi.patel.lamonadejetpack
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dhruvi.patel.lamonadejetpack.ui.theme.LamonadeJetPackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LamonadeJetPackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LetsMakeLemonade()
                }
            }
        }
    }
}

@Composable
fun LetsMakeLemonade(modifier: Modifier = Modifier) {


    var stage by remember {
        mutableStateOf(1)
    }

    //Switch Image
    val imageResource = when (stage) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }

    val stringResource = when(stage){
        1 -> R.string.tap_lemon
        2 -> R.string.squeeze_lemon
        3 -> R.string.drink_lemon
        4 -> R.string.start_over
        else -> R.string.tap_lemon
    }

    val randomSqueeze = (2..4).random()

    var totalClicks = 0



    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),

    ) {
        Image(
            painter = painterResource(imageResource),
            contentDescription = "Tree Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .clickable(onClick = {

                    totalClicks++

                    if(stage == 4)
                        stage = 1
                    else if(stage == 2){
                        if(totalClicks == randomSqueeze) {
                            stage+=1
                        }
                    }
                    else
                        stage += 1
                })
                .padding(30.dp)
                .clip(RoundedCornerShape(10))
                .background(Color(0xFFAFEBCA))
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(stringResource),
            fontSize = 40.sp,
            textAlign = TextAlign.Center,
            lineHeight = 45.sp,
            modifier = Modifier.fillMaxWidth(),
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LamonadeJetPackTheme {
        LetsMakeLemonade()
    }
}
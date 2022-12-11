package org.jetbrains.compose.resources.demo.shared

//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Backspace
//import androidx.compose.material.icons.filled.ExpandMore
//import androidx.compose.material.icons.filled.MoreVert
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.material3.lightColorScheme
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.RippleTheme.Companion
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
//import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.TextUnit
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp

//private val CalculationContainerColor = Color(229, 229, 240)
//private val KeyPinkColor = Color(255, 215, 244)
//private val KeyBlueColor = Color(222, 225, 249)
//private val KeyBlueColor2 = Color(221, 225, 255)
//private val KeyGrayColor = Color(245, 242, 249)

expect val OpenSansFontFamily: FontFamily

//@Composable
//private fun AppTheme(content: @Composable () -> Unit) = MaterialTheme(
//  lightColorScheme(
//    background = Color(254, 251, 255),
//  ),
//  content = content
//)

private object RippleCustomTheme: RippleTheme {

  //Your custom implementation...
  @Composable
  override fun defaultColor() =
    RippleTheme.defaultRippleColor(
      Color.Red,
      lightTheme = true
    )

  @Composable
  override fun rippleAlpha(): RippleAlpha {
    val contentColor = Color.Black
    val lightTheme = true
    return when {
      lightTheme -> {
        if (contentColor.luminance() > 0.5) {
          val LightThemeHighContrastRippleAlpha = RippleAlpha(
            pressedAlpha = 0.24f,
            focusedAlpha = 0f,
            draggedAlpha = 0.16f,
            hoveredAlpha = 0.08f
          )
          LightThemeHighContrastRippleAlpha
        } else {
          val LightThemeLowContrastRippleAlpha = RippleAlpha(
            pressedAlpha = 0.12f,
            focusedAlpha = 0f,
            draggedAlpha = 0.08f,
            hoveredAlpha = 0.04f
          )
          LightThemeLowContrastRippleAlpha
        }
      }
      else -> {
        val DarkThemeRippleAlpha = RippleAlpha(
          pressedAlpha = 0.10f,
          focusedAlpha = 0f,
          draggedAlpha = 0.08f,
          hoveredAlpha = 0.04f
        )
        DarkThemeRippleAlpha
      }
    }
  }
}

@Stable
private class MutableInteractionSourceImpl : MutableInteractionSource {
  override val interactions = MutableSharedFlow<Interaction>(
    extraBufferCapacity = 16,
    onBufferOverflow = BufferOverflow.DROP_OLDEST,
  )

  private var lastInteraction: Interaction? = null

  override suspend fun emit(interaction: Interaction) {
    if (interaction is FocusInteraction && lastInteraction is PressInteraction) {

    }
    when (interaction) {
      is FocusInteraction -> println("FocusInteraction")
      is HoverInteraction -> println("HoverInteraction")
      is PressInteraction -> println("PressInteraction")
      else -> println("Another Interaction")
    }
    interactions.emit(interaction)
  }

  override fun tryEmit(interaction: Interaction): Boolean {
    return interactions.tryEmit(interaction)
  }
}

@Composable
internal fun UseResources() {
  Column {
//    CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
      val interactionSource = remember { MutableInteractionSourceImpl() }
      TextButton(onClick = {}, interactionSource = interactionSource) {
        Text("Hello")
      }
      TextButton(onClick = {}) {
        Text("World")
      }
//    }
  }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//internal fun UseResources1() {
//  var text by remember { mutableStateOf("2+3") }
//  AppTheme {
//    Scaffold(
//      topBar = {
//        TopAppBar(
//          title = {},
//          Modifier.padding(top = 20.dp),
//          actions = {
//            IconButton(onClick = {}) {
//              Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
//            }
//          },
//          colors = TopAppBarDefaults.smallTopAppBarColors(CalculationContainerColor),
//        )
//      }) {
//      Column(
//        verticalArrangement = Arrangement.spacedBy(32.dp),
//      ) {
//        DisplayPanel(text, Modifier.weight(1f))
//        Keypad(
//          onKeyPress = { text = it.onPress(text) },
//          Modifier.weight(2f),
//        )
//      }
//    }
//  }
//}
//
//@Composable
//fun DisplayPanel(text: String, modifier: Modifier = Modifier) {
//  Column(
//    modifier
//      .fillMaxSize()
//      .clip(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp))
//      .background(CalculationContainerColor)
//      .padding(horizontal = 24.dp, vertical = 4.dp),
//    Arrangement.Bottom,
//    Alignment.End,
//  ) {
//    Text(
//      text,
////            Modifier.padding(bottom = 8.dp),
//      fontSize = 64.sp,
//      letterSpacing = 8.sp,
//    )
//    Text(
//      "5",
//      fontSize = 48.sp,
//    )
////          It seems like the color fades around its borders. Perhaps there's a material component for this?
//    Box(
//      Modifier
//        .align(Alignment.CenterHorizontally)
//        .size(24.dp, 10.dp)
//        .background(Color(67, 68, 76)),
//    )
//  }
//}
//
//sealed interface Key<T : Any> {
//  val display: T
//  val onPress: (text: String) -> String
//
//  data class Value(
//    override val display: String,
//    override val onPress: (text: String) -> String = { it + display },
//  ) : Key<String> {
//    val letterSpacing: TextUnit = 2.sp
//  }
//
//  data class Backspace(
//    override val display: ImageVector,
//  ) : Key<ImageVector> {
//    override val onPress: (text: String) -> String = { it.dropLast(1) }
//  }
//
//  data class Clear(
//    override val display: String,
//  ) : Key<String> {
//    val letterSpacing: TextUnit = 0.sp
//    override val onPress: (text: String) -> String = { "" }
//  }
//}
//
//@Composable
//fun Keypad(onKeyPress: (key: Key<*>) -> Unit, modifier: Modifier = Modifier) {
//  Column(modifier) {
//    Row(Modifier.padding(horizontal = 18.dp).height(40.dp), Arrangement.spacedBy(10.dp), Alignment.CenterVertically) {
//      Key(Key.Value("√"), onClick = onKeyPress)
//      Key(Key.Value("π"), onClick = onKeyPress)
//      Key(Key.Value("^"), onClick = onKeyPress)
//      Key(Key.Value("!"), onClick = onKeyPress)
//      TextButton(
//        onClick = { },
//        modifier = Modifier
//          .size(32.dp),
//        shape = CircleShape,
//        colors = ButtonDefaults.textButtonColors(containerColor = KeyGrayColor, contentColor = Color(red = 28, green = 27, blue = 31, (255 * .85).toInt())),
//        contentPadding = PaddingValues(9.dp),
//      ) {
//        Icon(Icons.Default.ExpandMore, null)
//      }
//    }
//    Column(
//      Modifier
//        .weight(1f)
//        .padding(16.dp, 14.dp),
//      Arrangement.spacedBy(10.dp),
//    ) {
//      Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Key(Key.Clear("AC"), onClick = onKeyPress, backgroundColor = KeyPinkColor)
//        Key(Key.Value("( )", onPress = { "$it(" }), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//        Key(Key.Value("%"), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//        Key(Key.Value("÷"), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//      }
//      Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Key(Key.Value("7"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("8"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("9"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("×"), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//      }
//      Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Key(Key.Value("4"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("5"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("6"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("-"), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//      }
//      Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Key(Key.Value("1"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("2"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("3"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("+"), onClick = onKeyPress, backgroundColor = KeyBlueColor)
//      }
//      Row(Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
//        Key(Key.Value("0"), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("∙", onPress = { "$it." }), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Backspace(Icons.Default.Backspace), onClick = onKeyPress, backgroundColor = KeyGrayColor)
//        Key(Key.Value("="), onClick = onKeyPress, backgroundColor = KeyBlueColor2)
//      }
//    }
//  }
//}
//
//@Composable
//fun RowScope.Key(
//  key: Key<*>,
//  onClick: (Key<*>) -> Unit,
//  backgroundColor: Color = Color.Unspecified,
//  height: Dp = Dp.Unspecified,
//) {
//  when (key) {
//    is Key.Backspace -> {
//      Key(
//        key,
//        onClick,
//        backgroundColor,
//        height,
//      ) {
//        Icon(key.display, null)
//      }
//    }
//    is Key.Clear -> Key(
//      key,
//      key.display,
//      onClick,
//      backgroundColor,
//      height,
//      key.letterSpacing,
//    )
//    is Key.Value -> Key(
//      key,
//      key.display,
//      onClick,
//      backgroundColor,
//      height,
//      key.letterSpacing,
//    )
//  }
//}
//
//@Composable
//fun RowScope.Key(
//  key: Key<*>,
//  text: String,
//  onClick: (Key<*>) -> Unit,
//  backgroundColor: Color = Color.Unspecified,
//  height: Dp = Dp.Unspecified,
//  letterSpacing: TextUnit = 2.sp,
//) {
//  Key(key, onClick, backgroundColor, height) {
//    Text(text, fontSize = 32.sp, fontWeight = FontWeight.Normal, fontFamily = OpenSansFontFamily, letterSpacing = letterSpacing)
//  }
//}
//
//@Composable
//fun RowScope.Key(
//  key: Key<*>,
//  onClick: (Key<*>) -> Unit,
//  backgroundColor: Color,
//  height: Dp,
//  content: @Composable () -> Unit,
//) {
//  TextButton(
//    { onClick(key) },
//    Modifier
//      .weight(1f)
//      .fillMaxHeight()
//      .height(height),
//    shape = CircleShape,
//    colors = ButtonDefaults.textButtonColors(containerColor = backgroundColor, contentColor = Color(red = 23, green = 27, blue = 44)),
//    contentPadding = PaddingValues(0.dp),
//  ) {
//    content()
//  }
//}

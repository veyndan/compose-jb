package org.jetbrains.compose.resource

import android.os.Build
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.detectEnvironment
import app.cash.paparazzi.androidHome
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import org.jetbrains.compose.resources.demo.shared.UseResources
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.reflect.Field
import java.lang.reflect.Modifier

@RunWith(TestParameterInjector::class)
class LaunchViewTest(
  @TestParameter private val parameter: Parameter,
) {
  @get:Rule
  val paparazzi = run {
    setFinalStatic(Build.VERSION::class.java.getField("CODENAME"), "REL")
    Paparazzi(
      deviceConfig = parameter.deviceConfig,
      maxPercentDifference = 1.0,
    )
  }

  @Test
  fun launchComposable() {
    paparazzi.snapshot {
      UseResources()
    }
  }

  enum class Parameter(val deviceConfig: DeviceConfig) {
    Pixel2(DeviceConfig.PIXEL_2.copy(softButtons = false)),
    Pixel4(DeviceConfig.PIXEL_4.copy(softButtons = false)),
    Tall(DeviceConfig.PIXEL_2.copy(softButtons = false, screenHeight = 6000))
  }
}

fun setFinalStatic(field: Field, newValue: Any) {
  Field::class.java.getDeclaredField("modifiers").apply {
    isAccessible = true
    setInt(field, field.modifiers and Modifier.FINAL.inv())
  }

  field.apply {
    isAccessible = true
    set(null, newValue)
  }
}

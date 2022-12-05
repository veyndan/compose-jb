/*
 * Copyright 2020-2022 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import org.jetbrains.compose.resources.demo.shared.UseResources
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        Window {
            MainView()
        }
    }
}

@Composable
fun MainView() {
    UseResources()
}

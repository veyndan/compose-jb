/*
 * Copyright 2020-2022 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package org.jetbrains.compose.resources.demo.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MainView() {
    UseResources()
}

actual val OpenSansFontFamily: FontFamily = FontFamily(
    Font(R.font.product_sans_bold, FontWeight.Bold),
    Font(R.font.product_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.product_sans_italic, style = FontStyle.Italic),
    Font(R.font.product_sans_regular),
)

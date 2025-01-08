/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2024 T8RIN (Malik Mukhametzyanov)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * You should have received a copy of the Apache License
 * along with this program.  If not, see <http://www.apache.org/licenses/LICENSE-2.0>.
 */

package ru.tech.imageresizershrinker.core.ui.widget.sliders

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import ru.tech.imageresizershrinker.core.settings.presentation.provider.LocalSettingsState
import ru.tech.imageresizershrinker.core.ui.theme.blend
import ru.tech.imageresizershrinker.core.ui.theme.outlineVariant
import ru.tech.imageresizershrinker.core.ui.theme.takeColorFromScheme
import ru.tech.imageresizershrinker.core.ui.utils.provider.SafeLocalContainerColor
import ru.tech.imageresizershrinker.core.ui.widget.modifier.container
import ru.tech.imageresizershrinker.core.ui.widget.sliders.custom_slider.CustomSlider
import ru.tech.imageresizershrinker.core.ui.widget.sliders.custom_slider.CustomSliderDefaults

@Composable
fun M2Slider(
    value: Float,
    enabled: Boolean,
    colors: SliderColors,
    interactionSource: MutableInteractionSource,
    modifier: Modifier,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)?,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    drawContainer: Boolean = true
) {
    val settingsState = LocalSettingsState.current
    CustomSlider(
        interactionSource = interactionSource,
        enabled = enabled,
        modifier = modifier
            .then(
                if (drawContainer) {
                    Modifier
                        .container(
                            shape = CircleShape,
                            autoShadowElevation = animateDpAsState(
                                if (settingsState.drawSliderShadows) {
                                    1.dp
                                } else 0.dp
                            ).value,
                            resultPadding = 0.dp,
                            borderColor = MaterialTheme.colorScheme
                                .outlineVariant(
                                    luminance = 0.1f,
                                    onTopOf = SwitchDefaults.colors().disabledCheckedTrackColor
                                )
                                .copy(0.3f),
                            color = SafeLocalContainerColor
                                .copy(0.3f)
                                .compositeOver(
                                    takeColorFromScheme {
                                        if (it) {
                                            tertiaryContainer
                                                .blend(
                                                    secondaryContainer,
                                                    0.5f
                                                )
                                                .copy(0.1f)
                                        } else {
                                            secondaryContainer
                                                .blend(
                                                    tertiaryContainer,
                                                    0.3f
                                                )
                                                .copy(0.2f)
                                        }
                                    }
                                )
                                .copy(colors.activeTrackColor.alpha),
                            composeColorOnTopOfBackground = false
                        )
                        .padding(horizontal = 12.dp)
                } else Modifier
            ),
        value = animateFloatAsState(
            targetValue = value,
            animationSpec = tween(100)
        ).value,
        colors = colors.toCustom(),
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        valueRange = valueRange,
        steps = steps,
        track = {
            CustomSliderDefaults.Track(
                sliderState = it,
                colors = colors.toCustom(),
                trackHeight = 4.dp,
                enabled = enabled
            )
        }
    )
}
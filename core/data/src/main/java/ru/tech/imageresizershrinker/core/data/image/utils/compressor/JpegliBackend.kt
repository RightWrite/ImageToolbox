/*
 * ImageToolbox is an image editor for android
 * Copyright (c) 2025 T8RIN (Malik Mukhametzyanov)
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

package ru.tech.imageresizershrinker.core.data.image.utils.compressor

import android.graphics.Bitmap
import io.github.awxkee.jpegli.coder.IccStrategy
import io.github.awxkee.jpegli.coder.JpegliCoder
import io.github.awxkee.jpegli.coder.Scalar
import ru.tech.imageresizershrinker.core.data.image.utils.ImageCompressorBackend
import ru.tech.imageresizershrinker.core.domain.image.model.Quality
import java.io.ByteArrayOutputStream

internal data object JpegliBackend : ImageCompressorBackend {

    override suspend fun compress(
        image: Bitmap,
        quality: Quality
    ): ByteArray = ByteArrayOutputStream().apply {
        use { out ->
            JpegliCoder.compress(
                bitmap = image,
                quality = quality.qualityValue,
                background = Scalar.ZERO,
                progressive = true,
                strategy = IccStrategy.DEFAULT,
                outputStream = out
            )
        }
    }.toByteArray()

}
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

package ru.tech.imageresizershrinker.feature.limits_resize.di

import android.graphics.Bitmap
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.tech.imageresizershrinker.core.domain.image.ImageScaler
import ru.tech.imageresizershrinker.feature.limits_resize.data.AndroidLimitsImageScaler
import ru.tech.imageresizershrinker.feature.limits_resize.domain.LimitsImageScaler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class LimitsResizeModule {

    @Provides
    @Singleton
    fun provideScaler(
        imageScaler: ImageScaler<Bitmap>
    ): LimitsImageScaler<Bitmap> = AndroidLimitsImageScaler(imageScaler)

}
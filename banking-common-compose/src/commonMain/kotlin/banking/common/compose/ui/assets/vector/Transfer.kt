package banking.common.compose.ui.assets.vector

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Butt
import androidx.compose.ui.graphics.StrokeJoin.Miter
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.graphics.vector.VectorAssetBuilder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val VectorAssets.Transfer: VectorAsset
    get() {
        if (_icTransfer != null) {
            return _icTransfer!!
        }
        _icTransfer = VectorAssetBuilder(
            name = "IcTransfer", defaultWidth = 477.859.dp,
            defaultHeight = 477.859.dp, viewportWidth = 477.859f,
            viewportHeight =
                477.859f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(472.863f, 175.662f)
                lineTo(353.396f, 56.195f)
                curveToRelative(-6.666f, -6.664f, -17.472f, -6.662f, -24.136f, 0.004f)
                curveToRelative(-3.199f, 3.2f, -4.996f, 7.538f, -4.997f, 12.063f)
                verticalLineToRelative(51.2f)
                horizontalLineTo(204.796f)
                curveToRelative(-9.426f, 0.0f, -17.067f, 7.641f, -17.067f, 17.067f)
                curveToRelative(0.0f, 9.426f, 7.641f, 17.067f, 17.067f, 17.067f)
                horizontalLineTo(341.33f)
                curveToRelative(9.426f, 0.0f, 17.067f, -7.641f, 17.067f, -17.067f)
                verticalLineTo(109.46f)
                lineToRelative(78.268f, 78.268f)
                lineToRelative(-78.268f, 78.268f)
                verticalLineToRelative(-27.068f)
                curveToRelative(0.0f, -9.426f, -7.641f, -17.067f, -17.067f, -17.067f)
                horizontalLineTo(153.596f)
                verticalLineToRelative(-51.2f)
                curveToRelative(-0.002f, -9.426f, -7.645f, -17.065f, -17.07f, -17.063f)
                curveToRelative(-4.524f, 0.001f, -8.863f, 1.798f, -12.063f, 4.997f)
                lineTo(4.997f, 278.062f)
                curveToRelative(-6.663f, 6.665f, -6.663f, 17.468f, 0.0f, 24.132f)
                lineToRelative(119.467f, 119.467f)
                curveToRelative(3.2f, 3.201f, 7.54f, 5.0f, 12.066f, 5.001f)
                curveToRelative(2.243f, 0.007f, 4.466f, -0.434f, 6.536f, -1.297f)
                curveToRelative(6.376f, -2.644f, 10.532f, -8.867f, 10.53f, -15.77f)
                verticalLineToRelative(-51.2f)
                horizontalLineToRelative(119.467f)
                curveToRelative(9.426f, 0.0f, 17.067f, -7.641f, 17.067f, -17.067f)
                reflectiveCurveToRelative(-7.641f, -17.067f, -17.067f, -17.067f)
                horizontalLineTo(136.53f)
                curveToRelative(-9.426f, 0.0f, -17.067f, 7.641f, -17.067f, 17.067f)
                verticalLineToRelative(27.068f)
                lineToRelative(-78.268f, -78.268f)
                lineToRelative(78.268f, -78.268f)
                verticalLineToRelative(27.068f)
                curveToRelative(0.0f, 9.426f, 7.641f, 17.067f, 17.067f, 17.067f)
                horizontalLineToRelative(187.733f)
                verticalLineToRelative(51.2f)
                curveToRelative(0.002f, 9.426f, 7.645f, 17.065f, 17.07f, 17.063f)
                curveToRelative(4.524f, -0.001f, 8.863f, -1.798f, 12.063f, -4.997f)
                lineToRelative(119.467f, -119.467f)
                curveTo(479.525f, 193.129f, 479.525f, 182.326f, 472.863f, 175.662f)
                close()
            }
        }
            .build()
        return _icTransfer!!
    }

private var _icTransfer: VectorAsset? = null

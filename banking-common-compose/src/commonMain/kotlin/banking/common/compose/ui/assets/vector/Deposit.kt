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

public val VectorAssets.Deposit: VectorAsset
    get() {
        if (_icDeposit != null) {
            return _icDeposit!!
        }
        _icDeposit = VectorAssetBuilder(
            name = "IcDeposit", defaultWidth = 72.0.dp,
            defaultHeight =
                72.0.dp,
            viewportWidth = 72.0f, viewportHeight = 72.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(61.653f, 65.2894f)
                curveToRelative(2.3628f, -0.6407f, 4.3556f, -2.7329f, 4.8538f, -5.1101f)
                curveToRelative(0.0994f, -0.5126f, 0.1425f, -2.306f, 0.1138f, -5.2951f)
                curveToRelative(-0.0425f, -4.3131f, -0.0569f, -4.5694f, -0.3701f, -5.3233f)
                curveToRelative(-0.9395f, -2.3485f, -3.1173f, -4.0712f, -5.5089f, -4.3412f)
                lineToRelative(-0.7826f, -0.0994f)
                lineToRelative(-0.0f, 6.861f)
                lineToRelative(-0.0f, 6.8466f)
                lineToRelative(-23.9847f, 0.0f)
                lineToRelative(-23.9847f, 0.0f)
                lineToRelative(-0.0f, -6.8472f)
                lineToRelative(-0.0f, -6.861f)
                lineToRelative(-0.7826f, 0.0994f)
                curveToRelative(-2.3916f, 0.2707f, -4.555f, 1.9784f, -5.5089f, 4.3412f)
                curveToRelative(-0.3132f, 0.7826f, -0.3275f, 0.9251f, -0.3275f, 5.7789f)
                reflectiveCurveToRelative(0.0144f, 5.0107f, 0.3275f, 5.7789f)
                curveToRelative(0.712f, 1.779f, 2.2066f, 3.288f, 3.9712f, 3.9999f)
                lineToRelative(0.8257f, 0.3275f)
                lineToRelative(25.1948f, 0.0288f)
                curveToRelative(21.6787f, 0.0288f, 25.308f, 0.0f, 25.9631f, -0.185f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(55.2334f, 39.7251f)
                lineToRelative(-0.0425f, -14.4196f)
                lineToRelative(-0.3275f, -0.8114f)
                curveToRelative(-0.712f, -1.7653f, -2.2203f, -3.2742f, -3.9999f, -3.9856f)
                lineToRelative(-0.797f, -0.3275f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-0.8114f, 0.3275f)
                curveToRelative(-1.7653f, 0.712f, -3.2736f, 2.2203f, -3.9856f, 3.9999f)
                lineToRelative(-0.3275f, 0.797f)
                lineToRelative(-0.0425f, 14.4196f)
                lineToRelative(-0.0288f, 14.4052f)
                lineToRelative(2.2066f, 0.0f)
                lineToRelative(2.2066f, 0.0f)
                lineToRelative(-0.0f, -11.3879f)
                lineToRelative(-0.0f, -11.3872f)
                lineToRelative(0.4694f, 0.0f)
                curveToRelative(0.6976f, 0.0f, 2.0497f, -0.3844f, 2.861f, -0.8257f)
                curveToRelative(0.9251f, -0.4982f, 2.1778f, -1.7509f, 2.676f, -2.676f)
                curveToRelative(0.4413f, -0.8114f, 0.8257f, -2.1634f, 0.8257f, -2.861f)
                lineToRelative(-0.0f, -0.4694f)
                lineToRelative(8.028f, 0.0f)
                lineToRelative(8.0136f, 0.0f)
                lineToRelative(0.0856f, 0.8395f)
                curveToRelative(0.3275f, 3.0604f, 2.8898f, 5.6227f, 5.9502f, 5.9502f)
                lineToRelative(0.8401f, 0.0856f)
                lineToRelative(-0.0f, 11.3591f)
                lineToRelative(-0.0f, 11.3735f)
                lineToRelative(2.2066f, 0.0f)
                lineToRelative(2.2066f, 0.0f)
                lineToRelative(-0.0294f, -14.4058f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(37.9672f, 51.6387f)
                curveToRelative(2.5054f, -0.5551f, 4.3981f, -2.0497f, 5.4514f, -4.2844f)
                curveToRelative(0.3419f, -0.7401f, 0.3844f, -1.0108f, 0.3844f, -2.2772f)
                curveToRelative(-0.0f, -1.3664f, -0.0288f, -1.4658f, -0.5551f, -2.5335f)
                curveToRelative(-1.3664f, -2.776f, -4.2987f, -4.3556f, -7.7717f, -4.1706f)
                curveToRelative(-3.1742f, 0.1563f, -5.6796f, 1.7653f, -6.9322f, 4.4413f)
                curveToRelative(-0.3557f, 0.7545f, -0.3988f, 1.0108f, -0.3988f, 2.2772f)
                curveToRelative(-0.0f, 1.3521f, 0.0288f, 1.4802f, 0.527f, 2.5197f)
                curveToRelative(1.509f, 3.1592f, 5.452f, 4.8819f, 9.2951f, 4.0274f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(55.2334f, 19.6116f)
                curveToRelative(-0.0994f, -1.3383f, -0.4838f, -2.4341f, -1.2239f, -3.5017f)
                curveToRelative(-0.6407f, -0.9251f, -1.9928f, -1.9784f, -3.1173f, -2.4341f)
                lineToRelative(-0.8257f, -0.3275f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-0.8114f, 0.3275f)
                curveToRelative(-1.779f, 0.7264f, -3.3167f, 2.2634f, -3.9712f, 3.9999f)
                curveToRelative(-0.1569f, 0.4413f, -0.3275f, 1.3096f, -0.3844f, 1.9359f)
                lineToRelative(-0.0713f, 1.1245f)
                lineToRelative(0.8395f, -0.7545f)
                curveToRelative(0.8826f, -0.7826f, 2.6335f, -1.7221f, 3.8293f, -2.0353f)
                curveToRelative(0.5695f, -0.1425f, 3.6436f, -0.185f, 14.7327f, -0.185f)
                curveToRelative(13.2094f, 0.0f, 14.0633f, 0.0144f, 14.8746f, 0.2563f)
                curveToRelative(1.2527f, 0.3844f, 2.6191f, 1.1527f, 3.5586f, 1.9928f)
                lineToRelative(0.8257f, 0.7257f)
                lineToRelative(-0.0713f, -1.1245f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = SolidColor(Color(0x00000000)),
                strokeLineWidth = 1.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveToRelative(55.2334f, 12.7794f)
                curveToRelative(-0.0569f, -0.6263f, -0.2275f, -1.4946f, -0.3844f, -1.9359f)
                curveToRelative(-0.6551f, -1.7365f, -2.1922f, -3.2736f, -3.9712f, -3.9999f)
                lineToRelative(-0.8114f, -0.3275f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-14.092f, 0.0f)
                lineToRelative(-0.797f, 0.3275f)
                curveToRelative(-1.7934f, 0.7264f, -3.3167f, 2.2491f, -3.9712f, 3.9712f)
                curveToRelative(-0.1706f, 0.4557f, -0.3419f, 1.3239f, -0.3988f, 1.964f)
                lineToRelative(-0.0713f, 1.1245f)
                lineToRelative(0.8395f, -0.7401f)
                curveToRelative(1.0395f, -0.9395f, 2.7754f, -1.8078f, 4.1562f, -2.0922f)
                curveToRelative(1.5371f, -0.3132f, 27.202f, -0.3132f, 28.7103f, 0.0f)
                curveToRelative(1.3377f, 0.2844f, 3.0886f, 1.167f, 4.1137f, 2.0922f)
                lineToRelative(0.8401f, 0.7401f)
                lineToRelative(-0.0706f, -1.1239f)
                close()
            }
        }
            .build()
        return _icDeposit!!
    }

private var _icDeposit: VectorAsset? = null

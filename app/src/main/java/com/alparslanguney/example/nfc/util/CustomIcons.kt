package com.alparslanguney.example.nfc.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val HamburgerMenu: ImageVector
    get() {
        if (_HamburgerMenu != null) {
            return _HamburgerMenu!!
        }
        _HamburgerMenu = ImageVector.Builder(
            name = "HamburgerMenu",
            defaultWidth = 15.dp,
            defaultHeight = 15.dp,
            viewportWidth = 15f,
            viewportHeight = 15f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(1.5f, 3f)
                curveTo(1.2239f, 3f, 1f, 3.2239f, 1f, 3.5f)
                curveTo(1f, 3.7761f, 1.2239f, 4f, 1.5f, 4f)
                horizontalLineTo(13.5f)
                curveTo(13.7761f, 4f, 14f, 3.7761f, 14f, 3.5f)
                curveTo(14f, 3.2239f, 13.7761f, 3f, 13.5f, 3f)
                horizontalLineTo(1.5f)
                close()
                moveTo(1f, 7.5f)
                curveTo(1f, 7.2239f, 1.2239f, 7f, 1.5f, 7f)
                horizontalLineTo(13.5f)
                curveTo(13.7761f, 7f, 14f, 7.2239f, 14f, 7.5f)
                curveTo(14f, 7.7761f, 13.7761f, 8f, 13.5f, 8f)
                horizontalLineTo(1.5f)
                curveTo(1.2239f, 8f, 1f, 7.7761f, 1f, 7.5f)
                close()
                moveTo(1f, 11.5f)
                curveTo(1f, 11.2239f, 1.2239f, 11f, 1.5f, 11f)
                horizontalLineTo(13.5f)
                curveTo(13.7761f, 11f, 14f, 11.2239f, 14f, 11.5f)
                curveTo(14f, 11.7761f, 13.7761f, 12f, 13.5f, 12f)
                horizontalLineTo(1.5f)
                curveTo(1.2239f, 12f, 1f, 11.7761f, 1f, 11.5f)
                close()
            }
        }.build()
        return _HamburgerMenu!!
    }

private var _HamburgerMenu: ImageVector? = null


public val Dumbbell: ImageVector
    get() {
        if (_Dumbbell != null) {
            return _Dumbbell!!
        }
        _Dumbbell = ImageVector.Builder(
            name = "Dumbbell",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF000000)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(14.4f, 14.4f)
                lineTo(9.6f, 9.6f)
            }
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF000000)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(18.657f, 21.485f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2.829f, -2.828f)
                lineToRelative(-1.767f, 1.768f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2.829f, -2.829f)
                lineToRelative(6.364f, -6.364f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2.829f, 2.829f)
                lineToRelative(-1.768f, 1.767f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2.828f, 2.829f)
                close()
            }
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF000000)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(21.5f, 21.5f)
                lineToRelative(-1.4f, -1.4f)
            }
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF000000)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(3.9f, 3.9f)
                lineTo(2.5f, 2.5f)
            }
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF000000)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(6.404f, 12.768f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2.829f, -2.829f)
                lineToRelative(1.768f, -1.767f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2.828f, -2.829f)
                lineToRelative(2.828f, -2.828f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2.829f, 2.828f)
                lineToRelative(1.767f, -1.768f)
                arcToRelative(2f, 2f, 0f, isMoreThanHalf = true, isPositiveArc = true, 2.829f, 2.829f)
                close()
            }
        }.build()
        return _Dumbbell!!
    }

private var _Dumbbell: ImageVector? = null



public val GraphUp: ImageVector
    get() {
        if (_GraphUp != null) {
            return _GraphUp!!
        }
        _GraphUp = ImageVector.Builder(
            name = "GraphUp",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(0f, 0f)
                horizontalLineToRelative(1f)
                verticalLineToRelative(15f)
                horizontalLineToRelative(15f)
                verticalLineToRelative(1f)
                horizontalLineTo(0f)
                close()
                moveToRelative(14.817f, 3.113f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.07f, 0.704f)
                lineToRelative(-4.5f, 5.5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.74f, 0.037f)
                lineTo(7.06f, 6.767f)
                lineToRelative(-3.656f, 5.027f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, -0.808f, -0.588f)
                lineToRelative(4f, -5.5f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.758f, -0.06f)
                lineToRelative(2.609f, 2.61f)
                lineToRelative(4.15f, -5.073f)
                arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.704f, -0.07f)
            }
        }.build()
        return _GraphUp!!
    }

private var _GraphUp: ImageVector? = null


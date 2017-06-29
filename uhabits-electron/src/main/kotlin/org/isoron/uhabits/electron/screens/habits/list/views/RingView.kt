/*
 * Copyright (C) 2015-2017 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.isoron.uhabits.electron.screens.habits.list.views

import kotlinx.html.*
import org.isoron.uhabits.core.graphics.*
import org.isoron.uhabits.core.ui.widgets.*
import org.isoron.uhabits.electron.graphics.*
import org.w3c.dom.*
import react.*
import react.dom.*
import kotlin.browser.*
import kotlin.js.*

object LIGHT_THEME : Theme {
    override val cardBackgroundColor = Color(255, 255, 255)
    override val highContrastTextColor = Color(0, 0, 0)
    override val mediumContrastTextColor = Color(100, 100, 100)
    override val lowContrastTextColor = Color(220, 220, 220)
    override val smallTextSize = 2.0
}

class RingView : ReactDOMComponent<RingView.Props, RingView.State>() {
    private val canvasId = Math.random().toString()

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        canvas {
            id = canvasId
            width = props.size
            height = props.size
        }
    }

    override fun componentDidMount() {
        val canvas = document.getElementById(canvasId) as HTMLCanvasElement
        val ctx = canvas.getContext("2d") as CanvasRenderingContext2D
        val ring = RingWidget(RingWidget.Props(
                LIGHT_THEME,
                thickness = 3.0,
                percentage = Math.random(),
                primaryColor = props.color))
        ring.draw(HTMLCanvas(canvas, ctx))
    }

    class State : RState
    class Props(var size: String,
                var color: Color) : RProps()

    companion object : ReactComponentSpec<RingView, Props, State>
}
package com.kalum.monese.rockets.util

import android.content.Context
import android.util.AttributeSet
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.ChangeBounds
import androidx.transition.ChangeClipBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet

class DetailsTransition : TransitionSet {

    constructor() {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {

        interpolator = FastOutSlowInInterpolator()

        ordering = ORDERING_TOGETHER

        duration = 300

        addTransition( ChangeClipBounds() )
        .addTransition( ChangeTransform())
        .addTransition( ChangeBounds())

    }

}

@file:Suppress("UNCHECKED_CAST")

package com.kryptkode.navigation.ktx

import android.app.Activity
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.kryptkode.commonandroid.extension.illegal
import com.kryptkode.navigation.direction.NavDirectionContext

val EmptyNavContext = NavDirectionContext()

/**
 * Gets the [NavDirectionContext] for an [Activity] or throws an exception.
 */
fun <T : Parcelable> Activity.requireNavParam(): T =
    intent?.getParcelableExtra<NavDirectionContext>(NavDirectionContext.CONTEXT_PARAM)
        ?.extra as? T
        ?: illegal("This intent must contain navigation param.")

/**
 * Gets the [NavDirectionContext] for an [Fragment] or throws an exception.
 */
fun <T : Parcelable> Fragment.requireNavParam(): T =
    arguments?.getParcelable<NavDirectionContext>(NavDirectionContext.CONTEXT_PARAM)
        ?.extra as? T
        ?: illegal("This bundle must contain navigation param.")

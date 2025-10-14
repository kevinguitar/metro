// Copyright (C) 2025 Zac Sweers
// SPDX-License-Identifier: Apache-2.0
package dev.zacsweers.metro

import kotlin.annotation.AnnotationTarget.CLASS

/**
 * The meta-annotation for adding contribution behavior to user-defined annotations.
 *
 * ```kotlin
 * @MetaContribution
 * @ContributesBinding(AppScope::class, binding = binding<Config<*>>)
 * annotation class ContributesAppConfig
 *
 * interface Config<T>
 *
 * @ContributesAppConfig
 * class StringConfig : Config<String>
 * ```
 */
@Target(CLASS)
public annotation class MetaContribution

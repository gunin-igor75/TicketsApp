package com.core.common.utils

import android.content.Context

class AndroidResourceProvider(
    private val context: Context
): ResourceProvider {

    override fun string(id: Int) = context.resources.getString(id)

}
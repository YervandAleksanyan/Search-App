package com.test.searchapp.utils

import timber.log.Timber

class NumberedTimberTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement) =
        "(${element.fileName}:${element.lineNumber})#${element.methodName}"
}
package com.neronguyenvn.nerolauncher.benchmarks

import android.annotation.SuppressLint
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.untilHasChildren
import org.junit.Rule
import org.junit.Test

@SuppressLint("NewApi")
class BaselineProfileGenerator {

    @get:Rule
    val rule = BaselineProfileRule()

    @Test
    fun generate() {
        rule.collect("com.neronguyenvn.nerolauncher") {
            pressHome()
            startActivityAndWait()
            waitForContent()
        }
    }
}

fun MacrobenchmarkScope.waitForContent() {
    val appList = device.waitAndFindObject(By.res("home:AppGridUi"), 5_000)
    appList.wait(untilHasChildren(), 5_000)
}

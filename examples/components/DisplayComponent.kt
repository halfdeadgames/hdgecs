package examples.components

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import ecs.Component

class DisplayComponent(var data: String, var updateFrequency: TimeSpan, var timeSinceLastUpdate: TimeSpan = 0.milliseconds) : Component
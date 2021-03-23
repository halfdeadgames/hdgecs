package examples.systems

import com.soywiz.klock.TimeSpan
import com.soywiz.klock.milliseconds
import ecs.Engine
import ecs.Family
import ecs.System
import examples.components.DisplayComponent
import examples.components.PositionComponent
import examples.components.VelocityComponent

class DisplaySystem(engine: Engine) : System(
    Family(engine)
        .containsAll(
            DisplayComponent::class,
            PositionComponent::class
        ),
    engine) {

    val displayComponentId = engine.getComponentId(DisplayComponent::class)
    val positionComponentId = engine.getComponentId(PositionComponent::class)

    override fun act(deltaTime: TimeSpan) {
        super.act(deltaTime)
        for(entity in entities) {
            val displayComponent = entity.components[displayComponentId] as DisplayComponent
            val positionComponent = entity.components[positionComponentId] as PositionComponent

            if(displayComponent.updateFrequency > displayComponent.timeSinceLastUpdate) {
                displayComponent.timeSinceLastUpdate += deltaTime
            } else {
                displayComponent.timeSinceLastUpdate = 0.milliseconds
                println("${displayComponent.data}: x ${positionComponent.x} y ${positionComponent.y}")
            }
        }
    }
}
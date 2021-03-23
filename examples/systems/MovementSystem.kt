package examples.systems

import com.soywiz.klock.TimeSpan
import com.soywiz.korui.layout.Position
import ecs.Engine
import ecs.Family
import ecs.System
import examples.components.PositionComponent
import examples.components.VelocityComponent

class MovementSystem(engine: Engine) : System(
    Family(engine)
        .containsAll(
            PositionComponent::class,
            VelocityComponent::class
        ),
    engine) {

    val positionComponentId = engine.getComponentId(PositionComponent::class)
    val velocityComponentId = engine.getComponentId(VelocityComponent::class)

    override fun act(deltaTime: TimeSpan) {
        super.act(deltaTime)
        for(entity in entities) {
            val position = entity.components[positionComponentId] as PositionComponent
            val velocity = entity.components[velocityComponentId] as VelocityComponent

            position.x += velocity.x * deltaTime.milliseconds.toFloat()
            position.y += velocity.y * deltaTime.milliseconds.toFloat()
        }
    }
}
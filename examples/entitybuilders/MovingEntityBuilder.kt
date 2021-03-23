package examples.entitybuilders

import com.soywiz.klock.TimeSpan
import ecs.Engine
import ecs.Entity
import examples.components.DisplayComponent
import examples.components.PositionComponent
import examples.components.VelocityComponent

object MovingEntityBuilder {
    fun getMovingEntity(engine: Engine, name: String, displayFrequency: TimeSpan, posX: Float, posY: Float, velX: Float, velY: Float) : Entity {
        return Entity(engine)
            .addComponent(PositionComponent(0f, 0f))
            .addComponent(VelocityComponent(0.1f))
            .addComponent(DisplayComponent(name, displayFrequency))
    }
}
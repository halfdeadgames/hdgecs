import com.soywiz.klock.TimeSpan
import com.soywiz.klock.seconds
import com.soywiz.korge.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import ecs.*
import examples.components.DisplayComponent
import examples.components.PositionComponent
import examples.components.VelocityComponent
import examples.entitybuilders.MovingEntityBuilder
import examples.systems.DisplaySystem
import examples.systems.MovementSystem

suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {

	val engine = Engine()

	engine.registerComponent(DisplayComponent::class)
	engine.registerComponent(PositionComponent::class)
	engine.registerComponent(VelocityComponent::class)

	engine.addSystem(MovementSystem(engine))
	engine.addSystem(DisplaySystem(engine))

	engine.addEntity(MovingEntityBuilder.getMovingEntity(engine, "alpha", 1.seconds, 0f, 0f, 0.1f, 0f))

	addUpdater {
		dt -> engine.update(dt)
	}
}
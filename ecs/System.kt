package ecs

import com.soywiz.klock.TimeSpan

abstract class System(val family: Family, val engine: Engine) {
    var entities = listOf<Entity>()

    fun updateEntityList() {
        entities = engine.getEntitiesFromFamily(family)
    }

    open fun act(deltaTime: TimeSpan) {
        updateEntityList()
    }
}
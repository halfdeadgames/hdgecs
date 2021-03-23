package ecs

import com.soywiz.klock.TimeSpan
import utils.Bits
import kotlin.reflect.KClass

open class Engine {
    private val componentRegistry = Bits()
    private val componentTypes = hashMapOf<KClass<out Component>, Int>()
    private val entities = mutableListOf<Entity>()
    private val systems = mutableListOf<System>()

    fun registerComponent(componentType: KClass<out Component>) : Int {
        return if(!componentTypes.containsKey(componentType)) {
            val id = componentRegistry.nextClearBit()
            componentRegistry.set(id)
            componentTypes[componentType] = id
            id
        } else {
            componentTypes[componentType]!!
        }
    }

    fun getComponentId(componentType: KClass<out Component>) = componentTypes[componentType]

    fun addEntity(entity: Entity) {
        entities.add(entity)
    }

    fun removeEntity(entity: Entity) {
        entities.remove(entity)
    }

    fun addSystem(system: System) {
        systems.add(system)
    }

    fun removeSystem(system: System) {
        systems.remove(system)
    }

    fun getBitsForGroup(vararg componentTypes: KClass<out Component>) : Bits {
        val output = Bits()
        for(i in componentTypes) {
            val id = getComponentId(i) ?: throw Exception("Component type $i is not registered with the engine $this")
            output.set(id)
        }
        return output
    }

    fun getEntitiesFromFamily(family: Family) : List<Entity> {
        return entities.filter {
            var isInFamily = true
            if(family.containsAll.isNotEmpty) {
                isInFamily = it.componentTypes.containsAll(family.containsAll)
            }

            if(isInFamily && family.containsAny.isNotEmpty) {
                isInFamily = it.componentTypes.intersects(family.containsAny)
            }

            if(isInFamily && family.containsNone.isNotEmpty) {
                isInFamily = !it.componentTypes.intersects(family.containsNone)
            }

            isInFamily
        }
    }

    open fun update(deltaTime: TimeSpan) {
        for(i in systems) i.act(deltaTime)
    }
}
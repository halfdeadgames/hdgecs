package ecs

import utils.Bits
import kotlin.reflect.KClass

class Entity(val engine: Engine) {
    val componentTypes = Bits()
    var components = hashMapOf<Int, Component>()

    fun addComponent(component: Component) : Entity {
        val id = engine.registerComponent(component::class)
        if(componentTypes[id]) throw Exception("Cannot add a component of the same type to a single entity")

        componentTypes.set(id)
        components[id] = component

        return this
    }

    fun removeComponent(componentType: KClass<out Component>) : Entity {
        val id = engine.getComponentId(componentType)
            ?: throw Exception("Entity does not contain component type $componentType")
        componentTypes.clear(id)
        components.remove(id)

        return this
    }
}
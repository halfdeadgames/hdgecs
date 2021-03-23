package ecs

import kotlin.reflect.KClass
import utils.Bits

class Family(val engine: Engine) {
    val containsAll: Bits = Bits()
    val containsAny: Bits = Bits()
    val containsNone: Bits = Bits()

    private fun containsX(bits: Bits, componentTypes: Array<out KClass<out Component>>) : Family {
        for(i in componentTypes) {
            val id = engine.getComponentId(i) ?: throw Exception("Engine $engine does not have a registed component for $i")
            bits.set(id)
        }
        return this
    }

    fun containsAll(vararg componentTypes: KClass<out Component>) : Family {
        return containsX(containsAll, componentTypes)
    }

    fun containsAny(vararg componentTypes: KClass<out Component>) : Family {
        return containsX(containsAny, componentTypes)
    }

    fun containsNone(vararg componentTypes: KClass<out Component>) : Family {
        return containsX(containsNone, componentTypes)
    }
}
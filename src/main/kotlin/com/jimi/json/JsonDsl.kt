package com.jimi.json

import com.fasterxml.jackson.databind.node.ArrayNode

fun jsonStr(block: JsonNodeBuilder.() -> Unit) = JsonNodeBuilder().also(block).jsonStr

fun json(block: JsonNodeBuilder.() -> Unit) = JsonNodeBuilder().also(block).json

class JsonNodeBuilder internal constructor() {
    val json = JsonMapperFactory.defaultMapper.createObjectNode()!!

    val jsonStr
        get() = JsonMapperFactory.defaultMapper.writeValueAsString(json)!!

    infix fun String.be(value: Int) {
        json.put(this, value)
    }

    infix fun String.be(value: Float) {
        json.put(this, value)
    }

    infix fun String.be(value: Double) {
        json.put(this, value)
    }

    infix fun String.be(value: Long) {
        json.put(this, value)
    }

    infix fun String.be(value: Boolean) {
        json.put(this, value)
    }

    infix fun String.be(value: String) {
        json.put(this, value)
    }

    infix fun String.be(value: JsonNodeBuilder.() -> Unit) {
        json.set(this, JsonNodeBuilder().also(value).json)
    }

    internal infix fun String.be(value: ArrayNode) = json.set(this, value)

    operator fun get(vararg values: String): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: Int): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: Double): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: Float): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: Long): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: Boolean): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(it) }
        return arrayNode
    }

    operator fun get(vararg values: JsonNodeBuilder.() -> Unit): ArrayNode {
        val arrayNode = JsonMapperFactory.defaultMapper.createArrayNode()
        values.forEach { arrayNode.add(JsonNodeBuilder().also(it).json) }
        return arrayNode
    }
}

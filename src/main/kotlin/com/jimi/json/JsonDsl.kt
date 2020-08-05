package com.jimi.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode

fun jsonStr(isArray: Boolean = false, block: JsonNodeBuilder.() -> Unit) = JsonNodeBuilder(isArray).also(block).jsonStr

fun json(isArray: Boolean = false, block: JsonNodeBuilder.() -> Unit) = JsonNodeBuilder(isArray).also(block).json

abstract class BaseJsonNode {
    abstract val json: JsonNode

    abstract val jsonStr: String
}

class JsonNodeBuilder internal constructor(isArray: Boolean) : BaseJsonNode() {
    override val json: JsonNode by lazy {
        if (isArray) JsonMapperFactory.defaultMapper.createArrayNode()!! else JsonMapperFactory.defaultMapper.createObjectNode()!!
    }

    override val jsonStr
        get() = JsonMapperFactory.defaultMapper.writeValueAsString(json)!!

    private val objectNode
        get() = json as ObjectNode

    private val arrayNode
        get() = json as ArrayNode

    infix fun String.be(value: Int) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: Float) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: Double) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: Long) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: Boolean) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: String) {
        objectNode.put(this, value)
    }

    infix fun String.be(value: JsonNodeBuilder.() -> Unit) {
        objectNode.set(this, JsonNodeBuilder(false).also(value).json)
    }

    infix fun String.beArr(value: JsonNodeBuilder.() -> Unit) {
        objectNode.set(this, JsonNodeBuilder(true).also(value).json)
    }

    internal infix fun String.be(value: ArrayNode) = objectNode.set(this, value)

    infix fun Int.be(value: Int) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: Float) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: Double) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: Long) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: Boolean) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: String) {
        arrayNode.insert(this, value)
    }

    infix fun Int.be(value: JsonNodeBuilder.() -> Unit) {
        arrayNode.insert(this, JsonNodeBuilder(false).also(value).json)
    }

    infix fun Int.beArr(value: JsonNodeBuilder.() -> Unit) {
        arrayNode.insert(this, JsonNodeBuilder(true).also(value).json)
    }

    internal infix fun Int.be(value: ArrayNode) = arrayNode.insert(this, value)
}

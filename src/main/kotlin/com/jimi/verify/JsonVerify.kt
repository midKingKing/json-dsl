package com.jimi.verify

import com.fasterxml.jackson.databind.JsonNode
import com.jimi.json.JsonMapperFactory
import java.math.BigDecimal
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun String.jsonVerify(block: JsonNodeWrapper.() -> Unit) {
    val jsonNode = JsonMapperFactory.defaultMapper.readTree(this)
    block(JsonNodeWrapper(jsonNode))
}

class JsonNodeWrapper constructor(private val node: JsonNode) {
    infix fun String.eq(expectedValue: String?) {
        if (notNullAssert(this, node, expectedValue)) {
            assert(node[this].isTextual) { "$this is not string type" }
            assert(node[this].textValue() == expectedValue)
        }
    }

    infix fun String.eq(expectedValue: Number?) {
        if (notNullAssert(this, node, expectedValue)) {
            assert(node[this].isNumber) { "$this is not number type" }
            when (expectedValue) {
                is Int -> assert(node[this].intValue() == expectedValue)
                is Long -> assert(node[this].longValue() == expectedValue)
                is Double -> assert(node[this].doubleValue() == expectedValue)
                is Float -> assert(node[this].floatValue() == expectedValue)
                is BigDecimal -> assert(node[this].decimalValue() == expectedValue)
                else -> throw NotImplementedError("${expectedValue::class} not support eq yet")
            }
        }
    }

    infix fun String.eq(expectedValue: Boolean?) {
        if (notNullAssert(this, node, expectedValue)) {
            assert(node[this].isBoolean) { "$this is not bool type" }
            assert(node[this].booleanValue() == expectedValue)
        }
    }

    infix fun String.eq(block: JsonNodeWrapper.() -> Unit) {
        assert(!(node[this] == null || node[this].isNull)) { "$this is null node" }
        block(JsonNodeWrapper(node[this]))
    }
}

@UseExperimental(ExperimentalContracts::class)
private fun notNullAssert(nodeName: String, node: JsonNode, expectedValue: Any?): Boolean {
    contract {
        returns(true) implies (expectedValue != null)
    }
    when {
        expectedValue == null && !node[nodeName].isNull -> assert(false) { "assert expectedValue null" }
        expectedValue != null && node[nodeName].isNull -> assert(false) { "assert $nodeName node null" }
    }
    return !(node[nodeName].isNull && expectedValue == null)
}
package com.jimi.json

import com.fasterxml.jackson.databind.node.ArrayNode
import org.junit.Test

class Sample {
    @Test
    fun `test dsl` () {
        val json = json {
            "a" be 1
            "b" be "1"
            "c" be 1.1
            "d" be false
            "e" be this[1, 2]
            "f" be {
                "g" be this["3", "4"]
            }
        }
        assert(json["a"].asInt() == 1)
        assert(json["b"].asText() == "1")
        assert(json["c"].asDouble() == 1.1)
        assert(!json["d"].asBoolean())
        val arrE = json["e"] as ArrayNode
        assert(arrE.size() == 2)
        assert(arrE[0].asInt() == 1)
        assert(arrE[1].asInt() == 2)
        val arrF = json["f"]["g"] as ArrayNode
        assert(arrF.size() == 2)
        assert(arrF[0].asText() == "3")
        assert(arrF[1].asText() == "4")
    }
}

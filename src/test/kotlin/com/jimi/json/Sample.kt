package com.jimi.json

import com.fasterxml.jackson.databind.node.ArrayNode
import com.jimi.verify.jsonVerify
import org.junit.Test

class Sample {
    @Test
    fun `test json object dsl`() {
        val json = json {
            "a" be 1
            "b" be "1"
            "c" be 1.1
            "d" be false
            "e" beArr {
                0 be 1
                1 be 2
            }
            "f" be {
                "g" beArr {
                    0 be "3"
                    1 be "4"
                }
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

    @Test
    fun `test json array dsl`() {
        val json = json(true) {
            0 be 1
            1 be "1"
            2 be 1.1
            3 be false
            4 beArr {
                0 be 1
                1 be 2
            }
            5 be {
                "g" beArr {
                    0 be "3"
                    1 be "4"
                }
            }
        } as ArrayNode
        assert(json[0].asInt() == 1)
        assert(json[1].asText() == "1")
        assert(json[2].asDouble() == 1.1)
        assert(!json[3].asBoolean())
        val arrE = json[4] as ArrayNode
        assert(arrE.size() == 2)
        assert(arrE[0].asInt() == 1)
        assert(arrE[1].asInt() == 2)
        val arrF = json[5]["g"] as ArrayNode
        assert(arrF.size() == 2)
        assert(arrF[0].asText() == "3")
        assert(arrF[1].asText() == "4")
    }

    @Test
    fun `json verify`() {
        jsonStr {
            "a" be "123"
            "b" be 1
            "c" be {
                "a" be true
            }
        }.jsonVerify {
            "a" eq "123"
            "b" eq 1
            "c" eq {
                "a" eq true
            }
        }
    }
}

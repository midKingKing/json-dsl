# json-dsl
a simple json dsl
[![CI](https://github.com/midKingKing/json-dsl/actions/workflows/blank.yml/badge.svg)](https://github.com/midKingKing/json-dsl/actions/workflows/blank.yml)


- json:
```kotlin
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
```
you will get a json object node like:
```json
{"a":1,"b":"1","c":1.1,"d":false,"e":[1,2],"f":{"g":["3","4"]}}
```

- jsonArray:
```kotlin
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
        }
```

you will get a json array node like:
```json
[1,"1",1.1,false,[1,2],{"g":["3","4"]}]
```

# json-dsl
a simple json dsl


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

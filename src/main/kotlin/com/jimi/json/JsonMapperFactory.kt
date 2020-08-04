package com.jimi.json

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule

object JsonMapperFactory {
    /**
     * Get default json object mapper.
     */
    @JvmStatic
    val defaultMapper = ObjectMapper()

    init {
        defaultMapper.setVisibility(
            defaultMapper.visibilityChecker
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE)
        )

        defaultMapper
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .enable(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS)
            // Fix jackson vs @ConstructorProperties issue: cannot deserialize json properly,
            // default values will be overwritten.
            .disable(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES)
            .apply {
                nodeFactory = JsonNodeFactory.withExactBigDecimals(true)
            }

        // Serialize/deserialize Joda DateTime.
        defaultMapper
            .registerModule(KotlinModule())
            .registerModule(ParameterNamesModule())
            .registerModule(Jdk8Module())
            .registerModule(JavaTimeModule())
    }
}
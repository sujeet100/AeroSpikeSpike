package com.example.model

import com.aerospike.client.exp.Exp

sealed class RecordField(
    open val name: String,
    val type: Exp.Type,
    open val primary: Boolean? = false,
    open val secondary: Boolean? = false
)

data class IntField(
    override val name: String, override val primary: Boolean? = false,
    override val secondary: Boolean? = false
) : RecordField(name, Exp.Type.INT, primary, secondary)

data class StringField(
    override val name: String, override val primary: Boolean? = false,
    override val secondary: Boolean? = false
) : RecordField(name, Exp.Type.STRING, primary, secondary)

object CustomerRecord {
    const val nameSpace = "test"
    const val setName = "customers"
    val pk = IntField("PK", primary = true)
    val id = IntField("id")
    val name = StringField("name")
    val age = IntField("age")
    val address = StringField("id")
}

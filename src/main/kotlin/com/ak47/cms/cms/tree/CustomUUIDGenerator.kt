package com.ak47.cms.cms.tree

import org.apache.commons.lang3.reflect.FieldUtils
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.UUIDGenerator
import java.io.Serializable


class CustomUUIDGenerator : UUIDGenerator() {

    override fun generate(session: SharedSessionContractImplementor, obj: Any): Serializable {
        val code = FieldUtils.readDeclaredField(obj, "code", true)
        if (code != null) {
            return code as Serializable
        }
        return super.generate(session, obj)
    }

}
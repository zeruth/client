package org.runestar.client.updater.mapper.std.classes

import org.runestar.client.updater.mapper.IdentityMapper
import org.runestar.client.updater.mapper.annotations.DependsOn
import org.runestar.client.updater.mapper.annotations.SinceVersion
import org.runestar.client.updater.mapper.extensions.Predicate
import org.runestar.client.updater.mapper.extensions.and
import org.runestar.client.updater.mapper.extensions.predicateOf
import org.runestar.client.updater.mapper.tree.Class2
import java.lang.reflect.Modifier

@SinceVersion(162)
@DependsOn(Username::class)
class Usernamed : IdentityMapper.Class() {
    override val predicate = predicateOf<Class2> { Modifier.isInterface(it.access) }
            .and { it.instanceMethods.size == 1 }
            .and { it.instanceMethods.first().returnType == type<Username>() }
}
package com.runesuite.client.updater.mapper.std.classes

import com.hunterwb.kxtra.lang.list.startsWith
import com.runesuite.mapper.IdentityMapper
import com.runesuite.mapper.OrderMapper
import com.runesuite.mapper.UniqueMapper
import com.runesuite.mapper.annotations.DependsOn
import com.runesuite.mapper.annotations.MethodParameters
import com.runesuite.mapper.extensions.Predicate
import com.runesuite.mapper.extensions.and
import com.runesuite.mapper.extensions.predicateOf
import com.runesuite.mapper.extensions.type
import com.runesuite.mapper.tree.Class2
import com.runesuite.mapper.tree.Instruction2
import com.runesuite.mapper.tree.Method2
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.*
import org.objectweb.asm.Type.*

@DependsOn(Entity.getModel::class)
class Model : IdentityMapper.Class() {
    override val predicate = predicateOf<Class2> { it.type == method<Entity.getModel>().returnType }

    class verticesX : OrderMapper.InConstructor.Field(Model::class, 0) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    class verticesY : OrderMapper.InConstructor.Field(Model::class, 1) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    class verticesZ : OrderMapper.InConstructor.Field(Model::class, 2) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    class indices1 : OrderMapper.InConstructor.Field(Model::class, 3) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    class indices2 : OrderMapper.InConstructor.Field(Model::class, 4) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    class indices3 : OrderMapper.InConstructor.Field(Model::class, 5) {
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isNotEmpty() }
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == IntArray::class.type }
    }

    @DependsOn(Entity.renderAtPoint::class)
    class renderAtPoint : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.mark == method<Entity.renderAtPoint>().mark }
    }

    @MethodParameters("pitch")
    @DependsOn(Projectile.getModel::class)
    class rotateZ : OrderMapper.InMethod.Method(Projectile.getModel::class, 0) {
        override val predicate = predicateOf<Instruction2> { it.isMethod && it.methodOwner == type<Model>() }
    }

    class method0 : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.returnType == VOID_TYPE }
                .and { it.arguments.startsWith(BOOLEAN_TYPE, BOOLEAN_TYPE, BOOLEAN_TYPE) }
    }

    class verticesCount : OrderMapper.InConstructor.Field(Model::class, 0) {
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == INT_TYPE }
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isEmpty() }
    }

    class indicesCount : OrderMapper.InConstructor.Field(Model::class, 1) {
        override val predicate = predicateOf<Instruction2> { it.opcode == PUTFIELD && it.fieldType == INT_TYPE }
        override val constructorPredicate = predicateOf<Method2> { it.arguments.isEmpty() }
    }

    // either xz or xyz
    @DependsOn(Model.renderAtPoint::class)
    class diagonal : OrderMapper.InMethod.Field(Model.renderAtPoint::class, 1) {
        override val predicate = predicateOf<Instruction2> { it.opcode == Opcodes.GETFIELD && it.fieldType == INT_TYPE }
    }

    @MethodParameters("x", "y", "z")
    @DependsOn(Npc.getModel::class)
    class offsetBy : OrderMapper.InMethod.Method(Npc.getModel::class, 1) {
        override val predicate = predicateOf<Instruction2> { it.opcode == INVOKEVIRTUAL && it.methodOwner == type<Model>() }
    }

    // yaw
    @MethodParameters()
    @DependsOn(Player.getModel::class)
    class rotateY90Ccw : OrderMapper.InMethod.Method(Player.getModel::class, -1) {
        override val predicate = predicateOf<Instruction2> { it.opcode == INVOKEVIRTUAL && it.methodOwner == type<Model>() && it.methodType.argumentTypes.size in 0..1 }
    }

    @DependsOn(rotateZ::class)
    class resetCalcs : UniqueMapper.InMethod.Method(rotateZ::class) {
        override val predicate = predicateOf<Instruction2> { it.isMethod }
    }

    // new = old * scale / 128
    @MethodParameters("x", "y", "z")
    class scale : IdentityMapper.InstanceMethod() {
        override val predicate = predicateOf<Method2> { it.returnType == VOID_TYPE }
                .and { it.arguments.size in 3..4 }
                .and { it.arguments.startsWith(INT_TYPE, INT_TYPE, INT_TYPE) }
                .and { it.instructions.count { it.opcode == SIPUSH && it.intOperand == 128 } == 3 }
    }
}
package test.hpcgrpc

import com.google.protobuf.GeneratedMessageV3
import happyco.manage.asset.v1.AssetServiceGrpc
import happyco.manage.folder.v1.FolderOuterClass
import happyco.manage.folder.v1.FolderServiceGrpc
import happyco.type.v1.IntegrationId
import io.grpc.MethodDescriptor
import io.grpc.protobuf.ProtoUtils
import kotlin.reflect.KClass

val ACCOUNT_ID = IntegrationId.IntegrationID.newBuilder()
// put here account id
        .build()

val marshallers = mutableMapOf<KClass<*>, MethodDescriptor.Marshaller<*>>()

@Suppress("UNCHECKED_CAST")
inline fun <reified T : GeneratedMessageV3> T.toJson() =
        (marshallers.getOrPut(T::class) {
            ProtoUtils.jsonMarshaller(defaultInstanceForType)
        } as MethodDescriptor.Marshaller<T>).stream(this).reader().readText()
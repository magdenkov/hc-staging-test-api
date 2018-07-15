package test.hpcgrpc

import happyco.manage.folder.v1.FolderOuterClass
import happyco.type.v1.IntegrationId
import io.grpc.*
import java.util.concurrent.Executor


fun main(args: Array<String>) {

    if (args.size < 2) {
        println("Plz specify 2 arguments: account id ant token")
        return
    }

    val accountId = args[0]
    val token = args[1]

    val channel = ManagedChannelBuilder.forTarget("staging-grpc.happyco.com").build()
    val api = HappyCoApi(channel, TokenCredentials(token))

    val folders = FolderOuterClass.ListFoldersRequest.newBuilder()
            .setAccountId(IntegrationId.IntegrationID.newBuilder()
                    .setExternalId(accountId)
                    .build())
            .build()
            .run(api.folders::listFolders)

    println(folders.toJson())
}

class TokenCredentials(private val authToken: String) : CallCredentials {
    override fun applyRequestMetadata(method: MethodDescriptor<*, *>?, attrs: Attributes?, appExecutor: Executor?, applier: CallCredentials.MetadataApplier) {
        val meta = Metadata().apply {
            put(Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER), "Basic $authToken")
        }

        applier.apply(meta)
    }

    override fun thisUsesUnstableApi() {}
}
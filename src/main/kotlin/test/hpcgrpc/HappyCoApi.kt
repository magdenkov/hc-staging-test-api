package test.hpcgrpc

import happyco.inspect.inspection.v1.InspectionEventServiceGrpc
import happyco.inspect.inspection.v1.InspectionServiceGrpc
import happyco.inspect.report.v1.ReportEventServiceGrpc
import happyco.inspect.report.v1.ReportServiceGrpc
import happyco.inspect.template.v1.TemplateServiceGrpc
import happyco.manage.account.v1.AccountServiceGrpc
import happyco.manage.account.v1.AccountUsersServiceGrpc
import happyco.manage.account_provisioning.v1.AccountProvisioningServiceGrpc
import happyco.manage.asset.v1.AssetServiceGrpc
import happyco.manage.folder.v1.FolderServiceGrpc
import io.grpc.ManagedChannel

class HappyCoApi(channel: ManagedChannel, credentials: TokenCredentials) {
    val assets = AssetServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val accounts = AccountServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val accountProvisioning = AccountProvisioningServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val accountUsers = AccountUsersServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val inspections = InspectionServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val inspectionEvents = InspectionEventServiceGrpc.newStub(channel).withCallCredentials(credentials)
    val folders = FolderServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val templates = TemplateServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val reports = ReportServiceGrpc.newBlockingStub(channel).withCallCredentials(credentials)
    val reportEvents = ReportEventServiceGrpc.newStub(channel).withCallCredentials(credentials)
}
package master

import io.grpc.ManagedChannelBuilder
import task.task.{TaskRequest, TaskServiceGrpc}

object Main {
  def main(args: Array[String]): Unit = {
    // 워커 노드들의 주소
    val workers = List("localhost:50051", "localhost:50052")

    workers.foreach { address =>
      val channel = ManagedChannelBuilder.forTarget(address).usePlaintext().build()
      val stub = TaskServiceGrpc.blockingStub(channel)
      
      val request = TaskRequest(functionName = "sayHello", args = Seq("World"))
      val response = stub.executeTask(request)

      println(s"Response from $address: ${response.result}")

      channel.shutdown()
    }
  }
}
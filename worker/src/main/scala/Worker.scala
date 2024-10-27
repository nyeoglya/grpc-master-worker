package worker

import io.grpc.ServerBuilder
import task.task.{TaskRequest, TaskResponse, TaskServiceGrpc}

import scala.concurrent.Future

class TaskServiceImpl extends TaskServiceGrpc.TaskService {

  override def executeTask(request: TaskRequest): Future[TaskResponse] = {
    val result = request.functionName match {
      case "sayHello" => s"Hello, ${request.args.headOption.getOrElse("Anonymous")}!"
      case _ => "Unknown function"
    }
    Future.successful(TaskResponse(result = result))
  }
}

object WorkerServer {
  def main(args: Array[String]): Unit = {
    val port = sys.env.getOrElse("PORT", "50051").toInt

    val server = ServerBuilder
      .forPort(port)
      .addService(TaskServiceGrpc.bindService(new TaskServiceImpl(), scala.concurrent.ExecutionContext.global))
      .build()
      .start()

    println(s"Worker server started, listening on $port")
    server.awaitTermination()
  }
}

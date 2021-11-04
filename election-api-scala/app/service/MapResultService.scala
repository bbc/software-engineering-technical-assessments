package service
import model.ConstituencyResult

import scala.util.Try

class MapResultService extends ResultService {
  var internalDatabase: Map[Int,ConstituencyResult] = Map[Int,ConstituencyResult]()

  override def GetResult(id: Int): Option[ConstituencyResult] = internalDatabase.get(id)

  override def NewResult(result: ConstituencyResult): Try[Unit] = {
    internalDatabase += (result.id -> result)
    Try[Unit]()
  }

  override def GetAll: Seq[ConstituencyResult] = internalDatabase.values.toSeq

  override def reset(): Try[Unit] =  {
    internalDatabase = Map[Int, ConstituencyResult]()
    Try[Unit]()
  }
}

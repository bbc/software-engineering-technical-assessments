package service
import com.google.inject.Singleton
import model.ConstituencyResult

import scala.util.{Success, Try}

@Singleton
class MapResultService extends ResultService {
  var internalDatabase: Map[Int,ConstituencyResult] = Map[Int,ConstituencyResult]()

  override def GetResult(id: Int): Option[ConstituencyResult] = internalDatabase.get(id)

  override def NewResult(result: ConstituencyResult): Try[Unit] = {
    internalDatabase += (result.id -> result)
    Success(())
  }

  override def GetAll: Seq[ConstituencyResult] = internalDatabase.values.toSeq

  override def reset(): Try[Unit] =  {
    internalDatabase = Map[Int, ConstituencyResult]()
    Success(())
  }
}

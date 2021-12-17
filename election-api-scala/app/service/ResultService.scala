package service

import com.google.inject.ImplementedBy
import model.ConstituencyResult

import scala.util.Try

@ImplementedBy(classOf[MapResultService])
trait ResultService {
  def GetResult(id: Int): Option[ConstituencyResult]
  def NewResult(result: ConstituencyResult): Try[Unit]
  def GetAll: Seq[ConstituencyResult]
  def reset(): Try[Unit]
}

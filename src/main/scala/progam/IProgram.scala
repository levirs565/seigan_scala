package progam

import entity.Buku

import scala.collection.mutable.ArrayBuffer

trait IProgram {
  val bukuList: ArrayBuffer[Buku]

  def exit(): Unit
}

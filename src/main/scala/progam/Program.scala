package progam

import commands.{ExitCommand, ListCommand}
import entity.Buku

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readInt
import scala.util.Try

class Program extends IProgram {
  var isExit = false
  val commands = Array(
    ListCommand(this),
    ExitCommand(this)
  )

  override val bukuList: ArrayBuffer[Buku] = ArrayBuffer()

  override def exit(): Unit = {
    isExit = true
  }

  def run(): Unit = {
    while (!isExit) {
      println("Opsi:")
      for ((cmd, index) <- commands.zipWithIndex) {
        println(s"${index + 1}. ${cmd.name}")
      }

      print("Masukkan Pilihanmu: ")

      Try(readInt()).toOption
        .flatMap(i => commands.lift(i - 1)) match {
        case None =>
          println("Pilihan tidak valid")
        case Some(cmd) =>
          cmd.execute()
          println()
      }
    }
  }
}

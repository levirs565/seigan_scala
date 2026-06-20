package progam

import commands.*
import entity.Buku

import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn.readInt
import scala.util.{Failure, Success, Try}

class Program extends IProgram {
  val storage = BukuStorage()
  var isExit = false
  val commands = Array(
    ListCommand(this),
    AddCommand(this),
    EditCommand(this),
    DeleteCommand(this),
    ExitCommand(this)
  )

  override val bukuList: ArrayBuffer[Buku] = ArrayBuffer()

  override def exit(): Unit = {
    isExit = true
  }

  def run(): Unit = {
    storage.readAll() match {
      case Failure(exception) => println(s"Gagal Membaca Buku: $exception")
      case Success(value) => bukuList.addAll(value)
    }

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

    storage.saveAll(bukuList.toArray) match {
      case Failure(exception) => println(s"Gagal Menyimpan Buku: $exception")
      case Success(_) =>
    }
  }
}

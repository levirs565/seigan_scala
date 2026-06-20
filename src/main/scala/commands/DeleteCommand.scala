package commands

import progam.IProgram
import reader.BukuReadHelper.*

import scala.util.chaining.scalaUtilChainingOps

class DeleteCommand(program: IProgram) extends Command(program) {
  override val name: String = "Hapus Buku"

  override def execute(): Unit = {
    println("=== Menghapus Buku ===")

    val kode = readKode { newCode =>
      program.bukuList.exists(_.kode == newCode).tap { exist =>
        if (!exist) {
          println("Kode tidak ditemukan")
        }
      }
    }
    program.bukuList.filterInPlace(_.kode != kode)

    println(s"Berhasil menghapus buku $kode")
  }
}

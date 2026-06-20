package commands

import entity.Buku
import progam.IProgram
import reader.BukuReadHelper.*

import scala.util.chaining.scalaUtilChainingOps

class EditCommand(program: IProgram) extends Command(program) {
  override val name: String = "Ubah Buku"

  override def execute(): Unit = {
    println("=== Mengubah Buku ===")

    val kode = readKode { newCode =>
      program.bukuList.exists(_.kode == newCode).tap { exist =>
        if (!exist) {
          println("Kode tidak ditemukan")
        }
      }
    }
    val index = program.bukuList.indexWhere(_.kode == kode)
    val oldBuku = program.bukuList.lift(index) match {
      case None =>
        println("Tidak menemukan buku. Harusnya gak terjadi")
        return
      case Some(value) => value
    }
    val judul = readJudul(s"Judul (${oldBuku.judul}): ")
    val penggarang = readPenggarang(s"Penggarang (${oldBuku.penggarang}): ")
    val tahunTerbit = readTahunTerbit(s"Tahun Terbit (${oldBuku.tahunTerbit}): ")
    val harga = readHarga(s"Harga (${oldBuku.harga}): ")

    program.bukuList.update(index, Buku(kode, judul, penggarang, tahunTerbit, harga))

    println(s"Berhasil mengubah buku $kode")
  }
}

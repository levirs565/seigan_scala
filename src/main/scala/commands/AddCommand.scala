package commands

import entity.Buku
import progam.IProgram
import reader.BukuReadHelper.*

import scala.util.chaining.scalaUtilChainingOps

class AddCommand(program: IProgram) extends Command(program) {
  override val name: String = "Tambah Buku"

  override def execute(): Unit = {
    println("=== Menambah Buku ===")

    val kode = readKode { newCode =>
      !program.bukuList.exists(_.kode == newCode).tap { exist =>
        if (exist) {
          println("Kode sudah digunakan")
        }
      }
    }
    val judul = readJudul("Judul: ")
    val penggarang = readPenggarang("Penggarang: ")
    val tahunTerbit = readTahunTerbit("Tahun Terbit: ")
    val harga = readHarga("Harga: ")

    program.bukuList.addOne(Buku(kode, judul, penggarang, tahunTerbit, harga))

    println(s"Berhasil menambahkan buku $kode")
  }
}

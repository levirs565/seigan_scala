package commands

import entity.Buku
import progam.IProgram
import reader.BukuReadHelper.{readHarga, readJudul, readKode, readPenggarang, readTahunTerbit}

class AddCommand(program: IProgram) extends Command(program) {
  override val name: String = "Tambah Buku"

  override def execute(): Unit = {
    println("=== Menambah Buku ===")

    val kode = readKode(newCode => {
      if (program.bukuList.exists(_.kode == newCode)) {
        println("Kode sudah digunakan")
        false
      } else true
    })
    val judul = readJudul("Judul: ")
    val penggarang = readPenggarang("Penggarang: ")
    val tahunTerbit = readTahunTerbit("Tahun Terbit: ")
    val harga = readHarga("Harga: ")

    program.bukuList.addOne(Buku(kode, judul, penggarang, tahunTerbit, harga))

    println(s"Berhasil menambahkan buku $kode")
  }
}

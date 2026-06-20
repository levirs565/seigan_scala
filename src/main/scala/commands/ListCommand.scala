package commands

import progam.IProgram

class ListCommand(program: IProgram) extends Command(program) {
  override val name: String = "Daftar Buku"

  override def execute(): Unit = {
    println("=== Menampilkan Daftar Buku ===")
    for ((item, index) <- program.bukuList.zipWithIndex) {
      println(s"${index + 1}. Kode : ${item.kode} | Judul : ${item.judul} | Pengarang : ${item.penggarang} | Tahun Rilis : ${item.tahunTerbit} | Harga : ${item.harga}")
    }
  }
}

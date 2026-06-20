package reader

import scala.annotation.tailrec
import scala.io.StdIn.{readInt, readLine, readLong}
import scala.util.Try

object BukuReadHelper {
  def readKode(extraCandidate: String => Boolean): String = {
    while (true) {
      print("Kode: ")

      val kode = readLine()

      if (kode.length() > 4) {
        println("Kode maksimal 4 karakter")
      } else if (extraCandidate(kode)) {
        return kode;
      }
    }

    ""
  }


  def readJudul(prompt: String): String = {
    print(prompt)
    readLine()
  }

  def readPenggarang(prompt: String): String = {
    print(prompt)
    readLine()
  }

  @tailrec
  def readTahunTerbit(prompt: String): Int = {
    print(prompt)
    Try(readInt()).toOption match {
      case Some(value) if value <= 0 =>
        println("Tahun terbit harus lebih dari 0")
        readTahunTerbit(prompt)
      case None =>
        println("Tahun terbit harus angka")
        readTahunTerbit(prompt)
      case Some(value) => value
    }
  }

  @tailrec
  def readHarga(prompt: String): Long = {
    print(prompt)
    Try(readLong()).toOption match {
      case Some(value) if value <= 0 =>
        println("Harga harus lebih dari 0")
        readHarga(prompt)
      case None =>
        println("Harga harus angka")
        readHarga(prompt)
      case Some(value) => value
    }
  }
}

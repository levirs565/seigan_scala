package reader

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

  def readTahunTerbit(prompt: String): Int = {
    while (true) {
      print(prompt)
      Try(readInt()).toOption match {
        case None => println("Tahun terbit harus angka")
        case Some(value) =>
          if (value <= 0) println("Tahun terbit harus lebih dari 0")
          else return value;
      }
    }

    0
  }

  def readHarga(prompt: String): Long = {
    while (true) {
      print(prompt)
      Try(readLong()).toOption match {
        case None => println("Harga harus angka")
        case Some(value) =>
          if (value <= 0) println("Harga harus lebih dari 0")
          else return value;
      }
    }

    0
  }
}

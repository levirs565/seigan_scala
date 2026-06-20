package progam

import entity.Buku

import java.io.*
import javax.crypto.spec.{IvParameterSpec, PBEKeySpec, SecretKeySpec}
import javax.crypto.*
import scala.util.{Failure, Success, Try, Using}

class BukuStorage {
  private val fileName = "buku.txt"
  private val file = File(fileName)
  private val salt = "1234567890123456"
  private val password = "password"
  private val algorithm =  "AES/CBC/PKCS5Padding"
  private lazy val key: SecretKey = {
    val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
    val spec = PBEKeySpec(password.toCharArray, salt.getBytes,  65536, 256)
    SecretKeySpec(factory.generateSecret(spec).getEncoded, "AES")
  }

  def readAll(): Try[Array[Buku]] = {
    if (!file.exists()) return Success(Array())

    Using.Manager { use =>
      val stream = use(FileInputStream(file))
      val cipher = Cipher.getInstance(algorithm)

      val fileIv = new Array[Byte](16)
      stream.read(fileIv)
      cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(fileIv))

      val cipherIn = use(CipherInputStream(stream, cipher))
//      val cipherIn = stream
      val objectStream = use(ObjectInputStream(cipherIn))
      objectStream.readObject() match {
        case value: Array[_] => Try(value.map(_.asInstanceOf[Buku]))
        case _ => Failure[Array[Buku]](Exception("Objek tidak sesuai"))
      }
    }.flatten
  }

  def saveAll(array: Array[Buku]): Try[Unit] = Using.Manager { use =>
    val stream = use(FileOutputStream(file))
    val cipher = Cipher.getInstance(algorithm)

    cipher.init(Cipher.ENCRYPT_MODE, key)

    val iv = cipher.getIV
    stream.write(iv)

    val cipherOut = use(CipherOutputStream(stream, cipher))
    val objectStream = use(ObjectOutputStream(cipherOut))

    objectStream.writeObject(array)
  }
}

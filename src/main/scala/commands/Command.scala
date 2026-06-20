package commands

import progam.IProgram

trait Command(val program: IProgram) {
  val name: String

  def execute(): Unit
}

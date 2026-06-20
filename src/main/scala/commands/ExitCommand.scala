package commands

import progam.IProgram

class ExitCommand(program: IProgram) extends Command(program) {
  override val name: String = "Exit"

  override def execute(): Unit = {
    program.exit()
  }
}

package model

class Task (val id:Int,val name:String){

}

object Task {
  def apply(id: Int, name: String): Task = new Task(id, name)

  private var taskList = scala.List[Task]()
  private var idSeq = 0

  def tasks: List[Task] = taskList

  def addTask(nameTask:String):Unit = {
    taskList = taskList ++ (Task(idSeq,nameTask) :: scala.Nil)
    idSeq += 1
  }

  def deleteTask(idTask:Int):Unit = {
    taskList = taskList.filterNot(_.id == idTask)
  }
}

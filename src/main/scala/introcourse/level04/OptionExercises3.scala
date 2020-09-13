package introcourse.level04

import introcourse.level04.OptionExercises2._

/**
  * These exercises mirror the ones from `OptionExercises2.scala`,
  * they are for the purpose of teaching for-comprehension, which is very useful for working with `Option`.
  */
object OptionExercises3 {

  /**
    * Rewrite this function using for-comprehension syntax.
    *
    * scala> findJobIdByHumanIdUsingFor(1)
    * > None
    *
    * scala> findJobIdByHumanIdUsingFor(2)
    * > Some(1)
    */
  def findJobIdByHumanIdUsingFor(humanId: HumanId): Option[JobId] =
    for {
      human <- findHumanById(humanId)
      jobId <- human.optJobId
    } yield jobId
//    original: findHumanById(humanId).flatMap(human => human.optJobId)

  /**
    * Rewrite this function using for-comprehension syntax.
    *
    * scala> findJobByHumanIdUsingFor(2)
    * > Some(Job("Teacher", "Expert in their field"))
    */
  def findJobByHumanIdUsingFor(humanId: HumanId): Option[Job] =
    for {
      human <- findHumanById(humanId)
      jobId <- human.optJobId
      job <- findJobById(jobId)
    } yield job
//    original: findJobIdByHumanId(humanId).flatMap(jobId => findJobById(jobId))

  /**
    * Rewrite this function using for-comprehension syntax.
    *
    * scala> findJobNameByHumanIdUsingFor(2)
    * > Some("Teacher")
    *
    * scala> findJobNameByHumanIdUsingFor(1)
    * > None
    */
  def findJobNameByHumanIdUsingFor(humanId: HumanId): Option[String] =
    for {
      human <- findHumanById(humanId)
      jobId <- human.optJobId
      job <- findJobById(jobId)
    } yield job.name
//    original: findJobByHumanId(humanId).map(job => job.name)
}

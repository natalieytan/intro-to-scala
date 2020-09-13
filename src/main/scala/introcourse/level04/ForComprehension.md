# For Comprehension

For Comprehension is a powerful technique for working with values in "containers", e.g. `Option`, `Either`, `Try`, `List`, etc.

Here are some examples of for comprehension usages. Run them in a REPL and see what they output. You can start up a REPL with `./auto/sbt console`.

## Option

```scala
case class Person(name: String, age: Int)

val result1: Option[Person] = 
  for {
    name <- Some("Bob")
    age  <- Some(20)
  } yield Person(name, age)

println(result1)
// Some(Person(Bob,20))

val result2: Option[Person] = 
  for {
    name <- Some("Bob")
    age  <- None
  } yield Person(name, age)

println(result2)
// None
```

desugared:
```scala
val result1: Option[Person] =
  Some("Bob")
    .flatMap(name =>
      Some(20)
        .map(age => Person(name, age))
    )
```
## Either

```scala
case class Person(name: String, age: Int)

case class MyError(msg: String)

val result1: Either[MyError, Person] = 
  for {
    name <- Right("Bob")
    age  <- Right(20)
  } yield Person(name, age)

println(result1)
// Right(Person(Bob,20))


val result2: Either[MyError, Person] = 
  for {
    name <- Left(MyError("empty name"))
    age  <- Right(20)
  } yield Person(name, age)

println(result2)
// Left(MyError(empty name))
```

desugared:
```scala
val result1 =
  Right("Bob")
    .flatMap(name =>
      Right(20)
        .map(age => Person(name, age))
    )
```
## Try

```scala
import scala.util.Try

val result1 = 
  for {
    one    <- Try("1".toInt)
    twenty <- Try("20".toInt)
  } yield (one + twenty)

println(result1)
// Success(21)

val result2 = 
  for {
    one    <- Try("one".toInt)
    twenty <- Try("20".toInt)
  } yield (one + twenty)

println(result2)
// Failure(java.lang.NumberFormatException: For input string: "one")
```

desugared:
```scala
import scala.util.Try

val result1 =
  Try("1".toInt)
    .flatMap(one =>
      Try("20".toInt)
        .map(twenty => (one + twenty))
    )
```
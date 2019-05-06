package com.example.quickstart

import cats._
import cats.data._
import cats.implicits._
import cats.effect.{ContextShift, IO}

import doobie._
import doobie.implicits._

import scala.concurrent.ExecutionContext

class Database{
  implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)

  val transactor = Transactor.fromDriverManager[IO](
    driver = "org.sqlite.JDBC",
    url = "jdbc:sqlite::memory:",
    user = "",
    pass = ""
  )

  def execute[A](query: ConnectionIO[A]): IO[A] = query.transact(transactor)
}

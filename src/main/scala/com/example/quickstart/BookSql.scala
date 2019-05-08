package com.example.quickstart

import cats._
import cats.effect.IO
import cats.data._
import cats.implicits._

import doobie._
import doobie.Transactor
import doobie.implicits._

class BookSql(db: Database) {
  private val getBooksQuery: ConnectionIO[List[Book]] = sql"""SELECT * from books""".query[Book].to[List]
  def getBooks(): IO[List[Book]] = db.execute(getBooksQuery)


  private def getBookByIdQuery(id: Int): ConnectionIO[Book] = sql"""SELECT * from books where id = $id""".query[Book].unique
  def getBookBy(id: Int): IO[Book] = db.execute(getBookByIdQuery(id))


  private def insertBookQuery(book: Book): Update0 = sql"""insert into books(name, pageCount) VALUES (${book.name}, ${book.pageCount})""".update
  def insertBook(book: Book): IO[Book] = db.execute(insertBookQuery(book).run) >>= {id => IO.pure(book.copy(id=Some(id)))}
}


class BookDDL(db: Database) {
  val createDBQuery: ConnectionIO[Int] = sql"""
CREATE TABLE books(
  id integer PRIMARY KEY AUTOINCREMENT,
  name varchar(50) NOT NULL,
  pageCount integer NOT NULL
)
""".update.run


  val dropDBQuery: ConnectionIO[Int] = sql"""
DROP TABLE IF EXISTS books
""".update.run

  def recreateTable(): IO[Int] = db.execute((dropDBQuery, createDBQuery).mapN(_ + _))
}


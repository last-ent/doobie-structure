package com.example.quickstart

import doobie.util.Read
import java.time.{ Instant, ZonedDateTime }

case class Book(id: Option[Int], name: String, pageCount: Int, date: ZonedDateTime)

object Book {
  implicit val reader: Read[Book] =
    Read[(Option[Int], String, Int, Long)].map{case (idOpt, nm, pgCnt, dt) =>
      Book(idOpt, nm, pgCnt, ZonedDateTime.from(Instant.ofEpochMilli(dt)))
    }
}

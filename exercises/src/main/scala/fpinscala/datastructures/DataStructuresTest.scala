package fpinscala.datastructures

import org.scalatest.FunSuite

/**
 * Created by af on 9/6/15.
 */
class DataStructuresTest extends FunSuite {
  test("map by cons") {
    assert(List.map(List(1, 2, 3))(x => x + 1) == List(2, 3, 4))
  }

  test("tail") {
    assert(List.tail(List(1, 2, 3)) == List(2, 3))
  }

  test("setHead") {
    assert(List.setHead(List(1, 2, 3), 4) == List(4, 2, 3))
  }

  test("drop") {
    assert(List.drop(List(1, 2, 3, 4), 2) == List(3, 4))
  }

  test("dropWhile") {

    val result = List.dropWhile(List(1, 1, 1, 2), (x: Int) => x != 2)
    assert(result == List(2))
  }

  test("length") {
    assert(List.length(List(1, 2, 3, 4)) == 4)
  }

  test("init") {
    assert(List.init(List(1, 2, 3, 4)) == List(1, 2, 3))
  }

  test("foldRight play") {
    println(List.foldRight(List(1, 2, 3), Nil: List[Int])(Cons(_, _)))
  }

  test("foldLeft") {
    assert(List.foldLeft(List(1, 2, 3), 0)(_ + _) == 6)
  }

  test("reverse") {
    assert(List.reverse(List(1, 2, 3)) == List(3, 2, 1))
  }

  test("mapByFold") {
    assert(List.mapByFold(List(1, 2, 3))(x => x + 1) == List(2, 3, 4))

  }

  test("append") {
    assert(List.concat(List(1, 2), List(3, 4)) == List(1, 2, 3, 4))
  }

  test("appendMulti") {
    assert(List.flatten(List(List(1, 2), List(3, 4))) == List(1, 2, 3, 4))
  }

  test("filter") {
    assert(List.filter(List(1, 2, 3))(_ != 3) == List(1, 2))
  }

  test("zipWith") {
    assert(List.zipWith(List(1, 2, 3), List(4, 5, 6))(_ + _) == List(5, 7, 9))
  }

}

package funsets

import org.junit._

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite {

  import FunSets._

  @Test def `contains is implemented`: Unit = {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   * val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)

    val s12 = union(s1, s2)
    val s123 = union(s12, s3)
    val s1234 = union(s123, s4)
    val s34 = union(s3, s4)
  }

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remvoe the
   *
   * @Ignore annotation.
   */
  @Test def `singleton set one contains one`: Unit = {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  @Test def `union contains all elements of each set`: Unit = {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  @Test def `intersection contains all elements that are in both sets`: Unit = {
    new TestSets {
      val s = intersect(s123, s34)
      assert(contains(s, 3), "Intersect 1")
      assert(!contains(s, 1), "Intersect 2")
      assert(!contains(s, 4), "Intersect 3")
    }
  }

  @Test def `difference contains all elements of 1st set that is not in 2nd set`: Unit = {
    new TestSets {
      val s = diff(s123, s34)
      assert(contains(s, 1), "Diff 1")
      assert(!contains(s, 3), "Diff 2")
      assert(!contains(s, 4), "Diff 3")
    }
  }

  @Test def `filter returns elements of the set that satisfy given predicate`: Unit = {
    new TestSets {
      val s = filter(s1234, _ % 2 == 0)
      assert(contains(s, 2), "Filter 1")
      assert(contains(s, 4), "Filter 2")
      assert(!contains(s, 1), "Filter 3")
      assert(!contains(s, 3), "Filter 4")
    }
  }

  @Test def `forAll returns whether all elements in the set satisfy given predicate`: Unit = {
    new TestSets {
      val s = forall(s1234, _ < 5)
      val ss = forall(s1234, _ % 2 == 0)
      assert(s, "ForAll 1")
      assert(!ss, "ForAll 2")
    }
  }

  @Test def `exists returns whether at least one element in the set satisfies given predicate`: Unit = {
    new TestSets {
      val s = exists(s1234, _ < 2)
      val ss = exists(s1234, _ == 5)
      assert(s, "Exists 1")
      assert(!ss, "Exists 2")
    }
  }

  @Test def `map returns the set transformed by applying the given function`: Unit = {
    new TestSets {
      val s = map(s123, _ * 2)
      assert(contains(s, 2), "Map 1")
      assert(contains(s, 4), "Map 2")
      assert(contains(s, 6), "Map 3")
      assert(!contains(s, 1), "Map 4")
      assert(!contains(s, 3), "Map 5")
    }
  }

  @Rule def individualTestTimeout = new org.junit.rules.Timeout(10 * 1000)
}

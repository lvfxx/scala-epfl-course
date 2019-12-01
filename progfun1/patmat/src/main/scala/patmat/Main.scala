package patmat

import Huffman._

object Main extends App {
  val rawText = decode(frenchCode, secret)
  //  println(rawText)
  val newSecret = encode(frenchCode)(rawText)
  val newRawText = decode(frenchCode, newSecret)
  //  println(secret)
  //  println(newSecret)
  //  println(newRawText)
  val newSecret2 = quickEncode(frenchCode)(rawText)
  val newRawText2 = decode(frenchCode, newSecret)

  assert(newSecret == secret)
  assert(newRawText == rawText)

  assert(newSecret2 == secret)
  assert(newRawText2 == rawText)
}

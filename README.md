# Advent of Code 2022 - My solutions in Kotlin

## Usage of the template for new / daily problem statements

1. Copy `DayTemplate.kt` to `DayXX.kt`.
2. Update the `day` parameter of the `aocTaskWithExample` function call.  
3. Paste inline example to `val example`, in case of multiple examples, use `listOf()` (then change the
   function call from `aocTaskWithExample` to `aocTaskWithExamples` .
4. Adjust expected output of the first part from text (parameter `expectedOutputPart1` of functions `aocTaskWithExample`
   and `aocTaskWithExamples`); use `listOf()` in case of multiple examples.
5. Download the puzzle input to file `input/2022_day_XX.txt`.
6. Solve the first part of the problem statement in the function `private fun part1(input: List<String>)`.
7. Run the main function and check the output. The examples are tested before using the puzzle input.
8. Solve the second part of the problem statement in the function `private fun part2(input: List<String>)`.
9. Uncomment the reference to the function `part2` in the function call `aocTaskWithExample` or `aocTaskWithExamples`. 
  
## License

      MIT License
      
      Copyright (c) 2022 Marek Šiller
      
      Permission is hereby granted, free of charge, to any person obtaining a copy
      of this software and associated documentation files (the "Software"), to deal
      in the Software without restriction, including without limitation the rights
      to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
      copies of the Software, and to permit persons to whom the Software is
      furnished to do so, subject to the following conditions:
      
      The above copyright notice and this permission notice shall be included in all
      copies or substantial portions of the Software.
      
      THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
      IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
      FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
      AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
      LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
      OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
      SOFTWARE.

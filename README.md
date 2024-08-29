### Calculator

* `operations.Operation` enum
  * defines four required operations as an enum.
* `operation.OperationStrategy` interface
  * declares a `apply` method, the execution strategy of an operation
* `operations.impl.*` class
  * implements the operation strategy of the four required operations
  * the strategies also judge if the calcuation is overflow.
* `exception.UnsupportedOperationException`
  * a custom exception extends `IllegalArgumentException` for unsupported operation requests.
* `exception.OperationOverflowException`
  * The calculator is designed to handle results within `[Integer.MIN, Integer.MAX]`.
* `CalculationFactory` class
  * Used factory pattern for extensibility.
  * To support a future operation, add an entry to map.
* `Calculator` class
  * static method `calculate`: for non-stateful one-step calculation
  * method `chaining`: support chaining calculation
    * enables calling in a stream API manner by returning `Calculator` itself.
  * Enables calling operators by symbols through method overloading
    * handles unsupported symbols
  * method `getRes`: terminates the chaining, reset calculator, and return result.
* `test/java/org.example.calculator/CalculatorApplicationTests` class
  * Test multiple cases including integer input, decimal input, overflow edge cases, chaining, divide by 0, unsupported operation, declare operator by symbol, etc, with Spring Test
  * The calculator was developed in a TDD manner.
* IoC Compatibility
  * The calculator is developed under a Spring Boot environment which provides great IoC compatibility. By adding a controller layer, this calculator can be easily shipped to web users.
* Error Handling
  * Whenever divided by 0 in calculation, an `ArithmeticException` will be thrown with a message.
  * Whenever calculation overflows, a custom `OperationOverflowException` will be thrown with a message.
  * When unsupported operators are called, a custom `UnsuporttedOperationException` will be thrown with a message.

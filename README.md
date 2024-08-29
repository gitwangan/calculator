# Calculator

### How this design fits the requirements
* Operations
  * `Operation` enum defines four required operators. By overriding the `apply` method, they act differently.
  * Support calling operators by name (`ADD`) or symbol (`+`).
* Basic Calculations
  * Implemented by `calculate` method in `Calculator` class.
* Chaining operations
  * Implemented by `chaining` method in `Calculator` class.
  * `reset` method analogues the reset button in a real calculator which you have to hit before a new chaining calculation.
  * `getVal` method analogues the `=` button in a real calculator.
* Extensibility 
  * To add new operation, simply adding a new entry in the `Operation` enum; override the `apply` method; and give a symbol to the new operation.
  * Consider using factory pattern instead of the enum structure (which is required by this assignment) for better extensibility for new custom operators.
* IoC Compatibility
  * The calculator is developed under a Spring Boot environment which provides great IoC compatibility. By adding a controller layer, this calculator can be easily shipped to web users.
* Error Handling
  * Whenever divided by 0 in calculation, an `ArithmeticException` will be thrown.
  * When unsupported operators are called (by symbol), an `IllegalArgumentException` will be thrown with a message.
* Testing
  * Testing is done with Spring Test, see the test package.
  * The calculator was developed in a TDD manner.
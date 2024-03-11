# Bank application

This project simulates a bank application through a terminal. This application contains features such as: **Create a new employee account bank**, **Deposit money**, **Withdraw money**, **Check an employee account** and **List all employees**.

This project was built using **Java Scanner** to simulate a bank using the user's terminal.

## Usage

This program doesn't require an installation, just follow the following steps to execute the program:

1. Open your favorite IDE (make sure it supports Java compiler).
2. Hit the "Run" button in your IDE.
3. Test the application.

## License

Distributed under the [MIT License](https://opensource.org/licenses/MIT).

## Contact

For any queries or issues, please contact the project owner.

## Detailed task (unfortunately, in Spanish)

## Realizar métodos para poder agregar o retirar dinero de la cuenta bancaria. Considerar lo siguiente:

1. La cuenta bancaria debe de ser alguno de estos tipos: A, B o C y debe de incluirse al momento de de registrar un usuario con una cuenta. Si se ingresa un valor diferentes a esos 3 advertirle al usuario y no permitir darlo de alta.

   - Si el número de cuenta es de tipo A la cuenta puede tener hasta $50,000
   - Si el número de cuenta es de tipo B la cuenta puede tener hasta $100,000
   - Si el número de cuenta es de tipo C la cuenta puede tener saldo ilimitado

   - Al momento de retirar dinero de la cuenta el saldo mínimo para la cuenta de tipo de tipo A es de $1,000
   - Al momento de retirar dinero de la cuenta el saldo mínimo para la cuenta de tipo de tipo B es de $5,000
   - Al momento de retirar dinero de la cuenta el saldo mínimo para la cuenta de tipo de tipo C es de $10,000

2. Crear un método para poder ver la cuenta de algún empleado (SI ES QUE CUENTA CON ELLA) con el siguiente formato:

   - "El número de cuenta del empleado Pedro es 12345, su saldo es 10000 y la cuenta es de tipo A" (los datos son variables)

3. Realizar todas las validaciones correspondientes y advertirle al usaurio antes de hacer cualquier modificación que debes utilizar la `sobrecarga de métodos` para que existan funciones con el mismo nombre pero realicen una función diferente.

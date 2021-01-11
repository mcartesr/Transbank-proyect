# Transbank-proyect
Integración servicio REST con transbank

# PASO 1
Clonar el proyecto en su maquina local y ejecutar el siguiente comando desde la ruta en donde guardara el proyecto. 
Comando: git clone https://github.com/mcartesr/Transbank-proyect.git

# PASO 2
Levantar el proyecto localmente, este se levantara en el puerto 8080 por defecto.

# PASO 3
Importar en postman el archivo "CreateTransaction.postman_collection.json" definida en la ruta src/postman_collection dentro del proyecto, este contendra 5 peticiones con su configuración correspondiente para el flujo de transbank.

- Crear transacción: esta request enviara en el body la orden de la compra, identificador de la sesion, monto de la compra, cvv, numero de tarjeta de crédito, fecha de expiración, En el caso de que la respuesta de la solicitud sea exitosa retornará un token que utilizaremos en el resto del flujo.

REQUEST BODY:

{
  "buyOrder": "ordenCompra12345678",
  "sessionId": "sesion1234564",
  "amount": 10000,
  "cvv": 123,
  "cardNumber": "4051885600446623",
  "cardExpirationDate": "22/10"
}

RESPONSE:

{
    "success": true,
    "data": {
        "tokenId": "01ab72504a1c092b9f16c27a13f7c00dad833d3916d70be4cae8fea1588df1e5"
    }
}

- Consulta de cuotas: este request enviara en el body el token generado anteriormente y el numero de cuotas. En el caso de que la respuesta de la solicitud sea exitosa retornará un identificador de la consulta de cuotas y el monto de cada cuota.

REQUEST BODY:

{
  "token": "01abf73617266fd919328bc47e4994a2f565ab045524e2e040402f29e2556442",
  "cuotas": 2
}

RESPONSE:

{
    "success": true,
    "data": {
        "idQueryInstallments": 31347849,
        "deferredPeriod": [],
        "installmentsAmount": 5000.0
    }
}

- Confirmación transacción: este request enviara en el body el token generado anteriormente y el identificador de la consulta de cuotas generado en el response anterior (consulta de cuotas). En el caso de que la respuesta de la solicitud sea exitosa retornará objeto con la información de la compra.

REQUEST BODY:

{
  "token": "01ab89ac53dd9015d5d26649d4fd00ce637afc3593a253801bfe00c10e58b9d4",
  "idQueryInstallments": 31347849
}

RESPONSE:

{
    "success": true,
    "data": {
        "accountingDate": "0111",
        "amount": 10000.0,
        "authorizationCode": "1213",
        "paymentTypeCode": "S2",
        "sessionId": "sesion1234564",
        "transactionDate": "2021-01-11T06:43:02.149Z",
        "buyOrder": "ordenCompra12345678",
        "cardNumber": 4051885600446623,
        "installmentsNumber": 2,
        "responseCode": 0
    }
}

- Consulta estado de transacción: este request enviara en el body el token generado anteriormente. En el caso de que la respuesta de la solicitud sea exitosa retornará objeto con la información de la compra.

REQUEST BODY:

{
    "token": "01ab89ac53dd9015d5d26649d4fd00ce637afc3593a253801bfe00c10e58b9d4"
}

RESPONSE:

{
    "success": true,
    "data": {
        "accountingDate": "0111",
        "amount": 10000.0,
        "authorizationCode": "1213",
        "paymentTypeCode": "S2",
        "sessionId": "sesion1234564",
        "transactionDate": "2021-01-11T06:43:02.149Z",
        "buyOrder": "ordenCompra12345678",
        "cardNumber": null,
        "installmentsNumber": 2,
        "responseCode": 0
    }
}

- Reservar o cancelar transacción: este request enviara en el body el token generado anteriormente y el monto. En el caso de que el monto ingresado sea igual al valor de la compra se hara la reserva en caso contrario si se ingresa un monto menor a la de la compra se hara una anulación parcial.

REQUEST BODY:

{
    "token": "01abb1f31791c72ac1f12e2aa9058642459d62dd963833269a225d23af451b1e",
    "amount": 10000
}

RESPONSE:

{
    "success": true,
    "data": {
        "nullifiedAmount": 0.0,
        "Type": "REVERSED",
        "authorizationDate": null,
        "balance": 0.0,
        "authorizationCode": null,
        "responseCode": 0
    }
}

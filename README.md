# personalSoftTest


## 🔐 Autenticación y uso de la API
Para poder acceder a los endpoints protegidos de esta aplicación, es necesario seguir los siguientes pasos a través de Swagger:

### 1️⃣ Registrar un usuario
Primero, debes registrar un usuario enviando una solicitud POST a la ruta:
/autenticacion/registrar

**Cuerpo de ejemplo (JSON):**

```json
{
"username": "root",
"password": "123",
"roles": ["ADMIN"]
}
```
Puedes personalizar el nombre de usuario, contraseña y roles según sea necesario.

### 2️⃣ Iniciar sesión

Después del registro, debes autenticarte con ese mismo usuario. Para ello, envía una solicitud POST a la ruta:
/autenticacion/logear

**Cuerpo de ejemplo (JSON):**
```json
{
"username": "root",
"password": "123"
}
```

La respuesta incluirá un token JWT similar al siguiente:

```json
{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290IiwiaWF0IjoxNzUyNjE.....",
"username": "root",
"rol": ["ADMIN"]
}
```


### 3️⃣ Autorizar en Swagger

Una vez obtenido el token, sigue estos pasos para autorizarte en Swagger:

1. Haz clic en el botón **"Authorize"** (ícono de candado en la parte superior derecha).
2. En el campo `value`, pega el token obtenido en el login, incluyendo el prefijo `Bearer`.  
   **Ejemplo:**
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb290Iiw...

3. Haz clic en **"Authorize"** y luego en **"Close"**.

Una vez autorizado correctamente, podrás consumir todos los endpoints protegidos de la API según los roles asignados.

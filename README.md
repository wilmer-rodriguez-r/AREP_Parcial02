# AREP_Parcial02

En este parcial se elaboro la siguiente arquitectura:

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/e82496a1-ec14-4848-9c75-7353329947fb)

* En la lógica el paquete mathService es el que se encarga de ejecutar la secuencia de lucas
* El paquete proxyService es el que contiene la lógica para el servidor proxy, el cual realiza round robin y peticiones REST a nuestra API de mathService.

# Ejecutando

Para ejecutar este proyecto se necesitan mínimo estas tecnologías.

* Maven
* Java
* Git

Para traer el proyecto se usa el siguiente comando:

```
git clone https://github.com/wilmer-rodriguez-r/AREP_Parcial02.git
```
Luego ejecutamos este comando para que traiga nuestras dependencias:

```
mvn package
```
Para ejecutar el proxy se usa el siguiente comando:

```
java -cp "target/classes;target/dependency/*" org.example.proxyService.ProxyServer
```

Para ejecutar el mathService se usa el siguiente comando:

```
java -cp "target/classes:target/dependency/*" org.example.mathService.LucasServer
```
Hay que tener en cuenta que la dirección de los servicios esta quemado en código, si quisiera cambiarlos deberia acceder al archivo de ProxyServer y modificar esta parte del código con sus servicios.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/f26364d4-d796-47bb-a1ff-00cbb703cf91)

#Desplegado

Cuando ejecutamos nuestros servicios podremos acceder al siguiente enlace http://localhost:5000, y aparecera lo siguiente:

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/5cac1381-da4e-4d7d-b841-3977a67106c7)

Si le pedimos que calcule la secuencia nos mostrara la siguiente respuesta:

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/c202f726-a12c-493a-8b69-708283b3c98c)

Además en consola podemos corroborar que esta haciendo las solicitudes de cálculo a los distintos servicios.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/0d0dfbb5-0889-42a5-981d-c062d435c31b)

# Corriendo en AWS

Para desplegar en AWS debemos garantizar que la maquina virtual tenga java, maven y github. En este caso creamos tres maquinas virtuales en AWS.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/c1911dec-e29c-467f-b19a-d2f254a068ef)

Despues de haber instalado maven y git en cada una, traeremos el proyecto de Git.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/d42a8855-8115-472f-b853-719a22406af5)

Es el mismo comando que aparece en un principio del readme.

Luego entramos a la carpeta que nos creo y ejecutamos el siguiente comando.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/cb2f4c05-fbbb-4f1e-bcf9-0d4fd4ac727e)

Por ultimo ejecutaremos el servidor correspondiente, en este caso vamos a ejecutar el proxy server primero:

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/dc65cbf9-925c-415b-9f26-4fa82c00b1e4)

Y para el mathService es el siguiente comando:

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/aec835d5-6597-438e-8389-363acc1601b3)

Despues de ejecutar los comandos, verificamos si los servicios corren. En este caso desplegamos una maquina proxy y dos maquinas con mathService.

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/c8795f91-3f9c-4253-87bc-3a71b4b751ad)

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/0f9b7465-ca02-4dc8-b616-a522bfd7c938)

![image](https://github.com/wilmer-rodriguez-r/AREP_Parcial02/assets/77862048/24a361b4-d79c-4dcf-9e0e-47247a6fd9cd)

Ya con esto tendriamos desplegado nuestra arquitectura propuesta en un principio.

# Video

El video de muestra se encuentra en la carpeta Video, ya que el archivo es muy grande y no me deja ponerlo en el README.









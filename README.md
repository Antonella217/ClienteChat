# ClienteChat

Aplicación cliente de un chat por sockets en Java. Se conecta a un servidor remoto e intercambia mensajes en tiempo real mediante hilos concurrentes.

## 📋 Descripción

Este proyecto forma parte de un laboratorio de Arquitectura de Software. Implementa el lado **cliente** de un sistema de chat distribuido que se comunica con [ServidorChat](https://github.com/Antonella217/ServidorChat) a través de sockets TCP.

## ⚙️ Funcionalidades

- Solicita al usuario la dirección IP y el puerto del servidor por consola.
- Establece conexión vía sockets con el servidor.
- Usa dos hilos para permitir comunicación bidireccional simultánea:
  - **Hilo principal:** lee del teclado y envía mensajes al servidor.
  - **Hilo ReceptorMensajes:** recibe mensajes del servidor y los muestra en consola.

## 🏗️ Arquitectura
  CLIENTE
├─ main (hilo principal)
│  └─ lee teclado → envía al servidor
└─ ReceptorMensajes (hilo daemon)
└─ lee del servidor → imprime en consola

## 🚀 Ejecución

### Requisitos
- Java JDK 8 o superior
- Tener el servidor (ServidorChat) corriendo previamente

### Compilar
```bash
javac ClienteChat.java
```

### Ejecutar
```bash
java ClienteChat
```

Al iniciar, te pedirá:
Ingrese la direccion del servidor (ej: localhost o 192.168.1.x):
Ingrese el puerto del servidor (ej: 5000):

## 🛠️ Tecnologías

- Java
- Sockets TCP
- Hilos (Threads)

## 👤 Autora

Antonella — Arquitectura de Software
<img width="850" height="180" alt="image" src="https://github.com/user-attachments/assets/69fcc69e-57bd-4585-975b-abd7669de363" />



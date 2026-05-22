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

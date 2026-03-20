# Sistema de Ventas para Android

Aplicación móvil para la gestión de productos y clientes. Este proyecto permite guardar la información tanto en el dispositivo como en la nube, asegurando que la aplicación funcione incluso sin conexión a internet.

## Funcionalidades
* Gestión de productos (agregar, ver, editar y eliminar).
* Registro de clientes.
* Sincronización de datos en tiempo real con la nube.
* Soporte para uso sin conexión (offline).

## Arquitectura y Tecnologías
El proyecto está construido aplicando buenas prácticas de desarrollo para mantener el código ordenado y fácil de escalar:
* Arquitectura Limpia (Clean Architecture) para separar responsabilidades.
* Patrón MVVM (Modelo-Vista-ViewModel) en la capa de presentación.
* Kotlin y Jetpack Compose para el diseño de la interfaz.
* Room para la base de datos local.
* Firebase Firestore para la base de datos remota.
* Dagger Hilt para la inyección de dependencias.

## Cómo ejecutar el proyecto
1. Clona este repositorio en tu computadora.
2. Por motivos de seguridad, el archivo de conexión a la base de datos no está incluido en el código público. Para que funcione, debes crear un proyecto en la consola de Firebase, habilitar Firestore, descargar tu propio archivo `google-services.json` y pegarlo dentro de la carpeta `app/`.
3. Abre el proyecto en Android Studio, espera a que se sincronicen las dependencias de Gradle y ejecútalo.


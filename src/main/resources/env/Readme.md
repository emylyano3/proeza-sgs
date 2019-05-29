# Ambientes de ejecución

## Test
Para ejecutar en este ambiente:
- Crear en una instancia de MySQL una base de datos con el nombre `sgs_proeza_test_db`
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P test`

La aplicación creará el modelo con datos de testing.
Cada vez que se despliegue la APP en este ambiente el modelo se regenera nuevamente.

## Development
Para ejecutar en este ambiente:
- Crear en una instancia de MySQL una base de datos con el nombre `sgs_proeza_dev_db`
- Ejecutar en orden los archivos SQL de la carpeta `\modelo`
- Ejecutar en orden los archivos SQL de la carpeta `\modelo\changelog`
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P dev`

## Cloud
Para ejecutar en este ambiente:
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P cloud`

Este ambiente utiliza una base de datos en la nube a través de un servicio de Google Cloud.
Al día de la fecha (29/5/2019) el servicio está activo y es gratuito. En el momento en que se vuelva pago esta opción quedara inutilizable.
Para poder correr en modo `cloud` es necesario instalar credenciales en la maquina. Ver `\cloud\Readme.md`

## Producción
El perfil `prod` sólo se usa para armar los binarios finales para producción.
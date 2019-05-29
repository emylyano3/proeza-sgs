# Ambientes de ejecución

## Test
Para ejecutar en este ambiente:
- Crear en una instancia de MySQL una base de datos con el nombre `sgs_proeza_test_db`
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P test`

La aplicacion creara el modelo con datos de testing.
Cada vez que se despliegue la APP en este ambiente el modelo regenera nuevamente.

## Development
Para ejecutar en este ambiente:
- Crear en una instancia de MySQL una base de datos con el nombre `sgs_proeza_dev_db`
- Ejecutar en orden los archivos SQL de la carpeta `\modelo`
- Ejecutar en orden los archivos SQL de la carpeta `\modelo\changelog`
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P dev`

La aplicacion creara el modelo con datos de testing.
Cada vez que se despliegue la APP en este ambiente el modelo regenera nuevamente.

## Cloud
Para ejecutar en este ambiente:
- Ejecutar en maven el comando 
> `mvn clean tomcat7:run -P cloud`

Este ambiente utiliza una base de datos en la nuebe a traves de un servicio de Google Cloud.
Al dia de la fecha el servicio esta activo y es gratuito. En el momento en que se vuelva pago esta opcion quedara inutilizable.
Para poder correr en modo Cloud es necesario instalar credenciales en la maquina. Ver `\cloud\Readme.md`

## Producción
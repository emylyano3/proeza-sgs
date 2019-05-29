# Cloud RDBMS (MySQL) con Google Cloud SQL

Documentacion de los pasos que se efectuaron para la implementacion de una base de datos en la nube.

## Acciones WEB

1. Suscribirse al servicio [Google Cloud](https://cloud.google.com/)
2. Ingresar al [Dashboard](https://console.cloud.google.com/home/dashboard)
3. Crear un [Proyecto](https://console.cloud.google.com/projectcreate)
4. Crear una [Instancia](https://console.cloud.google.com/sql/create-instance-mysql)
5. Crear una base de datos
6. Crear un bucket (o segmento) en [Cloud storage](https://console.cloud.google.com/storage/browser)
7. Subir un dump file al bucket (se detalla como crear un dump file en las acciones local)
8. Importar los datos a la base creada desde el dump file subido
9. Crear una cuenta de servicio y asignarle el rol de _project owner_ [Service Accouts](https://console.cloud.google.com/iam-admin/serviceaccounts)
10. Activar SQL Admin desde [API's](https://console.cloud.google.com/apis/library). Filtrar por _SQL Admin_ y habilitar la API.
11. Generar el archivo de [Credenciales](https://console.cloud.google.com/apis/credentials).
Seleccionar _Service Account Keys_ y asociarla a la cuenta de servicio creada anteriormente.

## Acciones LOCAL

Crear un dump file de la base sin triggers, vistas o stored procedures
> `mysqldump --databases <databade> -u<user> -p<pass> --hex-blob --skip-triggers --set-gtid-purged=OFF --ignore-table='<database>.<view_name>' --default-character-set=utf8mb4 > <path_to_dump_file>`

Configurar la variable de entorno que apunte al archivo de credenciales (creado anteriormente en acciones WEB)

>` set GOOGLE_APPLICATION_CREDENTIALS = <path_to_file>`

Agregar la dependencia para el [Socket Factory](https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory) (libreria de google que permite conectar a bases Cloud sin la necesidad de lidiar con listas blancas de IP´s o certificados SSL)

```
<dependency>
	<groupId>com.google.cloud.sql</groupId>
	<artifactId>mysql-socket-factory-connector-j-8</artifactId>
	<version>1.0.10</version>
</dependency>
 ```
Cambiar la version del driver msql (connector) a 8.x
```
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<version>8.0.12</version>
</dependency>
```
 
Configurar la conexion 
```
# --------- Data Source --------- 
datasource.url=jdbc:mysql://google/<db_name>?cloudSqlInstance=<instance_name>&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false
datasource.username=user
datasource.password=pass
datasource.driver=com.mysql.cj.jdbc.Driver
```
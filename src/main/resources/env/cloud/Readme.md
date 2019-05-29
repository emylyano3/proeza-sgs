# Configuracion del data source para Google Cloud SQL

Para evitar lidiar con listas blancas de IP´s o certificados SSL se usa la libreria: **com.google.cloud.sql.mysql.SocketFactory**. 
Esta libreria es configurada directamente en la url del data source (ver app-config.properties para en ambiente cloud)

> datasource.url=jdbc:mysql://google/<db_name>?cloudSqlInstance=<instance_name>&socketFactory=**com.google.cloud.sql.mysql.SocketFactory**&useSSL=false

SocketFactory utiliza [ADC](https://developers.google.com/identity/protocols/application-default-credentials) para la autenticacion, por eso es necesario que cuando se despliega la APP como una APP externa (corriendo fuera de la red de google) se setee en las variables de entorno del sistema la variable **GOOGLE_APPLICATION_CREDENTIALS** donde el valor de la misma es el path al archivo que contiene las credenciales.

>` set GOOGLE_APPLICATION_CREDENTIALS = <path_to_file>`

Sin esta variable de entorno la APP no será capaz de conectarse a la base de datos remota.

Para crear el archivo JSON con las credenciales:  [Creating and managing service account keys](https://cloud.google.com/iam/docs/creating-managing-service-account-keys)

El archivo tendrá la forma del siguiente:
```
{
"type": "service_account",
"project_id": "[PROJECT-ID]",
"private_key_id": "[KEY-ID]",
"private_key": "-----BEGIN PRIVATE KEY-----\n[PRIVATE-KEY]\n-----END PRIVATE KEY-----\n",
"client_email": "[SERVICE-ACCOUNT-EMAIL]",
"client_id": "[CLIENT-ID]",
"auth_uri": "https://accounts.google.com/o/oauth2/auth",
"token_uri": "https://accounts.google.com/o/oauth2/token",
"auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
"client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/[SERVICE-ACCOUNT-EMAIL]"
}
```
 
**Referencias:** [Socket factory](https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory) 
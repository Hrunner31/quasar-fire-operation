
# OPERACIÓN FUEGO DE QUASAR

Proyecto creado en lenguaje **Java version 8** usando como Framework de desarrollo **Spring Boot version 2.6.2**.
El siguiente enlace los redireccionará al servicio desplegado en **AWS**: [Operación Fuego de Quasar](http://quasarfireoperation-env.eba-8iabjmhj.us-east-1.elasticbeanstalk.com/swagger-ui/index.html).

**Operación Fuego de Quasar**, es un proyecto que a través del cálculo de las distancias que tiene la nave espacial rebelde con respecto a los satélites y las posiciones que tiene estos mismos, poder calcular la coordenada de la posición de la nave rebelde, adicional poder armar el mensaje que está tratando de transmitir la nave rebelde. 

## Arquitectura de software

A continuación se exponen las diferentes vistas de la arquitectura utilizada para el desarrollo de software.
### Vista de paquetes
Se definieron 6 paquetes principales para el componente de software, como se puede ver en la siguiente imagen:
![PackageViewImage](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/packageView-img.png?raw=true)

### Vista de clases
A continuación se expone el diagrama de clases que representa la lógica del controlador, de los servicios, de las diferentes excepciones que se manejan dentro del proyecto y aquellas utilidades transversales entre los diferentes servicios.
![ClassViewImage](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/classView-img.png?raw=true)

Se siguieron los principios de **SOLID** para separar las responsabilidades de calcular las coordenadas de la nave y construir el mensaje desfasado en diferentes servicios; adicional se empleó el patrón de diseño **FACADE** en los servicios, esto con el objetivo de separar toda la lógica de calcular las coordenadas de la nave y la construcción del mensaje para implementarlo si se desea en un microservicio y se pueda reutilizar está lógica.

También se separa las responsabilidades a través de capas, controlando la comunicación entre ellas a través de interfaces y el principio de inyección de dependencias.

### Patrones de diseño

 - **Patron Facade**
Es un patrón que permite estructurar el subsistema en capas. Este patrón se implementó en el proyecto para reducir el acoplamiento en la implementación del servicio. De tal manera, que la clase **SpaceMissionService** se encargará de comunicarse con la interfaz **ISecretSpaceMissionService**  con el fin, de que sea esta la clase encargada de realizar la comunicación con **ILocationService** y **IMessageService**, para encapsular la lógica de esas funcionalidades hacia **SpaceMissionService**. Esto también permitirá reducir el esfuerzo de futuras versiones de la implementación definida, dado que lo único que se tendría que cambiar serían los métodos de la fachada. Así mismo permitirá, generar un proyecto fachada, para que pueda ser reutilizado por otros microservicios o proyectos que lo requieran.

### Principios SOLID usados

 - Single Responsibility Principle (SRP)
 - Interface Segregation Principle (ISP)
 - Dependency Inversion Principle (DIP)

Un ejemplo muy claro en el uso de uno de estos principios **SOLID**, fue en la implementación del ejercicio en el **nivel 3**, que consistió en crear un método POST para almacenar la información de los satélites y otro método GET para obtener la información de la posición y el mensaje de la nave. Para esto, se implementó el principio SOLID DIP; el cual consta de un Proveedor de almacenamiento, que en este caso es **IStorageProvider** que se encarga de definir, lo que las clases concretas deberán implementar. En este caso, **CacheStorageProvider** será la clase encargada de almacenar en caché la información que viene del servicio. Sin embargo, si a futuro se requiere almacenar en base de datos o en otro tipo de implementación de caché como **redis**; sólo se tendría que hacer la implementación de la interfaz **IStorageProvider** y realizar la inyección de dependencia de esta a **SecretSpaceMissionService**.

### Elastic Beanstalk [EC2](https://aws.amazon.com/ec2)
Para la publicación del API se utilizó la nube de AWS, que nos brinda un servicio llamado **Elastic Beanstalk que nos brinda un wizard para implementar y escalar aplicaciones web. Nos permite cargar nuestro código y maneja el equilibrio de carga, la gestión de registros y métricas, las alertas, la gestión de la versión de la aplicación y la resolución de DNS de forma transparente.
![ElasticBeanstalk](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/elasticBeanstlk-img.png?raw=true)

## Tecnologías y herramientas

 - Java 8 con Spring Boot 2.6.2.
 - Maven
 - project Lombok
 - Junit5
 - Log4j2 : 2.17.1
 - Swagger OpenAPI
 - trilateration
 
## Consideraciones a tener en cuenta

 - Para usar la librería lombok, en el caso de que al ejecutar el proyecto no funcione, se debe ir a la carpeta 
.m2\repository\org\projectlombok\lombok y ejecutar el jar de lombok, colocando el directorio donde se encuentra el IDE.

 - Si se desea calcular las coordenadas de posición de la nave rebelde, con más de los tres satélites solicitados en la prueba, debe ir al archivo properties de la aplicación y agregar los satélites a dicho archivo conservando la estructura que este tiene. Adicional, en la clase Constant del paquete de utilidades realizar el cambio de la siguiente constante **SATELLITE_NUMBERS**, por el valor del número de satélites que tiene la lista del archivo properties.

- Para ejecutar el proyecto en local debe tener instalado Java 8 y maven.
- Si se va a utilizar un IDE diferente a STS (Spring Tools Suite) sebe tener en cuenta el plugin de Spring Boot para la compilación de este proyecto.
- Luego de ejecutar el proyecto en local puede dirigirse a está URL: http://localhost:8081/swagger-ui/index.html donde se encuentra la especificación de los servicios y la forma como se deben consumir.
![SwaggerImage](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/api-swagger-img.png?raw=true)
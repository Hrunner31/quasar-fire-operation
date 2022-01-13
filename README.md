# OPERACIÓN FUEGO DE QUASAR

Proyecto creado en **lenguaje Java version 8** usando como Framework de desarrollo **Spring Boot version 2.6.2**.
El siguiente enlace los redireccionara al servicio desplegado en AWS: [Operación Fuego de Quasar](http://quasarfireoperation-env.eba-8iabjmhj.us-east-1.elasticbeanstalk.com/swagger-ui/index.html).

**Operación Fuego de Quasar**, es un proyecto que a través del cálculo de las distancias que tiene la nave espacial rebelde con respecto a los satélites y las posiciones que tiene estos mismos, poder calcular la coordenada de la posición de la nave rebelde, adicional poder armar el mensaje que está tratando de transmitir la nave rebelde. 

## Arquitectura de software

A continuación se exponen las diferentes vistas de la arquitectura utilizada para el desarrollo de software.
### Vista de paquetes
Se definieron 6 paquetes para el componente de software, como se puede ver en la siguiente imagen:
![PackageViewImage](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/packageView-img.png?raw=true)

### Vista de clases
A continuación se expone el diagrama de clases que representa la lógica del controlador, de los servicios, de las diferentes excepciones que se manejan dentro del proyecto y aquellas utilidades transversales entre los diferentes servicios.
![ClassViewImage](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/classView-img.png?raw=true)

Se siguieron los principios de SOLID para separar las responsabilidades de calcular las coordenadas de la nave y construir el mensaje desfasado en diferentes servicios; adicional se utilizó el patrón de diseño FACADE en los servicios, esto con el objetivo de separar toda la lógica de calcular las coordenadas de la nave y la construcción del mensaje para implementarlo si se desea en un microservicio y se pueda reutilizar está lógica.

También se separa las responsabilidades a través de capas, controlando la comunicación entre ellas a través de interfaces y el principio de inyección de dependencias.

### Elastic Beanstalk [EC2](https://aws.amazon.com/ec2)
Para la publicación del API se utilizó la nube de **AWS**, que nos brinda un servicio llamado **Elastic Beanstalk** que nos brinda un wizard para implementar y escalar aplicaciones web. Nos permite cargar nuestro código y maneja el equilibrio de carga, la gestión de registros y métricas, las alertas, la gestión de la versión de la aplicación y la resolución de DNS de forma transparente.
![ElasticBeanstalk](https://github.com/Hrunner31/quasar-fire-operation/blob/dev/documentation/img/elasticBeanstlk-img.png?raw=true)


## Dependencias

En el desarrollo de esté proyecto 

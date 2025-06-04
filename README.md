# APE DE SOA

Para el caso de estudio se procederá a la creación de un aplicativo de escritorio que permita ingresar los datos un Vehículo con los siguientes campos(id,
marca, modelo, placa, chasis, año del vehículo, color) utilizando servicios Web. Para el estudio, si se utilizarán tablas tipo catálogo (para marca, modelo, color).

# INSTALACION

1. Clona este repositorio
2. Comandos back:
```
cd backend
npm install
npm run dev
```
3. Comandos frontend:
```
cd frontend
mvn clean
mvn build
```
# PATRONES

1. Repository
2. Factory
... 

# ARQUITECTURA

**Clean Code**
1. *Data*: Maneja la fuente de datos, que puede venir de una base de datos, una API, o demás. Se suele dividir en: Data Sources (la implementación de los orígenes de datos), Models (abstracciones desde las que se toman los datos) y Repository (abstracción sobre los orígenes de datos, para manejar una interfaz unificada).

2. *Domain*: Contiene la lógica de negocio y reglas de la aplicación. Suele dividirse en: Entities (objetos específicos del negocio, con su lógica interna), Use Cases (lógica específica del negocio que maneja la interacción entre entidades) y Repository Interfaces (una abstracción sobre los orígenes de datos específicos)

3. *Presentation*: Es responsable de gestionar la lógica de interfaz e interacciones. Se divide en: BLoC (Business Logic Componente, maneja el estado de la aplicación), y en los distintos elementos de presentación de la aplicación (páginas, componentes, widgets, etc.).
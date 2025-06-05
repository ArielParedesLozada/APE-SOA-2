import { AppRoutes } from "./presentation/routes";
import { Server } from "./presentation/server";
import { envs } from "./config/envs";
import { SQLiteDatabase } from "./data/sqlite/sqlite.database";
import { ColorModel } from "./data/models/color.model";
import { VehiculoModel } from "./data/models/vehiculo.model";
import { MarcaModel } from "./data/models/marca.model";
import { ModeloModel } from "./data/models/modelo.model";
import { MySQLDatabase } from "./data/mysql/mysql.database";

(() => {
    main();
})()


async function main() {
    const database = MySQLDatabase.getInstance({
        database : envs.MYSQL_DB,
        password: envs.MYSQL_PASSWORD ?? '',
        port: envs.MYSQL_PORT,
        username: envs.MYSQL_USER,
        entities: [
            ColorModel,
            MarcaModel,
            ModeloModel,
            VehiculoModel
        ]
    })

    await database.connect()

    new Server({
        port: envs.PORT,
        routes: AppRoutes.routes
    })
        .start();
}